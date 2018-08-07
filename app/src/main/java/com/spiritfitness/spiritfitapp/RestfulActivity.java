package com.spiritfitness.spiritfitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.spiritfitness.spiritfitapp.model.PickUpZoneMap;
import com.spiritfitness.spiritfitapp.restful.callback.ModelZoneMapCallback;
import com.spiritfitness.spiritfitapp.restfulapi.ZoneItemInfo;
import com.spiritfitness.spiritfitapp.restful.callback.InventoryCallback;
import com.spiritfitness.spiritfitapp.restfulapi.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RestfulActivity extends AppCompatActivity {
    private final String TAG = RestfulActivity.class.getSimpleName();

    private Button btnGetItems;
    private Button btnGetModels;
    private Button btnGetLocations;

    private Button btnGetItemById;
    private Button btnPostItem;
    private Button btnPutItem;
    private Button btnDeteleItem;
    private Button btnPickUpItem;


    private InventoryCallback apiInterface;
    private ModelZoneMapCallback modelZoneMapCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restful);

        apiInterface = APIClient.getClient().create(InventoryCallback.class);
        modelZoneMapCallback= APIClient.getClient().create(ModelZoneMapCallback.class);
        setViewById();
        setLister();
    }

    private void setViewById()
    {
        btnGetItems = (Button) this.findViewById(R.id.getItem);
        btnGetItemById = (Button) this.findViewById(R.id.getItemById);
        btnPostItem = (Button) this.findViewById(R.id.postItem);
        btnPutItem = (Button) this.findViewById(R.id.putItem);
        btnDeteleItem = (Button) this.findViewById(R.id.deleteItem);
        btnPickUpItem = (Button) this.findViewById(R.id.pickupItem);
    }

    private void setLister(){

        btnGetItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<ZoneItemInfo>> call2 = apiInterface.doGetItemList();
                call2.enqueue(new Callback<List<ZoneItemInfo>>() {
                    @Override
                    public void onResponse(Call<List<ZoneItemInfo>> call, Response<List<ZoneItemInfo>> response) {

                        List<ZoneItemInfo> itemList = response.body();

                        //List<ItemList> datumList = itemList.items;
                        //Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                        for (ZoneItemInfo bean : itemList) {
                            //Toast.makeText(getApplicationContext(), "id : " + bean.seq + " name: " + bean.SN + " " + bean.Location , Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"id : " + bean.seq + " name: " + bean.SN + " " + bean.Location);
                        }


                    }

                    @Override
                    public void onFailure(Call<List<ZoneItemInfo>> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });

        btnGetItemById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ZoneItemInfo> call2 = apiInterface.doGetItem(3);
                call2.enqueue(new Callback<ZoneItemInfo>() {
                    @Override
                    public void onResponse(Call<ZoneItemInfo> call, Response<ZoneItemInfo> response) {

                        ZoneItemInfo feed = response.body();
                        String sn = feed.SN;
                        String location = feed.Location;
                        Toast.makeText(getApplicationContext(), sn + " sn\n" + location + " location\n", Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onFailure(Call<ZoneItemInfo> call, Throwable t) {
                        call.cancel();
                    }
                });

            }
        });

        btnPostItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 POST name and job Url encoded.
                 **/
                Call<ZoneItemInfo> call3 = apiInterface.doCreateItemWithField(92,"1858151709001848","2017-12-19 13:16:53.363", "051","185815");
                call3.enqueue(new Callback<ZoneItemInfo>() {
                    @Override
                    public void onResponse(Call<ZoneItemInfo> call, Response<ZoneItemInfo> response) {
                        ZoneItemInfo zoneItemInfo = response.body();
                        //Log.e(TAG, itembean.SN);
                    }

                    @Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
                    public void onFailure(Call<ZoneItemInfo> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });

        btnDeteleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ZoneItemInfo> call2 = apiInterface.doDeleteItem(92);
                call2.enqueue(new Callback<ZoneItemInfo>() {
                    @Override
                    public void onResponse(Call<ZoneItemInfo> call, Response<ZoneItemInfo> response) {

                        ZoneItemInfo feed = response.body();
                        String sn = feed.SN;
                        String location = feed.Location;
                        Toast.makeText(getApplicationContext(), sn + " sn\n" + location + " location\n", Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onFailure(Call<ZoneItemInfo> call, Throwable t) {
                        call.cancel();
                    }
                });

            }
        });

        btnPutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 PUT name and job Url encoded.
                 **/
                Call<ZoneItemInfo> call3 = apiInterface.doUpdateItemWithField(92,92,"1858151709001849","2017-12-19 13:16:53.363", "062","185815");
                call3.enqueue(new Callback<ZoneItemInfo>() {
                    @Override
                    public void onResponse(Call<ZoneItemInfo> call, Response<ZoneItemInfo> response) {
                        ZoneItemInfo zoneItemInfo = response.body();
                        //Log.e(TAG, itembean.SN);
                    }

                    @Override
                    public void onFailure(Call<ZoneItemInfo> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });

        btnPickUpItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 PUT name and job Url encoded.
                 **/
                Call<List<PickUpZoneMap>> call3 = modelZoneMapCallback.getPickUpByZone(2);
                call3.enqueue(new Callback<List<PickUpZoneMap>>() {
                    @Override
                    public void onResponse(Call<List<PickUpZoneMap>> call, Response<List<PickUpZoneMap>> response) {
                        List<PickUpZoneMap> itemList = response.body();
                        for (PickUpZoneMap bean : itemList) {
                            //Toast.makeText(getApplicationContext(), "id : " + bean.seq + " name: " + bean.SN + " " + bean.Location , Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"Model no. : " + bean.ModelNo + " location: " + bean.Location + " " + bean.Location);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PickUpZoneMap>> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });
    }
}
