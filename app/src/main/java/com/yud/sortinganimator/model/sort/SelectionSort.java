package com.yud.sortinganimator.model.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Created by yurik on 5/2/16.
 */
public class SelectionSort extends Sort<Integer> {
	@Override
	public void sort(List<Integer> items, Comparator<Integer> comparator, SortListener sortListener) {
		for (int i = 0; i < items.size(); i++) {
			Integer min = items.get(i);
			Integer minIndex = i;
			for (int j = i + 1; j < items.size(); j++) {
				if (comparator.compare(items.get(j), min) > 0) {
					min = items.get(j);
					minIndex = j;
				}
			}
			if (i != minIndex) {
				swap(items, sortListener, i, minIndex);
			}
		}
	}
}
