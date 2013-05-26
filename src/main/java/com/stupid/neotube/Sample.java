package com.stupid.neotube;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

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
	private final List<Point> allPoints;

	@Inject
	public Sample(TransportSystem system) {
		super();
		this.system = system;
		allPoints = Lists.newArrayList(system.getAllPoints());
	}

	private Point getRandomPoint() {
		return allPoints.get((int) (allPoints.size() * Math.random()));
	}

	private Point getPoint(String pointName) {
		return getPoint(Pattern.compile(pointName));
	}

	private Point getPoint(Pattern pattern) {
		final List<Point> points = new ArrayList<Point>();

		for (Point point : allPoints) {
			if (pattern.matcher(point.getName()).matches()) {
				points.add(point);
			}
		}

		if (points.isEmpty()) {
			throw new IllegalArgumentException("No points found matching ["
					+ pattern.pattern() + "]");
		} else if (points.size() > 1) {
			final StringBuilder messageBuilder = new StringBuilder(
					"Found multiple matches for [").append(pattern.pattern())
					.append("] :");
			final String newLine = System.getProperty("newline");
			for(Point point : points) {
				messageBuilder.append("    ").append(point.getName()).append(newLine);
			}
			throw new IllegalArgumentException(messageBuilder.toString());

		} else {
			return points.get(0);
		}
	}

	public void runTest() throws Exception {
		//while (true) {
			final Point start = getPoint("Whitechapel");
			final Point end = getPoint("White City");
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
		//}
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
