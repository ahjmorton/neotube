package com.stupid.neotube.impl.modules;

import org.neo4j.graphdb.GraphDatabaseService;

class Neo4JUtils {


	static void registerShutdown(final GraphDatabaseService db) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
	
			@Override
			public void run() {
				db.shutdown();
			}
		});
	}

	static final String DB_PATH = "neo4j.files";

}
