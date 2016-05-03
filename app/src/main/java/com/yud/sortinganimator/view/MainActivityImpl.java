package com.yud.sortinganimator.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.yud.sortinganimator.R;
import com.yud.sortinganimator.common.Action;
import com.yud.sortinganimator.common.SortAlgorithms;
import com.yud.sortinganimator.presenter.MainActivityPresenter;
import com.yud.sortinganimator.presenter.MainActivityPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivityImpl extends BaseActivity<MainActivityPresenter> implements MainActivity {
	@InjectView(R.id.toolbar)
	private Toolbar toolbar;

	@InjectView(R.id.rvValues)
	private RecyclerView rvValues;

	@InjectView(R.id.spnSortAlgorithm)
	private Spinner spnSortAlgorithm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setPresenter(new MainActivityPresenterImpl(this));


		setSupportActionBar(this.toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		initSpinner();

		findViewById(R.id.fabGenerate).setOnClickListener(v -> this.presenter.onGenerateClick());
		findViewById(R.id.fabSort).setOnClickListener(v -> this.presenter.onSortClick());

		this.rvValues.setLayoutManager(new GridLayoutManager(this, 5));
		this.presenter.onViewCreated(this);
	}

	private void initSpinner() {
		List<SortAlgorithm> algorithms = new ArrayList<>();

		algorithms.add(new SortAlgorithm(R.string.bubble_sort, () -> this.presenter.onAlgorithmSelected(SortAlgorithms.BUBBLE)));
		algorithms.add(new SortAlgorithm(R.string.selection_sort, () -> this.presenter.onAlgorithmSelected(SortAlgorithms.SELECTION)));
		algorithms.add(new SortAlgorithm(R.string.quick_sort, () -> this.presenter.onAlgorithmSelected(SortAlgorithms.QUICK)));

		this.spnSortAlgorithm.setAdapter(new SortSpinnerAdapter(this, algorithms));

		this.spnSortAlgorithm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				algorithms.get(position).action.doAction();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	@Override
	public void showValues(List<Integer> integers) {
		this.rvValues.setAdapter(new ValuesAdapter(integers));
	}

	@Override
	public void swapItems(int from, int to) {
//		Log.e("M", String.format("%s-%s, %d %d ", getTextFromGrid(from), getTextFromGrid(to), from, to));
		this.rvValues.getAdapter().notifyItemMoved(from, to);
		this.rvValues.getAdapter().notifyItemMoved(to - 1, from);
	}

	private String getTextFromGrid(int position) {
		return ((TextView) rvValues.getChildAt(position).findViewById(R.id.tvValue)).getText().toString();
	}

	@Override
	public void showCongratulation() {
		Snackbar.make(this.rvValues, R.string.done, Snackbar.LENGTH_SHORT).show();
	}


	private class SortAlgorithm {
		public final Action action;

		@StringRes
		public final int title;

		public SortAlgorithm(int title, Action action) {
			this.action = action;
			this.title = title;
		}
	}

	private class SortSpinnerAdapter extends ArrayAdapter<SortAlgorithm> {
		private static final String NON_DROPDOWN_TAG = "NON_DROPDOWN";
		private static final String DROPDOWN_TAG = "DROPDOWN";

		public SortSpinnerAdapter(Context context, List<SortAlgorithm> objects) {
			super(context, 0, objects);
		}

		@Override
		public View getDropDownView(int position, View view, ViewGroup parent) {
			if (view == null || !view.getTag().toString().equals(DROPDOWN_TAG)) {
				view = getLayoutInflater().inflate(R.layout.view_spinner_dropdown, parent, false);
				view.setTag(DROPDOWN_TAG);
			}

			TextView textView = (TextView) view.findViewById(android.R.id.text1);
			textView.setText(getItem(position).title);

			return view;
		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			if (view == null || !view.getTag().toString().equals(NON_DROPDOWN_TAG)) {
				view = getLayoutInflater().inflate(R.layout.view_spinner_item, parent, false);
				view.setTag(NON_DROPDOWN_TAG);
			}
			TextView textView = (TextView) view.findViewById(android.R.id.text1);
			textView.setText(getItem(position).title);
			return view;
		}
	}
}
