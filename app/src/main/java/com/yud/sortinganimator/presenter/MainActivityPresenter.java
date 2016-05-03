package com.yud.sortinganimator.presenter;

import com.yud.sortinganimator.view.MainActivity;

/**
 * Created by yurik on 5/3/16.
 */
public interface MainActivityPresenter extends Presenter<MainActivity> {
	void onSortClick();

	void onGenerateClick();

	void onAlgorithmSelected(MainActivityPresenterImpl.SortAlgorithms bubble);
}
