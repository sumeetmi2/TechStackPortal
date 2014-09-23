package com.techStackPortal.graph.implementation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.log4j.Logger;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.graphdb.index.ReadableIndex;

import com.techStackPortal.common.ApplicationProperties;

public class GraphDB {

	private static String DB_PATH = null;
	private static GraphDatabaseService graphService = null;
	private static ExecutionEngine executionEngine = null;
	private static final Logger LOGGER = Logger.getLogger(GraphDB.class);
	
	static{
		DB_PATH = ApplicationProperties.getProperty("neo4j.db.location");
		try{ 
			graphService = new GraphDatabaseFactory()
					.newEmbeddedDatabaseBuilder(DB_PATH)
					.setConfig(GraphDatabaseSettings.node_keys_indexable,"name")
					.setConfig(GraphDatabaseSettings.relationship_keys_indexable,"name")
					.setConfig(GraphDatabaseSettings.node_auto_indexing, "true")
					.setConfig(GraphDatabaseSettings.relationship_auto_indexing,"true")
					.newGraphDatabase();
			registerShutdownHook(graphService);
			executionEngine= getEngine(graphService);
		}catch(Exception e){
			File file = new File(DB_PATH+"/tm_tx_log.1");
			file.delete();
			LOGGER.debug("Error initializing graph",e);
		}
	}
	
	private GraphDB() {
	}	
	
	/**
	 * @return graph service 
	 */
	public static GraphDatabaseService getGraphService(){
		return graphService;
	}
	
	
	/**
	 * @return execution engine for executing cypher queries
	 */
	public static ExecutionEngine getExecutionEngine(){
		return executionEngine;
	}
	
	
	/**
	 *  shutdown graph database
	 */
	public static void shutDown() {
		graphService.shutdown();
	}

	
	/**
	 * @param graphDb
	 * to initiate a shutdown if graph db crshes or closes suddenly
	 */
	private static void registerShutdownHook(final GraphDatabaseService graphDb) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}
	

	/**
	 *  clearing graph db
	 */
	public static void clearDb() {
		try {
			File files = new File(DB_PATH);
			for(File file : files.listFiles()){
				FileDeleteStrategy.FORCE.delete(file);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * @param graphService 
	 * @return execution engine instance
	 */
	private static ExecutionEngine getEngine(GraphDatabaseService graphService) {
		try (Transaction tx = graphService.beginTx()) {
//			graphService.schema().constraintFor(NodeLabels.PERSON).assertPropertyIsUnique("personId");
//			graphService.schema().constraintFor(NodeLabels.DEGREE).assertPropertyIsUnique("name");
//			graphService.schema().constraintFor(NodeLabels.SPECIALIZATION).assertPropertyIsUnique("name");
//			graphService.schema().constraintFor(NodeLabels.INSTITUTION).assertPropertyIsUnique("name");
//			graphService.schema().constraintFor(NodeLabels.LOCATION).assertPropertyIsUnique("name");
//			graphService.schema().constraintFor(NodeLabels.ROLE).assertPropertyIsUnique("name");
//			graphService.schema().constraintFor(NodeLabels.EMPLOYER).assertPropertyIsUnique("name");
//			graphService.schema().constraintFor(NodeLabels.MONTH).assertPropertyIsUnique("name");
//			graphService.schema().constraintFor(NodeLabels.EVENT).assertPropertyIsUnique("name");
//			graphService.schema().constraintFor(NodeLabels.YEAR).assertPropertyIsUnique("name");
			tx.success();
		}
		return new ExecutionEngine(graphService);
	}
	
}
