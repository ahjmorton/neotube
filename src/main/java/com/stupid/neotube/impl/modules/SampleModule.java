package com.stupid.neotube.impl.modules;

import com.google.inject.AbstractModule;
import com.stupid.neotube.Sample;

public class SampleModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Sample.class);
		
	}

}
