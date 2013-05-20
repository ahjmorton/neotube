package com.stupid.neotube.api;

import java.util.List;

public interface TransportSystem {
	public List<Route> getRoutes(long startId, long endId);
}
