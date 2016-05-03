package com.yud.sortinganimator.presenter;

import android.content.Context;
import android.support.annotation.CallSuper;

import com.yud.sortinganimator.view.AbstractView;

import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

/**
 * Created by yurik on 5/2/16.
 */
public class BasePresenter<V extends AbstractView> implements Presenter<V> {
	protected V view;
	protected final Context context;

	public BasePresenter(Context context) {
		this.context = context;

		RoboInjector injector = RoboGuice.getInjector(context);
		injector.injectMembersWithoutViews(this);
	}

	@CallSuper
	@Override
	public void onViewCreated(V view) {
		this.view = view;
	}

	@CallSuper
	@Override
	public void onViewDestroyed() {

	}
}
