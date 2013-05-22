package com.stupid.neotube.impl.modules;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Path;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.stupid.neotube.api.TransportSystem;
import com.stupid.neotube.impl.Neo4JTransportSystem;

public class Neo4JTransportSystemModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<PathFinder<Path> >(){}).toProvider(AllPathRouteProvider.class);
		
		bind(TransportSystem.class).to(Neo4JTransportSystem.class);
		
	}

}
