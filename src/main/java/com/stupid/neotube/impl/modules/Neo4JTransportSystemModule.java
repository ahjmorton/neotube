package com.stupid.neotube.impl.modules;
import com.google.inject.AbstractModule;
import com.stupid.neotube.api.TransportSystem;
import com.stupid.neotube.impl.Neo4JTransportSystem;

public class Neo4JTransportSystemModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TransportSystem.class).to(Neo4JTransportSystem.class);
		
	}

}
