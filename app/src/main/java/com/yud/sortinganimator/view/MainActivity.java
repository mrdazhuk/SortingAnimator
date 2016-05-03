package com.yud.sortinganimator.view;

import com.yud.sortinganimator.presenter.MainActivityPresenter;

import java.util.List;

/**
 * Created by yurik on 5/3/16.
 */
public interface MainActivity extends AbstractView<MainActivityPresenter> {
	void showValues(List<Integer> integers);

	void swapItems(int from, int to);

	void showCongratulation();
}
