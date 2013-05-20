package com.stupid.neotube.impl;

import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;

import javax.inject.Inject;

import com.stupid.neotube.api.Point;
import com.stupid.neotube.api.Route;
import com.stupid.neotube.api.TransportSystem;

public class Neo4JTransportSystem implements TransportSystem {
	
	private final GraphDatabaseService graphDB;
	
	@Inject
	public Neo4JTransportSystem(GraphDatabaseService graphDB) {
		super();
		this.graphDB = graphDB;
	}

	@Override
	public List<Route> getRoutes(Point start, Point end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Point> getAllPoints() {
		// TODO Auto-generated method stub
		return null;
	}

}
