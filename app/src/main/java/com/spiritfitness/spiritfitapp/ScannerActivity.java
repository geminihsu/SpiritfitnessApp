package com.spiritfitness.spiritfitapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.spiritfitness.spiritfitapp.adapter.ContainerAdapter;
import com.spiritfitness.spiritfitapp.model.Container;
import com.spiritfitness.spiritfitapp.model.Item;
import com.spiritfitness.spiritfitapp.util.LocationHelper;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScannerActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler{
    private final String TAG = ScannerActivity.this.toString();

    private final int ACTIONBAR_MENU_ITEM_SUMMIT = 0x0001;

    private String location;
    private int zoneCode;
    private int count;
    private Container container;
    private List<Item> items;

    private RecyclerView recyclerView;
    private TextView itemLocation;
    private TextView itemZone;
    private TextView itemCount;
    private ViewGroup scannerView;

    private IntentIntegrator integrator;
    private ZBarScannerView mScannerView;

    private ContainerAdapter containerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        items =  new ArrayList<>();
        findView();
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey(ContainerActivity.BUNDLE_CONTAINER_INFO)){
                container = (Container) bundle.getSerializable(ContainerActivity.BUNDLE_CONTAINER_INFO);
                location = LocationHelper.convertLocation(bundle.getString(ContainerActivity.BUNDLE_CONTAINER_LOCATION));
                zoneCode = bundle.getInt(ContainerActivity.BUNDLE_CONTAINER_LOCATION_ZONE_CODE);
                count = bundle.getInt(ContainerActivity.BUNDLE_CONTAINER_ITEMS_COUNT);

                itemLocation.setText(getString(R.string.txt_container_location)+location);
                itemZone.setText(getString(R.string.txt_container_location_code)+ zoneCode );
                itemCount.setText(getString(R.string.txt_container_location_count)+items.size()+"/" + count);

                Log.d(TAG,container.getContainerNo());
            }else
            {
                //Error!!!!
            }
        }
        containerAdapter = new ContainerAdapter(ScannerActivity.this, container.getContainerNo(), items);

        recyclerView.setLayoutManager(new LinearLayoutManager(ScannerActivity.this));
        recyclerView.setAdapter(containerAdapter);

       // mScannerView = new ZBarScannerView(ScannerActivity.this);
       // scannerView.addView(mScannerView);

      //  scannerView.setVisibility(View.VISIBLE);
        scannerXzing();
    }

    private void scannerXzing() {
        IntentIntegrator integrator = new IntentIntegrator(ScannerActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

    }


    @Override
    protected void onResume()
    {
        mScannerView.setResultHandler(this);
        //mScannerView.startCamera();

        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //mScannerView.stopCamera();
    }



    private void findView()
    {
        scannerView = (ViewGroup) findViewById(R.id.content_frame);

        itemLocation = (TextView) findViewById(R.id.item_location);
        itemZone = (TextView) findViewById(R.id.item_zone_code);
        itemCount = (TextView) findViewById(R.id.item_count);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mScannerView = new ZBarScannerView(ScannerActivity.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem item = menu.add(Menu.NONE, ACTIONBAR_MENU_ITEM_SUMMIT, Menu.NONE, getString(R.string.g_add));
        SpannableString spanString = new SpannableString(item.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spanString.length(), 0); //fix the color to white
        item.setTitle(spanString);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  ACTIONBAR_MENU_ITEM_SUMMIT:

                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {

                String info = result.toString();
                int contentIndex = info.indexOf("containers");

                Item item = new Item();
                item.setSN(info.substring(contentIndex + 28,contentIndex + 42));
                //item.setSN(rawResult.getContents());
                item.setLocation(location);
                item.setZoneCoe(zoneCode);

                items.add(item);
                containerAdapter.notifyItemRangeChanged(items.size()-1, items.size());
                itemCount.setText(getString(R.string.txt_container_location_count)+items.size()+"/" + count);

                Toast.makeText(this, "Scanned: " + result.toString(), Toast.LENGTH_LONG).show();
                if(items.size() < count)
                    scannerXzing();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(this, "Contents = " + rawResult.getContents() +
                ", Format = " + rawResult.getBarcodeFormat().getName(), Toast.LENGTH_SHORT).show();


        String info = rawResult.getContents();
        int contentIndex = info.indexOf("containers");

        Item item = new Item();
        //item.setSN(info.substring(contentIndex + 28,contentIndex + 42));
        item.setSN(rawResult.getContents());
        item.setLocation(location);
        item.setZoneCoe(zoneCode);

        items.add(item);
        containerAdapter.notifyItemRangeChanged(items.size()-1, items.size());
        itemCount.setText(getString(R.string.txt_container_location_count)+items.size()+"/" + count);

        mScannerView.stopCamera();
        scannerView.setVisibility(View.GONE);
            // Note:
            // * Wait 2 seconds to resume the preview.
            // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
            // * I don't know why this is the case but I don't have the time to figure out.
         /*   Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    mScannerView.resumeCameraPreview(ScannerActivity.this);
                }
            }, 2000);*/

    }
}
