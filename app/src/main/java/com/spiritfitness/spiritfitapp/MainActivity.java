package com.spiritfitness.spiritfitapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.spiritfitness.spiritfitapp.common.Constants;
import com.spiritfitness.spiritfitapp.adapter.ItemAdapter;
import com.spiritfitness.spiritfitapp.model.Item;
import com.spiritfitness.spiritfitapp.util.FileHelper;
import com.spiritfitness.spiritfitapp.util.TestContainHelper;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private Button receiving;
    private Button moving;
    private Button inquiry;
    private Button shipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String filePath = Environment.getExternalStorageDirectory()+ Constants.SDACRD_DIR_APP_ROOT;
        FileHelper.checkSdCard(filePath);// 檢查S是否有 SD卡,並建立會用到的 SD卡路徑

        checkStoragePermissions(this);


        TestContainHelper.inital(MainActivity.this, Item.class);

        receiving = (Button) this.findViewById(R.id.receiving);
        receiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(MainActivity.this, ContainerActivity.class);
                startActivity(page);
                //finish();
            }
        });

        moving = (Button) this.findViewById(R.id.moving);
        moving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(MainActivity.this, ScannerActivity.class);
                Bundle flag = new Bundle();
                flag.putInt(Constants.Assign_TYPE, Constants.ASSIGN_MOVING);
                page.putExtras(flag);
                startActivity(page);
                //finish();
            }
        });
        inquiry = (Button) this.findViewById(R.id.inquiry);
        inquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayQueryOption();
            }
        });
        shipping = (Button) this.findViewById(R.id.shipping);
        shipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page = new Intent(MainActivity.this, ContainerActivity.class);
                Bundle flag = new Bundle();
                //flag.putInt(Constants.ARG_POSITION, Constants.DEPARTURE_QUERY_BOOKMARK);
                //page.putExtras(flag);
                //startActivityForResult(page,Constants.DEPARTURE_QUERY_BOOKMARK);
                //page.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(page);
                //finish();
            }
        });
    }

    private void displayQueryOption()
    {
        final String[] unloadingOption = getResources().getStringArray(R.array.inquiry_menu);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set title
        alertDialogBuilder.setTitle(getString(R.string.menu_inquiry));

        final ArrayAdapter adapterUnloading = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                unloadingOption);


        alertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialogBuilder.setAdapter(adapterUnloading, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String strName = unloadingOption[which];

                Intent page = new Intent(MainActivity.this, InQuiryActivity.class);
                Bundle flag = new Bundle();
                flag.putInt(Constants.INQUIRY_TYPE, which);
                page.putExtras(flag);
                //startActivityForResult(page,Constants.DEPARTURE_QUERY_BOOKMARK);
                //page.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(page);


            }
        });
        alertDialogBuilder.show();
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

                ItemAdapter item = new ItemAdapter();
                Item model = item.getItemModel();
                model.setSN(info.substring(contentIndex + 28,contentIndex + 44));

                item.setItemModel(model);
                Toast.makeText(this, "Scanned: " + result.toString(), Toast.LENGTH_LONG).show();
                //if(items.size() < count)
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void checkStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    String filePath = Environment.getExternalStorageDirectory()+Constants.SDACRD_DIR_APP_ROOT;
                    FileHelper.checkSdCard(filePath);// 檢查S是否有 SD卡,並建立會用到的 SD卡路徑

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }


}
