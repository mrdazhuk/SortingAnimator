package com.yud.sortinganimator.presenter;

import com.yud.sortinganimator.view.AbstractView;

/**
 * Created by yurik on 5/2/16.
 */
public interface Presenter<V extends AbstractView> {

	void onViewCreated(V view);

	void onViewDestroyed();
}
