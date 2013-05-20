package com.stupid.neotube.impl;

import org.neo4j.graphdb.GraphDatabaseService;

import com.google.inject.AbstractModule;

public class Neo4JTubeModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GraphDatabaseService.class).toProvider(EmbeddedGraphProvider.class);
		
	}

}
