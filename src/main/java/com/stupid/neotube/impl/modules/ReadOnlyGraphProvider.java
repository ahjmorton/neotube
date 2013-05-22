package com.stupid.neotube.impl.modules;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;


public class ReadOnlyGraphProvider implements Provider<GraphDatabaseService>{

	@Override
	public GraphDatabaseService get() {
		final Map<String, String> config = new HashMap<String, String>();
		config.put("read_only", "true");
		final GraphDatabaseService result = new GraphDatabaseFactory()
				.newEmbeddedDatabaseBuilder(Neo4JUtils.DB_PATH).setConfig(config)
				.newGraphDatabase();
		Neo4JUtils.registerShutdown(result);
		return result;
	}



}
