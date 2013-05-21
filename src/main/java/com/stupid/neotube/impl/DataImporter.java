package com.stupid.neotube.impl;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import org.neo4j.graphdb.GraphDatabaseService;

import com.google.inject.Guice;
import com.google.inject.Injector;

import static com.stupid.neotube.impl.NodePropertyNames.*;

public class DataImporter {

	
	private static void performImport(GraphDatabaseService graphDb) {
		final Transaction trans = graphDb.beginTx();
		
	    final Node enfield = graphDb.createNode();
	    enfield.setProperty(POINT_NAME, "Enfield Lock");
	    
	    final Node liverpoolStreet = graphDb.createNode();
	    liverpoolStreet.setProperty(POINT_NAME, "London Liverpool Street");
	    
	    enfield.createRelationshipTo(liverpoolStreet, Transport.LINK);
		
		trans.finish();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Injector injector = Guice.createInjector(new Neo4JTubeModule());
		final GraphDatabaseService graphDb = injector.getInstance(GraphDatabaseService.class);
		performImport(graphDb);
	}

}
