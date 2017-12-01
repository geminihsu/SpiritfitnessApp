package com.spiritfitness.spiritfitapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spiritfitness.spiritfitapp.R;
import com.spiritfitness.spiritfitapp.model.Container;
import com.spiritfitness.spiritfitapp.model.Item;

import java.util.List;

/**
 * Created by geminihsu on 30/11/2017.
 */

public class ContainerAdapter extends RecyclerView.Adapter<ContainerAdapter.ViewHolder> {

    private Context mContext;
    private List<Item> container;
    private String containerNo;

    public ContainerAdapter(Context context,String _containerNo, List<Item> data) {
        this.mContext = context;
        this.container = data;
        this.containerNo = _containerNo;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.container_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.tvContainerNo = (TextView) view.findViewById(R.id.tvContent);
        holder.tvSerial = (TextView) view.findViewById(R.id.txtSerialNo);
        holder.tvLocation = (TextView) view.findViewById(R.id.txtLocation);
        holder.tvZoneCode = (TextView) view.findViewById(R.id.txtZoneCode);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = container.get(position);
        holder.tvContainerNo .setText(containerNo);
        holder.tvSerial.setText(item.getSN());
        holder.tvLocation.setText(item.getLocation());
        holder.tvZoneCode.setText(String.valueOf(item.getZoneCoe()));
    }


    @Override
    public int getItemCount() {
        return container.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvContainerNo;
        public TextView tvSerial;
        public TextView tvLocation;
        public TextView tvZoneCode;



        public ViewHolder(View itemView) {
            super(itemView);
        }


    }


}

