package com.stupid.neotube.api;

import java.util.List;

public class Route {
	
	private final List<Point> path;

	public Route(List<Point> path) {
		super();
		this.path = path;
	}

	public List<Point> getPath() {
		return path;
	}
	
	
}
