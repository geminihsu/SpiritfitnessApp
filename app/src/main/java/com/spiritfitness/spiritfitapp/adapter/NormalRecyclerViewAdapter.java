package com.spiritfitness.spiritfitapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spiritfitness.spiritfitapp.R;
import com.spiritfitness.spiritfitapp.model.PickUpZoneMap;

import java.util.List;

import butterknife.ButterKnife;

public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {

    private final Context mContext;
    private List<PickUpZoneMap> mTitles;

    public NormalRecyclerViewAdapter(Context context,List<PickUpZoneMap> _mTitles) {
        mTitles = _mTitles;
        mContext = context;

    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.zonelist_item_border, parent, false);
        NormalTextViewHolder holder = new NormalTextViewHolder(view);

        holder.modelNo = (TextView) view.findViewById(R.id.modelNo);
        holder.location = (TextView) view.findViewById(R.id.location);
        holder.qty = (TextView) view.findViewById(R.id.qty);

        return holder;

    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.modelNo.setText(mTitles.get(position).ModelNo);
        holder.location.setText(mTitles.get(position).Location);
        if(mTitles.get(position).Qty!=-1)
            holder.qty.setText(String.valueOf(mTitles.get(position).Qty));
        else
            holder.qty.setText("Quantity");
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    public class NormalTextViewHolder extends RecyclerView.ViewHolder {

        TextView modelNo;
        TextView location;
        TextView qty;

        public NormalTextViewHolder(View itemView) {
            super(itemView);

        }
    }
}
