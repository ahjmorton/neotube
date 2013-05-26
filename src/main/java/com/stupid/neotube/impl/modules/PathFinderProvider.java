package com.stupid.neotube.impl.modules;

import javax.inject.Provider;

import static java.lang.Math.*;

import org.neo4j.graphalgo.CostEvaluator;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphalgo.WeightedPath;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.kernel.Traversal;

import com.stupid.neotube.impl.Transport;

public class PathFinderProvider implements Provider<PathFinder<? extends Path>> {

	@Override
	public PathFinder<WeightedPath> get() {
		return GraphAlgoFactory.dijkstra(
				Traversal.pathExpanderForTypes(Transport.LINK, Direction.BOTH),
				new CostEvaluator<Double>() {
					
					private static final double EARTH_RADIUS_KM = 6373d;

					private double distance(double pointALat, double pointALon,
							double pointBLat, double pointBLon) {
						final double lonDiff = pointBLon - pointALon;
						final double latDiff = pointBLat - pointALat;
						final double a = pow(sin(latDiff / 2), 2)
								+ cos(pointALat) * cos(pointBLat)
								* pow(sin(lonDiff / 2), 2);
						final double c = 2 * atan2(sqrt(a), sqrt(1 - a));
						return EARTH_RADIUS_KM * c;
					}

					@Override
					public Double getCost(Relationship relationship,
							Direction direction) {
						final Node from = relationship.getStartNode();
						final Node to = relationship.getEndNode();
						final double fromLat = ((Double)from.getProperty("lat")).doubleValue();
						final double fromLong = ((Double)from.getProperty("long")).doubleValue();
						final double toLat = ((Double)to.getProperty("lat")).doubleValue();
						final double toLong = ((Double)to.getProperty("long")).doubleValue();
						return Double.valueOf(distance(fromLat, fromLong, toLat, toLong));
					}
				});
	}

}
