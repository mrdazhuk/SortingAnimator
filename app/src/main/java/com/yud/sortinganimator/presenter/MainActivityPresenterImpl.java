package com.yud.sortinganimator.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yud.sortinganimator.common.SortAlgorithms;
import com.yud.sortinganimator.model.sort.BubbleSort;
import com.yud.sortinganimator.model.sort.QuickSort;
import com.yud.sortinganimator.model.sort.SelectionSort;
import com.yud.sortinganimator.model.sort.Sort;
import com.yud.sortinganimator.view.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yurik on 5/2/16.
 */
public class MainActivityPresenterImpl extends BasePresenter<MainActivity> implements MainActivityPresenter {
	public static final int MAX_INT_VALUE = 100;
	private final static int MAX_COUNT = 35;
	public static final int DELAY_SWAP_TIME = 450;

	private List<Integer> integers = new ArrayList<>(MAX_COUNT);
	private Subscription sortingSubscription;
	private Sort<Integer> sortAlgorithm = new BubbleSort();

	public MainActivityPresenterImpl(Context context) {
		super(context);
	}

	@Override
	public void onViewCreated(MainActivity view) {
		super.onViewCreated(view);
		generateRandomValues();
	}

	@Override
	public void onViewDestroyed() {
		super.onViewDestroyed();
		unsubscribe();
	}

	@Override
	public void onSortClick() {
		this.sortingSubscription = getSwapObservable(this.sortAlgorithm)
				.doOnNext(swap1 -> delay())
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(swap -> this.view.swapItems(swap.from, swap.to)
						, Throwable::printStackTrace,
						this.view::showCongratulation);
	}

	private void delay( ) {
		try {
			Thread.sleep(DELAY_SWAP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onGenerateClick() {
		generateRandomValues();
	}

	@Override
	public void onAlgorithmSelected(SortAlgorithms algorithm) {
		switch (algorithm) {
			case BUBBLE:
				this.sortAlgorithm = new BubbleSort();
				break;
			case SELECTION:
				this.sortAlgorithm = new SelectionSort();
				break;
			case QUICK:
			default:
				this.sortAlgorithm = new QuickSort();
				break;
		}
	}

	@NonNull
	private Observable<Swap> getSwapObservable(Sort<Integer> sort) {
		return Observable.create(new Observable.OnSubscribe<Swap>() {
			@Override
			public void call(Subscriber<? super Swap> subscriber) {
				sort.sort(integers, (lhs, rhs) -> rhs - lhs, (i, j) -> subscriber.onNext(new Swap(i, j)));
				subscriber.onCompleted();
			}
		});
	}

	private void generateRandomValues() {
		unsubscribe();

		this.integers.clear();
		Random random = new Random();
		for (int i = 0; i < MAX_COUNT; i++) {
			this.integers.add(random.nextInt(MAX_INT_VALUE));
		}
		this.view.showValues(this.integers);
	}

	private void unsubscribe() {
		if (this.sortingSubscription != null && !this.sortingSubscription.isUnsubscribed()) {
			this.sortingSubscription.unsubscribe();
		}
	}

	private class Swap {
		public final int from;
		public final int to;

		public Swap(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
}
