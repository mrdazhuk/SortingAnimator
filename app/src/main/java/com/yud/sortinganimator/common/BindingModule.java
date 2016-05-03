package com.yud.sortinganimator.common;

import android.app.Application;

import com.google.inject.AbstractModule;

/**
 * Created by tas on 14.01.2016.
 */
public class BindingModule extends AbstractModule {

	private Application application;

	public BindingModule(Application application) {
		this.application = application;
	}

	@Override
	protected void configure() {
	}
}
