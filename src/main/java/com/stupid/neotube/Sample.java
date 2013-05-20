package com.stupid.neotube;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.stupid.neotube.api.TransportSystem;
import com.stupid.neotube.impl.Neo4JTubeModule;

public class Sample {
	
	private final TransportSystem system;
	
	
	public Sample(TransportSystem system) {
		super();
		this.system = system;
	}
	
	public void runTest() {
		
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Injector injector = Guice.createInjector(new Neo4JTubeModule());
		final TransportSystem system = injector.getInstance(TransportSystem.class);
		final Sample sample = new Sample(system);
		sample.runTest();
	}

}
