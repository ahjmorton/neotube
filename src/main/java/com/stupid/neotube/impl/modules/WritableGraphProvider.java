package com.stupid.neotube.impl.modules;

import javax.inject.Provider;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

class WritableGraphProvider implements Provider<GraphDatabaseService> {

	@Override
	public GraphDatabaseService get() {
		final GraphDatabaseService result = new GraphDatabaseFactory()
				.newEmbeddedDatabaseBuilder(Neo4JUtils.DB_PATH)
				.newGraphDatabase();

		Neo4JUtils.registerShutdown(result);
		return result;
	}

}
