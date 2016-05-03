package com.yud.sortinganimator.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yud.sortinganimator.R;

import java.util.List;

/**
 * Created by yurik on 5/2/16.
 */
public class ValuesAdapter extends RecyclerView.Adapter<ValuesAdapter.ValuesViewHolder> {
	private List<Integer> values;

	public ValuesAdapter(List<Integer> values) {
		this.values = values;
	}

	@Override
	public ValuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card, parent, false);
		return new ValuesViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ValuesViewHolder holder, int position) {
		holder.tvValue.setText(String.valueOf(this.values.get(position)));
	}

	@Override
	public int getItemCount() {
		return this.values.size();
	}

	public class ValuesViewHolder extends RecyclerView.ViewHolder {
		public TextView tvValue;

		public ValuesViewHolder(View view) {
			super(view);
			this.tvValue = (TextView) view.findViewById(R.id.tvValue);
		}
	}
}
