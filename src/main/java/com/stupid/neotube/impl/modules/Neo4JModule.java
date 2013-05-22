package com.stupid.neotube.impl.modules;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.tooling.GlobalGraphOperations;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.stupid.neotube.impl.annotations.ReadOnlyGraph;
import com.stupid.neotube.impl.annotations.WritableGraph;

public class Neo4JModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GraphDatabaseService.class).annotatedWith(WritableGraph.class)
				.toProvider(WritableGraphProvider.class).in(Singleton.class);

		bind(GraphDatabaseService.class).annotatedWith(ReadOnlyGraph.class)
				.toProvider(ReadOnlyGraphProvider.class).in(Singleton.class);

	}

	@Provides
	@Inject
	public GlobalGraphOperations getGlobalGraphOperations(
			@ReadOnlyGraph GraphDatabaseService graphDb) {
		return GlobalGraphOperations.at(graphDb);
	}

}
