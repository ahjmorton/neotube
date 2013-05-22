package com.stupid.neotube.impl;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

import javax.inject.Inject;

import com.stupid.neotube.api.Point;
import com.stupid.neotube.api.Route;
import com.stupid.neotube.api.TransportSystem;
import com.stupid.neotube.impl.annotations.ReadOnlyGraph;

public class Neo4JTransportSystem implements TransportSystem {

	private final GraphDatabaseService graphDB;
	private final GlobalGraphOperations globalOps;

	@Inject
	public Neo4JTransportSystem(@ReadOnlyGraph GraphDatabaseService graphDB,
			GlobalGraphOperations globalOps) {
		super();
		this.graphDB = graphDB;
		this.globalOps = globalOps;
	}

	@Override
	public List<Route> getRoutes(Point start, Point end) {
		return null;
	}

	@Override
	public List<Point> getAllPoints() {
		final List<Point> result = new ArrayList<Point>();
		for (Node pointObject : globalOps.getAllNodes()) {
			if (pointObject.hasProperty(NodePropertyNames.POINT_NAME)) {
				final Point point = new Point(
						(String) pointObject
								.getProperty(NodePropertyNames.POINT_NAME));

				result.add(point);
			}
		}
		return result;
	}

}
