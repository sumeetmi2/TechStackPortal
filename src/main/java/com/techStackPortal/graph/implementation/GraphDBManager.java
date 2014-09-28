package com.techStackPortal.graph.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.neo4j.cypher.CypherException;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.tooling.GlobalGraphOperations;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.techStackPortal.dataObject.PersonDO;
import com.techStackPortal.dataObject.ProjectDO;
import com.techStackPortal.dataObject.QueryDO;
import com.techStackPortal.graph.labels.NodeLabels;
import com.techStackPortal.graph.labels.RelationshipLabels;

@Component
public class GraphDBManager {

	static Logger LOGGER = Logger.getLogger(GraphDBManager.class);
	private ExecutionResult executionResult;
	
	/**
	 * @param person
	 * @return 
	 */
	public boolean addPersonNodeInGraph(PersonDO person) throws Exception{
		GraphDatabaseService graphService = GraphDB.getGraphService();
		ExecutionEngine engine = GraphDB.getExecutionEngine();
		String nameParam = person.getName() +"|"+person.getId();
		Node employeeNode = null;
		Node projectNode = null;
		Node technologyNode = null;
		ArrayList<ProjectDO> projects = person.getProjects();
		ArrayList<String> technologies = null;
		try (Transaction tx = graphService.beginTx()) {
			LOGGER.info("No of nodes : " + IteratorUtil.count(GlobalGraphOperations.at(graphService).getAllNodes()));
			employeeNode = createUniqueNode(engine, graphService, nameParam, NodeLabels.EMPLOYEE);
			for(ProjectDO proj : projects){
				projectNode = createUniqueNode(engine, graphService,proj.getName(), NodeLabels.PROJECT);
				createRelationshipBetween(engine, graphService, employeeNode, projectNode, RelationshipLabels.HAS_WORKED);
				technologies = proj.getTechnologies();
				for(String tech : technologies){
					technologyNode = createUniqueNode(engine, graphService,tech, NodeLabels.TECHNOLOGY);
					createRelationshipBetween(engine, graphService, projectNode,technologyNode,RelationshipLabels.HAS);
					createRelationshipBetween(engine, graphService, employeeNode,technologyNode,RelationshipLabels.KNOWS);
				}
			}
			tx.success();
		}catch(Exception e){
			LOGGER.error("Exception while creating nodes",e);
			throw e;
		}
		return true;
	}
	
	/**
	 * @param engine
	 * @param graphService
	 * @param name
	 * @param label
	 * @return
	 */
	private Node createUniqueNode(ExecutionEngine engine,GraphDatabaseService graphService, String name, NodeLabels label) {
		Node result = null;
		if(name==null){
			return null;
		}else{
			name = name.toLowerCase();
			ResourceIterator<Node> resultIterator;
			Map<String, Object> parameters = new HashMap<String, Object>();
			String params = " {name: {name} ";

			String[] personParam = name.split("\\|");
			if (personParam.length > 1) {
				params = params + ", empCode: {empCode} ";
				parameters.put("empCode", personParam[1]);
			}
			parameters.put("name", personParam[0]);
			String queryString = "MERGE (n:" + label + params + " } ) RETURN n";

			try (Transaction tx = graphService.beginTx()) {
				executionResult = engine.execute(queryString, parameters);
				resultIterator = executionResult.columnAs("n");
				if(resultIterator.hasNext()){
					result = resultIterator.next();
				}
				resultIterator.close();
				// searchIndex.add(result,"name", personParam[0]);
				tx.success();
			} catch (CypherException e) {
				LOGGER.error("Error while creating unique node : " + e);
				throw e;
			}
		}
		return result;
	}
	
	/**
	 * @param engine
	 * @param graphService
	 * @param startNode
	 * @param endNode
	 * @param rel
	 * @return
	 */
	private Relationship createRelationshipBetween(ExecutionEngine engine,GraphDatabaseService graphService, Node startNode, Node endNode, RelationshipLabels rel){
		Relationship result = null;
		ExecutionResult executionResult;
		try (Transaction tx = graphService.beginTx()) {
			ResourceIterator<Relationship> resultIterator;
			String queryString = "START n=node("+startNode.getId()+") , m=node("+endNode.getId()+") WITH n,m MERGE n-[r:"+rel+"]->m RETURN r";
			executionResult = engine.execute(queryString);
			resultIterator = executionResult.columnAs("r");
			if(resultIterator.hasNext()){
				result = resultIterator.next();
			}
			resultIterator.close();
			tx.success();
		} catch (CypherException e) {
			LOGGER.error("Error while creating relationship between two nodes startNode : "
					+ startNode.toString()
					+ ", endNode : "
					+ endNode.toString() + e);
			throw e;
		}

		return result;
	}
	
	/**
	 * @param queryObj
	 * @return list of person objects from search
	 */
	public QueryDO getSearchResult(QueryDO queryObj) throws Exception{
		QueryDO resultObj = null;
		try{
			ExecutionResult executionResult;
			GraphDatabaseService graphService = GraphDB.getGraphService();
			ExecutionEngine engine = GraphDB.getExecutionEngine();
			String query = "MATCH (e:EMPLOYEE) -[:KNOWS]-> (t:TECHNOLOGY)<-[:HAS]-(p:PROJECT) "
						+"WITH e,t,p "
						+"MATCH (p)<-[:HAS_WORKED]-(e) "
						+"{queryParam} "
						+"WITH e,{name: p.name, technologies: collect(t.name)} as projs "
						+"WITH {id: e.empCode,name: e.name,projects: collect(projs)} as person "
						+"RETURN {result: collect(person)} as result";
			String queryStr = "";
			boolean flag = false;
			if(queryObj != null){
				queryStr += "WHERE ";
				if(queryObj.getPersonName() != null){
					queryStr += "e.name =~ \".*"+queryObj.getPersonName().toLowerCase()+".*\" ";
					flag =true;
				}
				
				if(queryObj.getProjName() != null){
					if(queryStr.contains("e.name")) {
						queryStr +="AND ";
					}
					queryStr += "p.name =~ \".*"+queryObj.getProjName().toLowerCase()+".*\" ";
					flag =true;
				}
				
				if(queryObj.getTechnologyName() != null){
					if(queryStr.contains("e.name") || queryStr.contains("p.name")) {
						queryStr +="AND ";
					}
					queryStr += "t.name =~ \".*"+queryObj.getTechnologyName().toLowerCase()+".*\" ";
					flag =true;
				}
			}
			if(!flag){
				queryStr = "";
			}
			query = query.replaceAll("\\{queryParam\\}", queryStr);
			try(Transaction tx = graphService.beginTx()){
				executionResult = engine.execute(query);
				Gson g1 = new GsonBuilder().setPrettyPrinting().create();
				String result = g1.toJson(IteratorUtil.asList(executionResult.iterator()));
				JsonParser parser = new JsonParser();
				JsonArray jArray = parser.parse(result).getAsJsonArray();
				JsonElement elem = null;
				if(jArray.size()>0){
					elem = jArray.get(0).getAsJsonObject().get("result");
				}	
				resultObj = g1.fromJson(elem, QueryDO.class);
				tx.success();
			}
		}catch(Exception e){
			LOGGER.error("Exception while querying",e);
			throw e;
		}
		return resultObj;
	}

}
