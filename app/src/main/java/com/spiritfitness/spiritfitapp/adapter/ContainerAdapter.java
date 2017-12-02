package com.spiritfitness.spiritfitapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.spiritfitness.spiritfitapp.R;
import com.spiritfitness.spiritfitapp.model.Item;

import java.util.List;

/**
 * Created by geminihsu on 30/11/2017.
 */

public class ContainerAdapter extends RecyclerView.Adapter<ContainerAdapter.ViewHolder> {

    private Context mContext;
    private List<ItemAdapter> container;
    private String containerNo;

    public ContainerAdapter(Context context,String _containerNo, List<ItemAdapter> data) {
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

        holder.checkBox = (CheckBox) view.findViewById(R.id.checkAll);
        holder.tvContainerNo = (TextView) view.findViewById(R.id.tvContent);
        holder.tvSerial = (TextView) view.findViewById(R.id.txtSerialNo);
        holder.tvLocation = (TextView) view.findViewById(R.id.txtLocation);
        holder.tvZoneCode = (TextView) view.findViewById(R.id.txtZoneCode);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemAdapter item = container.get(position);
        Item item1 = item.getItemModel();
        holder.checkBox.setVisibility(item.checkbox_visible);
        holder.checkBox.setChecked(item.isCheck());
        holder.tvContainerNo .setText(containerNo);
        holder.tvSerial.setText(item1.getSN());
        holder.tvLocation.setText(item1.getLocation());
        holder.tvZoneCode.setText(String.valueOf(item1.getZoneCoe()));
    }


    @Override
    public int getItemCount() {
        return container.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox checkBox;
        public TextView tvContainerNo;
        public TextView tvSerial;
        public TextView tvLocation;
        public TextView tvZoneCode;



        public ViewHolder(View itemView) {
            super(itemView);
        }


    }


}

