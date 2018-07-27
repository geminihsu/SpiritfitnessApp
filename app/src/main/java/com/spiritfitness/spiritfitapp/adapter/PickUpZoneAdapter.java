package com.spiritfitness.spiritfitapp.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spiritfitness.spiritfitapp.R;
import com.spiritfitness.spiritfitapp.model.PickUpZoneMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PickUpZoneAdapter extends ArrayAdapter<PickUpZoneMap> {

	public List<PickUpZoneMap> list;


	private LayoutInflater mInflater;


	public PickUpZoneAdapter(Context _context,
								  int rid, List<PickUpZoneMap> list) {
		super(_context, rid, list);
		mInflater = (LayoutInflater)_context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


	}

	public View getView(int position,
						View convertView, ViewGroup parent) {


		// 取出資料
		final PickUpZoneMap item = (PickUpZoneMap)getItem(position);
		ViewHolder holder;
		if(convertView == null)
		{
			holder = new ViewHolder();
			// 以layout檔案產生View
			convertView = mInflater.inflate(R.layout.zonelist_item, null);
			//DisplayUtil displayUtil = new DisplayUtil();
			//displayUtil.setFontSize(view, context.getResources().getDimension(R.dimen.default_text_size_px));
			// 設定尊稱
			holder.modelNo = (TextView)convertView.findViewById(R.id.modelNo);
			holder.location = (TextView)convertView.findViewById(R.id.location);
			holder.qty = (TextView)convertView.findViewById(R.id.qty);


			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();

			holder.modelNo.setText(item.ModelNo);
			holder.location.setText(item.Location);

			if(item.Qty != -1)
				holder.qty.setText(String.valueOf(item.Qty));
			else
				holder.qty.setText("Quantity");


		}


		return convertView;
	}

	private class ViewHolder {
		TextView modelNo;
		TextView location;
		TextView qty;

	}


}
