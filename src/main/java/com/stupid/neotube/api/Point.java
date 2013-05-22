package com.stupid.neotube.api;

public class Point {

	private final long nodeId;
	private final String name;

	public Point(long nodeId, String name) {
		super();
		this.nodeId = nodeId;
		this.name = name;
	}

	public long getNodeId() {
		return nodeId;
	}

	public String getName() {
		return name;
	}

}
