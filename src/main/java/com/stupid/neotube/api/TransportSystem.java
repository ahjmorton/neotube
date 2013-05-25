package com.stupid.neotube.api;

import java.util.List;

public interface TransportSystem {
	public Iterable<Route> getRoutes(Point start, Point end);
	
	public Iterable<Point> getAllPoints();
}
