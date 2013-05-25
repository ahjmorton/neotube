package com.stupid.neotube.impl;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	private static class PathToRouteIterator implements Iterator<Route>, Iterable<Route>{
		
		private final Iterator<Path> pathIter;
		
		private PathToRouteIterator(Iterator<Path> pathIter) {
			this.pathIter = pathIter;
		}

		@Override
		public boolean hasNext() {
			return pathIter.hasNext();
		}

		@Override
		public Route next() {
			final Path path = pathIter.next();
			final List<Point> points = new ArrayList<Point>();
			for (Node node : path.nodes()) {
				points.add(makePoint(node));
			}
			return new Route(points);
		}

		@Override
		public void remove() {
			pathIter.remove();
			
		}

		@Override
		public Iterator<Route> iterator() {
			return this;
		}
		
	}

	@Override
	public Iterable<Route> getRoutes(Point start, Point end) {
		final Node startNode = graphDB.getNodeById(start.getNodeId());
		final Node endNode = graphDB.getNodeById(end.getNodeId());
		return new PathToRouteIterator(routeFinder.findAllPaths(startNode, endNode).iterator());
	}

	private static Point makePoint(Node pointObject) {
		return new Point(pointObject.getId(),
				(String) pointObject.getProperty(NodePropertyNames.POINT_NAME));
	}

	@Override
	public Iterable<Point> getAllPoints() {
		final List<Point> result = new ArrayList<Point>();
		for (Node pointObject : globalOps.getAllNodes()) {
			result.add(makePoint(pointObject));

		}
		return result;
	}

}
