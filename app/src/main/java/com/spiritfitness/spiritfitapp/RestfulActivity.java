package com.spiritfitness.spiritfitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.spiritfitness.spiritfitapp.restfulapi.ItemList;
import com.spiritfitness.spiritfitapp.restfulapi.Itembean;
import com.spiritfitness.spiritfitapp.restfulapi.SpiritfitAPI;
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


    private   SpiritfitAPI apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restful);

        apiInterface = APIClient.getClient().create(SpiritfitAPI.class);
        setViewById();
        setLister();
    }

    private void setViewById()
    {
        btnGetItems = (Button) this.findViewById(R.id.getItem);
        btnGetItemById = (Button) this.findViewById(R.id.getItemById);
        btnPostItem = (Button) this.findViewById(R.id.postItem);
    }

    private void setLister(){

        btnGetItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Itembean>> call2 = apiInterface.doGetItemList();
                call2.enqueue(new Callback<List<Itembean>>() {
                    @Override
                    public void onResponse(Call<List<Itembean>> call, Response<List<Itembean>> response) {

                        List<Itembean> itemList = response.body();

                        //List<ItemList> datumList = itemList.items;
                        //Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                        for (Itembean bean : itemList) {
                            //Toast.makeText(getApplicationContext(), "id : " + bean.seq + " name: " + bean.SN + " " + bean.Location , Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"id : " + bean.seq + " name: " + bean.SN + " " + bean.Location);
                        }


                    }

                    @Override
                    public void onFailure(Call<List<Itembean>> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });

        btnGetItemById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Itembean> call2 = apiInterface.doGetItem(3);
                call2.enqueue(new Callback<Itembean>() {
                    @Override
                    public void onResponse(Call<Itembean> call, Response<Itembean> response) {

                        Itembean feed = response.body();
                        String sn = feed.SN;
                        String location = feed.Location;
                        Toast.makeText(getApplicationContext(), sn + " sn\n" + location + " location\n", Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onFailure(Call<Itembean> call, Throwable t) {
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
                Call<Itembean> call3 = apiInterface.doCreateItemWithField(92,"1858151709001848","051");
                call3.enqueue(new Callback<Itembean>() {
                    @Override
                    public void onResponse(Call<Itembean> call, Response<Itembean> response) {
                        Itembean itembean = response.body();
                        //Log.e(TAG, itembean.SN);
                    }

                    @Override                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
                    public void onFailure(Call<Itembean> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });
    }
}
