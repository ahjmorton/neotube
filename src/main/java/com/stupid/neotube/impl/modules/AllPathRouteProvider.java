package com.stupid.neotube.impl.modules;

import javax.inject.Provider;

import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Path;
import org.neo4j.kernel.Traversal;

import com.stupid.neotube.impl.Transport;

public class AllPathRouteProvider implements Provider<PathFinder<Path> >{

	@Override
	public PathFinder<Path> get() {
		return GraphAlgoFactory.allPaths(Traversal.pathExpanderForTypes(Transport.LINK, Direction.BOTH), Integer.MAX_VALUE);
	}

}
