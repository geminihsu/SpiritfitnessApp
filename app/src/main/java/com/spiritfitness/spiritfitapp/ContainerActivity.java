package com.spiritfitness.spiritfitapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.spiritfitness.spiritfitapp.common.Constants;
import com.spiritfitness.spiritfitapp.model.Container;
import com.spiritfitness.spiritfitapp.util.LocationHelper;


public class ContainerActivity extends AppCompatActivity {

    public final static String BUNDLE_CONTAINER_INFO = "container";// from
    public final static String BUNDLE_CONTAINER_LOCATION = "container_location";// from
    public final static String BUNDLE_CONTAINER_LOCATION_ZONE_CODE = "container_zonecode";// from
    public final static String BUNDLE_CONTAINER_ITEMS_COUNT = "container_items_count";// from


    private Button scanner;

    private EditText container_no;
    private EditText invoice_no;
    private EditText received_info;
    private EditText received_date;
    private EditText received_worker;

    private EditText entered_cs;
    private EditText entered_pt;

    private Container container;

    private String location;
    private int zoneCode;
    private int itemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        scanner = (Button) findViewById(R.id.btn_scanner);
        container_no = (EditText) findViewById(R.id.container_no);
        invoice_no = (EditText) findViewById(R.id.invoive_no);
        received_info = (EditText) findViewById(R.id.received_info);
        received_date = (EditText) findViewById(R.id.received_date);
        received_worker = (EditText) findViewById(R.id.received_worker_mail);
        entered_cs = (EditText) findViewById(R.id.entered_cs);
        entered_pt = (EditText) findViewById(R.id.entered_pt);

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startUnloading();

            }
        });
    }

    private void startUnloading()
    {
        container = new Container();
        container.setContainerNo(container_no.getText().toString());
        container.setInvoiceNo(invoice_no.getText().toString());
        container.setPiceRecevid(received_info.getText().toString());
        container.setRecevidDate(received_date.getText().toString());
        container.setWorkerInfo(received_worker.getText().toString());
        container.setEnteredCS(entered_cs.getText().toString());
        container.setEnteredPT(entered_pt.getText().toString());


        final String[] unloadingOption = getResources().getStringArray(R.array.unloading_menu);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set title
        alertDialogBuilder.setTitle(getString(R.string.container_dialog_title));

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

                        //unloadingAssignLocation(strName,getString(R.string.container_dialog_contain_location),false);
                        Intent page = new Intent(ContainerActivity.this, ScannerActivity.class);
                        Bundle flag = new Bundle();
                        flag.putSerializable(BUNDLE_CONTAINER_INFO, container);
                        flag.putInt(Constants.Assign_TYPE, Constants.ASSIGN_RECEIVING);
                        page.putExtras(flag);
                        //startActivityForResult(page,Constants.DEPARTURE_QUERY_BOOKMARK);
                        //page.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(page);
                        break;

                    case 1:

                        break;
                }


            }
        });
        alertDialogBuilder.show();
    }




}
