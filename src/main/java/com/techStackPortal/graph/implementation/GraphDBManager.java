package com.techStackPortal.graph.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.neo4j.cypher.CypherException;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.ReadableIndex;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.tooling.GlobalGraphOperations;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.techStackPortal.dataObject.PersonDO;
import com.techStackPortal.dataObject.PropsDO;
import com.techStackPortal.dataObject.ResultDO;

/**
 * @author SumeetS
 *
 */
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
		String nameParam = person.getFirstName()+" "+person.getLastName() +"|"+person.getId();
		try(Transaction tx = graphService.beginTx()){
			ReadableIndex<Node> nodeAutoIndex = graphService.index().getNodeAutoIndexer().getAutoIndex();
			IndexHits<Node> r1= nodeAutoIndex.get("empCode",person.getId());
			if(r1.hasNext()){
				return false;
			}
			tx.success();
		}catch(Exception e){
			LOGGER.error("Exception while creating nodes",e);
		}
		Node n = null;
		Node personNode = null;
		Map<String,String> propsMap = new HashMap<String,String>();
		ArrayList<PropsDO> props = person.getProps();
		for(PropsDO prop : props){
			propsMap.put(prop.getName(),prop.getValue());
		}
		try (Transaction tx = graphService.beginTx()) {
			LOGGER.info("No of nodes : " + IteratorUtil.count(GlobalGraphOperations.at(graphService).getAllNodes()));
			personNode = createUniqueNode(engine, graphService,nameParam,"employee");
			for(String key : propsMap.keySet()){
				n = createUniqueNode(engine, graphService, propsMap.get(key), key);
				createRelationshipBetween(engine, graphService, personNode, n, person.getId());
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
	private Node createUniqueNode(ExecutionEngine engine,GraphDatabaseService graphService, String name, String label) {
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
	private Relationship createRelationshipBetween(ExecutionEngine engine,GraphDatabaseService graphService, Node startNode, Node endNode,String rel){
		Relationship result = null;
		ExecutionResult executionResult;
		try (Transaction tx = graphService.beginTx()) {
			ResourceIterator<Relationship> resultIterator;
			String queryString = "START n=node("+startNode.getId()+") , m=node("+endNode.getId()+") WITH n,m MERGE n-[r:"+"t"+rel.trim()+""+"]->m RETURN r";
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
	public ArrayList<ResultDO> getSearchResult(String search) throws Exception{
		ResultDO tempObj = null;
		ArrayList<ResultDO> resultObject = new ArrayList<ResultDO>();
		try{
			ExecutionResult executionResult;
			GraphDatabaseService graphService = GraphDB.getGraphService();
			ExecutionEngine engine = GraphDB.getExecutionEngine();
			String query = "match (n)-[r]-() where n.name =~ \".*{searchParam}.*\" return distinct type(r)";
			String query1 = "Match (n)-[x:{rel}]-(r) "
					+"with distinct type(x) as x,{value: r.name, label: labels(r)} as l "
					+"return {relType: x,result: collect(l)} as results";
			String queryStr = "";
			String masterQuery = "";
			if(search != null && search.trim().length()>0){
				queryStr = search;
			}
			query = query.replaceAll("\\{searchParam\\}", queryStr);
			try(Transaction tx = graphService.beginTx()){
				executionResult = engine.execute(query);
				for (Map<String, Object> row : executionResult) {
			        for (Entry<String, Object> column : row.entrySet()) {
			        	if(masterQuery.length()>0){
			        		masterQuery += " UNION ";
			        	}
			        	String tmp = query1;
			        	tmp = tmp.replaceAll("\\{rel\\}",column.getValue().toString());
			        	masterQuery += tmp;
			        }
				}
				if(masterQuery.length() > 0 ){
					executionResult = engine.execute(masterQuery);
					Gson g1 = new GsonBuilder().setPrettyPrinting().create();
					String result = g1.toJson(IteratorUtil.asList(executionResult.iterator()));
					JsonParser parser = new JsonParser();
					JsonArray jArray = parser.parse(result).getAsJsonArray();
					JsonElement elem = null;
					int size = jArray.size();
					for(int i =0;i<size;i++){
						elem = jArray.get(i).getAsJsonObject().get("results");
						tempObj = g1.fromJson(elem, ResultDO.class);
						resultObject.add(tempObj);
					}
				}
				tx.success();
			}
		}catch(Exception e){
			LOGGER.error("Exception while querying",e);
			throw e;
		}
		return resultObject;
	}

}
