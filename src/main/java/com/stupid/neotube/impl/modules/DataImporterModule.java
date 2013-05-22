package com.stupid.neotube.impl.modules;

import com.google.inject.AbstractModule;
import com.stupid.neotube.impl.DataImporter;

public class DataImporterModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DataImporter.class);
		
	}

}
