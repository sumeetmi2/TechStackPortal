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

import com.techStackPortal.dataObject.PersonDO;
import com.techStackPortal.dataObject.ProjectDO;
import com.techStackPortal.graph.labels.NodeLabels;
import com.techStackPortal.graph.labels.RelationshipLabels;

@Component
public class GraphDBManager {

	static Logger LOGGER = Logger.getLogger(GraphDBManager.class);
	private ExecutionResult executionResult;
	
	public boolean addPersonNodeInGraph(PersonDO person) {
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
		}
		return false;
	}
	public Node createUniqueNode(ExecutionEngine engine,GraphDatabaseService graphService, String name, NodeLabels label) {
		Node result = null;
		if(name==null){
			return null;
		}else{
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
			}
		}
		return result;
	}
	
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
		}

		return result;
	}

}
