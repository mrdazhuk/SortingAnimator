package com.yud.sortinganimator.model.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Created by yurik on 5/2/16.
 */
public abstract class Sort<T> {
	public abstract void sort(List<T> items, Comparator<T> comparator, SortListener sortListener);

	protected final void swap(List<T> items, SortListener sortListener, int i, int j) {
		//Log.e("S", String.format("%d-%d, %d %d ", items.get(i), items.get(j), i, j));
		T t = items.get(i);
		items.set(i, items.get(j));
		items.set(j, t);
		sortListener.onSwapped(i, j);
	}
}
