package com.techStackPortal.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.ReadableIndex;

import com.techStackPortal.graph.implementation.GraphDB;

//@RunWith(Parameterized.class)
public class TestCases{
	
	private GraphDatabaseService graphService = null;
	private Node n1=null,n2=null;
	
	@Before
	public void initialize(){
		System.out.println("init");
		graphService = GraphDB.getGraphService();
		try(Transaction tx = graphService.beginTx()){
			n1 = graphService.createNode();
			n2 = graphService.createNode();
			n1.setProperty("name","Sumeet");
			n2.setProperty("name","Harish");
			tx.success();
		}
	}
	
	@After
	public void destroy(){
		System.out.println("destroy");
		try(Transaction tx = graphService.beginTx()){
			n1.delete();
			n2.delete();
			tx.success();
		}
	}
	
//	@Parameters
//	public static Collection nodes(){
//		
//	}
	
	@Test
	public void testNodeProps(){
		System.out.println("testing");
		 ReadableIndex<Node> nodeAutoIndex = graphService.index().getNodeAutoIndexer().getAutoIndex();;
		try(Transaction tx = graphService.beginTx()){
//			assertFalse(nodeAutoIndex.get("name","Sumeet").hasNext());
			assertEquals(n2,nodeAutoIndex.get("name","Harish").getSingle());
		}
	}

	
}
