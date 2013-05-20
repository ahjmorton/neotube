package com.stupid.neotube.impl;

import org.neo4j.graphdb.GraphDatabaseService;

import com.google.inject.AbstractModule;
import com.stupid.neotube.api.TransportSystem;

public class Neo4JTubeModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GraphDatabaseService.class).toProvider(EmbeddedGraphProvider.class);
		
		bind(TransportSystem.class).to(Neo4JTransportSystem.class);
		
	}

}
