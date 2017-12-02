package com.spiritfitness.spiritfitapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.spiritfitness.spiritfitapp.adapter.ItemAdapter;
import com.spiritfitness.spiritfitapp.model.Item;
import com.spiritfitness.spiritfitapp.util.ScannerHelper;

public class MainActivity extends AppCompatActivity {

    private Button receiving;
    private Button moving;
    private Button inquiry;
    private Button shipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiving = (Button) this.findViewById(R.id.receiving);
        receiving.setOnClickListener(new View.OnClickListener() {
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

        moving = (Button) this.findViewById(R.id.moving);
        moving.setOnClickListener(new View.OnClickListener() {
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

                switch (which)
                {
                    case 0:
                        ScannerHelper.scannerXzing(MainActivity.this);
                        break;

                    case 1:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle(getString(R.string.inquiry_location));

                        // Set up the input
                        final EditText input = new EditText(MainActivity.this);

                        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder.setView(input);

                        // Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();
                        break;
                }


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
}
