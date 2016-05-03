package com.yud.sortinganimator.model.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Created by yurik on 5/2/16.
 */
public class QuickSort extends Sort<Integer> {
	@Override
	public void sort(List<Integer> items, Comparator<Integer> comparator, SortListener sortListener) {
		int startIndex = 0;
		int endIndex = items.size() - 1;
		doSort(items, comparator, sortListener, startIndex, endIndex);
	}

	private void doSort(List<Integer> items, Comparator<Integer> comparator, SortListener sortListener, int start, int end) {
		if (start >= end) {
			return;
		}
		int i = start, j = end;
		int cur = i - (i - j) / 2;
		while (i < j) {
			while (i < cur && (comparator.compare(items.get(i), items.get(cur)) >= 0)) {
				i++;
			}
			while (j > cur && (comparator.compare(items.get(cur), items.get(j)) >= 0)) {
				j--;
			}
			if (i < j) {
				swap(items, sortListener, i, j);
				if (i == cur)
					cur = j;
				else if (j == cur)
					cur = i;
			}
		}
		doSort(items, comparator, sortListener, start, cur);
		doSort(items, comparator, sortListener, cur + 1, end);
	}
}
