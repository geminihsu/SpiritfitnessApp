package com.spiritfitness.spiritfitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.spiritfitness.spiritfitapp.common.Constants;
import com.spiritfitness.spiritfitapp.adapter.ContainerAdapter;
import com.spiritfitness.spiritfitapp.adapter.ItemAdapter;
import com.spiritfitness.spiritfitapp.delegate.query.InQuiryViewDelegateBase;
import com.spiritfitness.spiritfitapp.delegate.query.location.InQuiryViewDelegateLocation;
import com.spiritfitness.spiritfitapp.delegate.query.model.InQuiryViewDelegateModel;

import java.util.ArrayList;
import java.util.List;

public class InQuiryActivity extends AppCompatActivity {

    private InQuiryViewDelegateBase inQuiryViewDelegateBase;
    private ContainerAdapter containerAdapter;

    private RecyclerView recyclerView;
    private TextView itemLocation;
    private TextView itemZone;
    private TextView itemCount;

    private List<ItemAdapter> items;
    //private LinearLayout linearLayout_zone_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_quiry);
        findView();
        items = new ArrayList<>();

        containerAdapter = new ContainerAdapter(InQuiryActivity.this, "",
                items);

        recyclerView.setLayoutManager(new LinearLayoutManager(InQuiryActivity.this));
        recyclerView.setAdapter(containerAdapter);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey(Constants.INQUIRY_TYPE)) {
                if(bundle.getInt(Constants.INQUIRY_TYPE) == Constants.INQUIRY_MODEL)
                {
                    inQuiryViewDelegateBase = new InQuiryViewDelegateModel(this,containerAdapter,items,itemLocation,itemCount);
                    //linearLayout_zone_info.setVisibility(View.VISIBLE);

                }else{
                    inQuiryViewDelegateBase = new InQuiryViewDelegateLocation(this,containerAdapter,items,itemLocation,itemCount);
                    //linearLayout_zone_info.setVisibility(View.GONE);
                }
            }

            inQuiryViewDelegateBase.setInitialActivity();
        }


    }

    private void findView()
    {
        itemLocation = (TextView) findViewById(R.id.item_query_title);
        //itemZone = (TextView) findViewById(R.id.item_zone_title);
        itemCount = (TextView) findViewById(R.id.item_quantity);

        //linearLayout_zone_info = (LinearLayout) findViewById(R.id.zone_info);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                inQuiryViewDelegateBase.queryBySN(result);

            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
