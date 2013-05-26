package com.stupid.neotube;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.stupid.neotube.api.Point;
import com.stupid.neotube.api.Route;
import com.stupid.neotube.api.TransportSystem;
import com.stupid.neotube.impl.modules.Neo4JModule;
import com.stupid.neotube.impl.modules.Neo4JTransportSystemModule;
import com.stupid.neotube.impl.modules.SampleModule;

public class Sample {

	private final TransportSystem system;

	@Inject
	public Sample(TransportSystem system) {
		super();
		this.system = system;
	}

	private static Point getRandomPoint(List<Point> allPoints) {
		return allPoints.get((int) (allPoints.size() * Math.random()));
	}

	public void runTest() throws Exception {
		final List<Point> allPoints = Lists.newArrayList(system.getAllPoints());
		while (true) {
			final Point start = getRandomPoint(allPoints);
			final Point end = getRandomPoint(allPoints);
			System.out.println("Finding shortest route from ["
					+ start.getName() + "] to [" + end.getName() + "]");
			final Route route = system.getFastestRoute(start, end);
			System.out.println("Found route with [" + route.getPath().size()
					+ "] steps");
			for (Point point : route.getPath()) {
				System.out.println("    " + point.getName());
			}
			System.out.println();
			TimeUnit.MILLISECONDS.sleep(250);
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		final Injector injector = Guice.createInjector(new Neo4JModule(),
				new Neo4JTransportSystemModule(), new SampleModule());
		final Sample sample = injector.getInstance(Sample.class);
		sample.runTest();
	}

}
