package com.stupid.neotube.api;

public interface TransportSystem {
	public Iterable<Route> getRoutes(Point start, Point end);
	
	public Iterable<Point> getAllPoints();
}
