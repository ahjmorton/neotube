package com.stupid.neotube.impl.data;

import java.util.List;

import javax.inject.Inject;

import org.neo4j.cypher.javacompat.ExecutionEngine;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.stupid.neotube.impl.modules.DataImporterModule;
import com.stupid.neotube.impl.modules.Neo4JModule;

public class DataImporter {

	private final ExecutionEngine executor;
	private final List<String> scriptLines;

	@Inject
	public DataImporter( ExecutionEngine executor, @InitialImport List<String> scriptLines) {
		super();
		this.executor = executor;
		this.scriptLines = scriptLines;
	}


	private void performImport() {
		for(String line : scriptLines) {
			executor.execute(line);
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
