package com.yud.sortinganimator.view;

import com.yud.sortinganimator.presenter.Presenter;

import roboguice.activity.RoboActionBarActivity;

/**
 * Created by yurik on 5/2/16.
 */
public class BaseActivity<P extends Presenter> extends RoboActionBarActivity implements AbstractView<P> {

	protected P presenter;

	protected void setPresenter(P presenter) {
		this.presenter = presenter;
	}
}
