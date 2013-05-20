package com.stupid.neotube.impl;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import javax.inject.Provider;

public class EmbeddedGraphProvider implements Provider<GraphDatabaseService> {
	
	private static final String DB_PATH = "neo4j.file";

	public GraphDatabaseService get() {
		final GraphDatabaseService result = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
		registerShutdown(result);
		return result;
	}
	
	private static void registerShutdown(final GraphDatabaseService db) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			@Override
			public void run() {
				db.shutdown();
			}
		});
	}

}
