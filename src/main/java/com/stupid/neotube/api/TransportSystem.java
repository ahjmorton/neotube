package com.stupid.neotube.api;

import java.util.List;

public interface TransportSystem {
	public List<Route> getRoutes(Point start, Point end);
	
	public List<Point> getAllPoints();
}
