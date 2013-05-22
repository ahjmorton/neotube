package com.stupid.neotube.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.tooling.GlobalGraphOperations;

import com.stupid.neotube.api.Point;
import com.stupid.neotube.api.Route;
import com.stupid.neotube.api.TransportSystem;
import com.stupid.neotube.impl.annotations.ReadOnlyGraph;

public class Neo4JTransportSystem implements TransportSystem {

	private final GraphDatabaseService graphDB;
	private final GlobalGraphOperations globalOps;
	private final PathFinder<Path> routeFinder;

	@Inject
	public Neo4JTransportSystem(@ReadOnlyGraph GraphDatabaseService graphDB,
			GlobalGraphOperations globalOps, PathFinder<Path> routeFinder) {
		super();
		this.graphDB = graphDB;
		this.globalOps = globalOps;
		this.routeFinder = routeFinder;
	}

	@Override
	public List<Route> getRoutes(Point start, Point end) {
		final Node startNode = graphDB.getNodeById(start.getNodeId());
		final Node endNode = graphDB.getNodeById(end.getNodeId());
		final List<Route> routes = new ArrayList<Route>();
		for (Path path : routeFinder.findAllPaths(startNode, endNode)) {
			final List<Point> points = new ArrayList<Point>();
			for (Node node : path.nodes()) {
				points.add(makePoint(node));
			}
			routes.add(new Route(points));
		}
		return routes;
	}

	private static Point makePoint(Node pointObject) {
		return new Point(pointObject.getId(),
				(String) pointObject.getProperty(NodePropertyNames.POINT_NAME));
	}

	@Override
	public List<Point> getAllPoints() {
		final List<Point> result = new ArrayList<Point>();
		for (Node pointObject : globalOps.getAllNodes()) {
			result.add(makePoint(pointObject));

		}
		return result;
	}

}
