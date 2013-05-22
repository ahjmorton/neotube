package com.stupid.neotube.impl;

import static com.stupid.neotube.impl.NodePropertyNames.POINT_NAME;

import javax.inject.Inject;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.stupid.neotube.impl.annotations.WritableGraph;
import com.stupid.neotube.impl.modules.DataImporterModule;
import com.stupid.neotube.impl.modules.Neo4JModule;

public class DataImporter {

	private final GraphDatabaseService graphDb;

	@Inject
	public DataImporter(@WritableGraph GraphDatabaseService graphDb) {
		super();
		this.graphDb = graphDb;
	}

	private static void link(Node from, Node to) {
		from.createRelationshipTo(to, Transport.LINK);
	}

	@SuppressWarnings("deprecation")
	private void performImport() {
		final Transaction trans = graphDb.beginTx();
		try {
			// Remove reference node
			graphDb.getReferenceNode().delete();
			
			final Node enfield = graphDb.createNode();
			enfield.setProperty(POINT_NAME, "Enfield Lock");

			final Node brimsdown = graphDb.createNode();
			brimsdown.setProperty(POINT_NAME, "Brimsdown");

			final Node pondersEnd = graphDb.createNode();
			pondersEnd.setProperty(POINT_NAME, "Ponders End");

			final Node liverpoolStreet = graphDb.createNode();
			liverpoolStreet.setProperty(POINT_NAME, "London Liverpool Street");

			final Node walthamCross = graphDb.createNode();
			walthamCross.setProperty(POINT_NAME, "Waltham Cross");

			link(enfield, walthamCross);
			link(enfield, brimsdown);
			link(brimsdown, pondersEnd);
			link(pondersEnd, liverpoolStreet);
			trans.success();
		} catch (Exception e) {
			trans.failure();
			e.printStackTrace();
		} finally {
			trans.finish();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Injector injector = Guice.createInjector(new Neo4JModule(),
				new DataImporterModule());
		final DataImporter importer = injector.getInstance(DataImporter.class);

		importer.performImport();
	}

}
