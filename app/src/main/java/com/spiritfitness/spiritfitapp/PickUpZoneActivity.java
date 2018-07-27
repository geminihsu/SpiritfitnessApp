package com.spiritfitness.spiritfitapp;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.spiritfitness.spiritfitapp.adapter.PickupZoneRecyclerViewAdapter;
import com.spiritfitness.spiritfitapp.model.PickUpZoneMap;
import com.spiritfitness.spiritfitapp.restful.callback.ModelZoneMapCallback;
import com.spiritfitness.spiritfitapp.restfulapi.APIClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickUpZoneActivity extends AppCompatActivity {

    private Timer mTimer;
    private ModelZoneMapCallback modelZoneMapCallback;
   // private ListView listView;
    private PickupZoneRecyclerViewAdapter pickupZoneRecyclerViewAdapter;
    private List<PickUpZoneMap> pickupData;

    private ProgressDialog progressDialog_loading;
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


        mTimer = new Timer();
        // start timer task
        setTimerTask();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setTimerTask() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                Log.e("Pickup","Updated !!");
                doActionHandler.sendMessage(message);
            }
        }, 1000, 600*1000/* 表示1000毫秒之後，每隔1000毫秒執行一次 */);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(progressDialog_loading!=null) {
            progressDialog_loading.dismiss();
            progressDialog_loading =null;
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(progressDialog_loading!=null) {
            progressDialog_loading.dismiss();
            progressDialog_loading =null;
        }
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
                if(progressDialog_loading!=null) {
                    progressDialog_loading.dismiss();
                    progressDialog_loading=null;
                }
                pickupZoneRecyclerViewAdapter = new PickupZoneRecyclerViewAdapter(PickUpZoneActivity.this, pickupData);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(PickUpZoneActivity.this));
                mRecyclerView.setAdapter(pickupZoneRecyclerViewAdapter);
               // normalRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PickUpZoneMap>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private Handler doActionHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgId = msg.what;
            switch (msgId) {
                case 1:
                    if (progressDialog_loading==null) {
                        progressDialog_loading = ProgressDialog.show(PickUpZoneActivity.this, "",
                                "Loading. Please wait...", true);
                    }
                    getPickupZone();
                    break;
                default:
                    break;
            }
        }
    };




}
