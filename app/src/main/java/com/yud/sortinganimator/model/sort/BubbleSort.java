package com.yud.sortinganimator.model.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Created by yurik on 5/2/16.
 */
public class BubbleSort extends Sort<Integer> {
	@Override
	public void sort(List<Integer> items, Comparator<Integer> comparator, SortListener sortListener) {
		int j;
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (j = 0; j < items.size() - 1; j++) {
				if (comparator.compare(items.get(j), items.get(j + 1)) <  0) {
					swap(items, sortListener, j, j + 1);

					swapped = true;
				}
			}
		}
	}
}
