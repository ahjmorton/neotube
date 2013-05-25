package com.stupid.neotube;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.stupid.neotube.api.Point;
import com.stupid.neotube.api.Route;
import com.stupid.neotube.api.TransportSystem;
import com.stupid.neotube.impl.modules.Neo4JModule;
import com.stupid.neotube.impl.modules.Neo4JTransportSystemModule;

public class Sample {
	
	private final TransportSystem system;

	public Sample(TransportSystem system) {
		super();
		this.system = system;
	}

	public void runTest() {
		final List<Point> allPoints = Lists.newArrayList(system.getAllPoints());
		final int numOfPoints = allPoints.size();
		final Point start = allPoints.get((int) (numOfPoints * Math.random()));
		final Point end = allPoints.get((int) (numOfPoints * Math.random()));
		System.out.println("From [" + numOfPoints + "] points our start is ["
				+ start.getName() + "], ending at [" + end.getName() + "]");
		int i = 0;
		for(Route route : system.getRoutes(start, end)) {
			System.out.println("Route " + (++i) + ":");
			for(Point point : route.getPath()) {
				System.out.println("    " + point.getName());
			}
			System.out.println();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Injector injector = Guice.createInjector(new Neo4JModule(), new Neo4JTransportSystemModule());
		final TransportSystem system = injector
				.getInstance(TransportSystem.class);
		final Sample sample = new Sample(system);
		sample.runTest();
	}

}
