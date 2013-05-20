package com.stupid.neotube.impl;

import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;

import javax.inject.Inject;
import com.stupid.neotube.api.Route;
import com.stupid.neotube.api.TransportSystem;

public class Neo4JTransportSystem implements TransportSystem {
	
	private GraphDatabaseService graphDB;

	@Inject
	public void setGraphDB(GraphDatabaseService graphDB) {
		this.graphDB = graphDB;
	}

	public List<Route> getRoutes(long startId, long endId) {
		// TODO Auto-generated method stub
		return null;
	}


}
