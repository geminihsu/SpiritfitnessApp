package com.spiritfitness.spiritfitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.spiritfitness.spiritfitapp.adapter.NormalRecyclerViewAdapter;
import com.spiritfitness.spiritfitapp.adapter.PickUpZoneAdapter;
import com.spiritfitness.spiritfitapp.common.Constants;
import com.spiritfitness.spiritfitapp.model.PickUpZoneMap;
import com.spiritfitness.spiritfitapp.restful.callback.ModelZoneMapCallback;
import com.spiritfitness.spiritfitapp.restfulapi.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickUpZoneActivity extends AppCompatActivity {

    private ModelZoneMapCallback modelZoneMapCallback;
   // private ListView listView;
    private PickUpZoneAdapter pickUpZoneAdapter;
    private NormalRecyclerViewAdapter normalRecyclerViewAdapter;
    private List<PickUpZoneMap> pickupData;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_zone);

        pickupData = new  ArrayList<PickUpZoneMap>();
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
       // listView = (ListView) findViewById(R.id.listView1);

      //  pickUpZoneAdapter=new PickUpZoneAdapter(PickUpZoneActivity.this, 0, pickupData);
       // listView.setAdapter(pickUpZoneAdapter);

        modelZoneMapCallback= APIClient.getClient().create(ModelZoneMapCallback.class);


        getPickupZone();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void getPickupZone()
    {
        pickupData.clear();
        Call<List<PickUpZoneMap>> call3 = modelZoneMapCallback.getPickUpByZone(2);
        call3.enqueue(new Callback<List<PickUpZoneMap>>() {
            @Override
            public void onResponse(Call<List<PickUpZoneMap>> call, Response<List<PickUpZoneMap>> response) {
                List<PickUpZoneMap> itemList = response.body();
                PickUpZoneMap head = new PickUpZoneMap();
                head.ModelNo = "ModelNo#";
                head.Location = "Location";
                head.Qty = -1;
                itemList.add(0,head);
                int i = 0;

                for (PickUpZoneMap bean : itemList) {
                    //Toast.makeText(getApplicationContext(), "id : " + bean.seq + " name: " + bean.SN + " " + bean.Location , Toast.LENGTH_SHORT).show();
                    Log.e("","Model no. : " + bean.ModelNo + " location: " + bean.Location + "Qty: " + bean.Qty);
                    pickupData.add(bean);
                }
                //listView.setAdapter(pickUpZoneAdapter);
                normalRecyclerViewAdapter = new NormalRecyclerViewAdapter(PickUpZoneActivity.this, pickupData);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(PickUpZoneActivity.this));
                mRecyclerView.setAdapter(normalRecyclerViewAdapter);
               // normalRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PickUpZoneMap>> call, Throwable t) {
                call.cancel();
            }
        });
    }



}
