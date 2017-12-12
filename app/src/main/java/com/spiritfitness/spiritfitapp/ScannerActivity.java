package com.spiritfitness.spiritfitapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.spiritfitness.spiritfitapp.adapter.ContainerAdapter;
import com.spiritfitness.spiritfitapp.common.Constants;
import com.spiritfitness.spiritfitapp.model.Container;
import com.spiritfitness.spiritfitapp.adapter.ItemAdapter;
import com.spiritfitness.spiritfitapp.model.Item;
import com.spiritfitness.spiritfitapp.util.DbHelper;
import com.spiritfitness.spiritfitapp.util.LocationHelper;
import com.spiritfitness.spiritfitapp.util.XzingScannerHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.RealmResults;

public class ScannerActivity extends AppCompatActivity{
    private final String TAG = ScannerActivity.this.toString();

    private final int ACTIONBAR_MENU_ITEM_SCANNER = 0x0001;
    private final int ACTIONBAR_MENU_ITEM_SUMMIT= 0x0002;

    private String location;
    private int zoneCode;
    private int count;
    private Container container;
    private List<ItemAdapter> items;
    private boolean isEditMode = true;

    private RecyclerView recyclerView;
    private TextView itemLocation;
    private TextView itemZone;
    private TextView itemCount;
    private Menu menu;
    private CheckBox all;
    private HashMap<String, ItemAdapter> ItemAdapterMapping = new HashMap<String, ItemAdapter>();

    private int assign_type;
    //private ViewGroup scannerView;

    //private ZBarScannerView mScannerView;

    private ContainerAdapter containerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        items =  new ArrayList<>();
        findView();
        setLister();
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey(ContainerActivity.BUNDLE_CONTAINER_INFO)){
                container = (Container) bundle.getSerializable(ContainerActivity.BUNDLE_CONTAINER_INFO);
                  //location = LocationHelper.convertLocation(bundle.getString(ContainerActivity.BUNDLE_CONTAINER_LOCATION));
                //zoneCode = bundle.getInt(ContainerActivity.BUNDLE_CONTAINER_LOCATION_ZONE_CODE);
                //count = bundle.getInt(ContainerActivity.BUNDLE_CONTAINER_ITEMS_COUNT);

                //itemLocation.setText(getString(R.string.txt_container_location)+location);
                //itemZone.setText(getString(R.string.txt_container_location_code)+ zoneCode );
                //itemCount.setText(getString(R.string.txt_container_location_count)+items.size()+"/" + count);

                Log.d(TAG,container.getContainerNo());
            }else if(bundle.containsKey(Constants.Assign_TYPE))
            {
                assign_type = bundle.getInt(Constants.Assign_TYPE);

            }else
            {
                //Error!!!!
            }
        }


        if(assign_type == Constants.ASSIGN_RECEIVING) {
            containerAdapter = new ContainerAdapter(ScannerActivity.this, container.getContainerNo(),
                    items);
        }else{
            containerAdapter = new ContainerAdapter(ScannerActivity.this, "",
                    items);

        }

        recyclerView.setLayoutManager(new LinearLayoutManager(ScannerActivity.this));
        recyclerView.setAdapter(containerAdapter);

       // mScannerView = new ZBarScannerView(ScannerActivity.this);
       // scannerView.addView(mScannerView);

      //  scannerView.setVisibility(View.VISIBLE);

        XzingScannerHelper.scannerXzing(ScannerActivity.this);
    }




    @Override
    protected void onResume()
    {
        //mScannerView.setResultHandler(this);
        //mScannerView.startCamera();
        itemCount.setText(getString(R.string.txt_container_location_count)+items.size());
        itemLocation.setText(getString(R.string.txt_container_location)+ "Unknown");
        containerAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //mScannerView.stopCamera();
    }



    private void findView()
    {
        //scannerView = (ViewGroup) findViewById(R.id.content_frame);

        itemLocation = (TextView) findViewById(R.id.item_location);
        //itemZone = (TextView) findViewById(R.id.item_zone_code);
        itemCount = (TextView) findViewById(R.id.item_count);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        all = (CheckBox) findViewById(R.id.item_all);
        //mScannerView = new ZBarScannerView(ScannerActivity.this);

    }

    private void  setLister()
    {
        all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                if(isChecked) {
                    MenuItem bedMenuItem = menu.findItem(ACTIONBAR_MENU_ITEM_SCANNER);
                    bedMenuItem.setTitle((getString(R.string.menu_container_location_assign)));
                    for (int i = 0; i < items.size(); i++)
                    {
                        ItemAdapter item1 = items.get(i);
                        item1.setCheck(true);
                        items.set(i,item1);

                    }
                    containerAdapter.notifyDataSetChanged();
                }else {
                    MenuItem bedMenuItem = menu.findItem(ACTIONBAR_MENU_ITEM_SCANNER);
                    bedMenuItem.setTitle((getString(R.string.menu_container_location_add)));

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(assign_type == Constants.ASSIGN_RECEIVING) {
            MenuItem scanner = menu.add(Menu.NONE, ACTIONBAR_MENU_ITEM_SCANNER, Menu.NONE, getString(R.string.menu_container_location_add));
            SpannableString spanString = new SpannableString(scanner.getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spanString.length(), 0); //fix the color to white
            scanner.setTitle(spanString);
            scanner.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }else
        {
            MenuItem scanner = menu.add(Menu.NONE, ACTIONBAR_MENU_ITEM_SCANNER, Menu.NONE, getString(R.string.menu_container_location_assign));
            SpannableString spanString = new SpannableString(scanner.getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, spanString.length(), 0); //fix the color to white
            scanner.setTitle(spanString);
            scanner.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        }
        this.menu = menu;
        return true;
    }

    private void unloadingAssignLocation(final String strName, String title)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ScannerActivity.this);
        builder.setTitle(strName);
        builder.setMessage(title);

        // Set up the input
        final EditText input = new EditText(ScannerActivity.this);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(all.isChecked())
                {
                    for (int i = 0; i < items.size(); i++)
                    {
                        ItemAdapter item1 = items.get(i);
                        Item model = item1.getItemModel();
                        model.setLocation(LocationHelper.convertLocation(input.getText().toString()));
                        model.setZoneCoe(LocationHelper.MapZoneCode(input.getText().toString()));
                        item1.setCheck(false);
                        item1.checkbox_visible = View.GONE;
                        item1.setItemModel(model);
                        items.set(i,item1);

                    }
                    all.setVisibility(View.GONE);
                    MenuItem bedMenuItem = menu.findItem(ACTIONBAR_MENU_ITEM_SCANNER);
                    bedMenuItem.setTitle((getString(R.string.g_summit)));
                    itemLocation.setText(getString(R.string.txt_container_location) + LocationHelper.convertLocation(input.getText().toString()));
                    containerAdapter.notifyDataSetChanged();
                }

                isEditMode = false;
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  ACTIONBAR_MENU_ITEM_SCANNER:
                if(!isEditMode) {
                    //scannerXzing();
                    confirmBeforeSummit();
                }else
                {
                    unloadingAssignLocation(getString(R.string.menu_container_location_assign),getString(R.string.container_dialog_contain_location));

                }
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void setEditMode(boolean isEdit,String location)
    {
        for (int i = 0; i < items.size(); i++)
        {
            ItemAdapter item1 = items.get(i);
            Item model = item1.getItemModel();
            model.setLocation(LocationHelper.convertLocation(location));
            item1.setCheck(isEdit);
            if(isEdit)
                item1.checkbox_visible = View.VISIBLE;
            else
                item1.checkbox_visible = View.GONE;

            item1.setItemModel(model);
            items.set(i,item1);

        }
        if(isEdit)
        {
            MenuItem bedMenuItem = menu.findItem(ACTIONBAR_MENU_ITEM_SCANNER);
            bedMenuItem.setTitle((getString(R.string.menu_container_location_add)));

            all.setVisibility(View.VISIBLE);
        }else
        {
            MenuItem bedMenuItem = menu.findItem(ACTIONBAR_MENU_ITEM_SUMMIT);
            bedMenuItem.setTitle((getString(R.string.menu_container_location_assign)));

            all.setVisibility(View.GONE);
        }

        containerAdapter.notifyDataSetChanged();
    }


    private void confirmBeforeSummit()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set title
        alertDialogBuilder.setTitle(getString(R.string.menu_container_location_assign));
        // set dialog message
        alertDialogBuilder
                .setMessage(getString(R.string.scanner_dialog_confirm))
                .setCancelable(false)
                .setNegativeButton(getString(R.string.g_confirm), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        DbHelper dbHelper = new DbHelper(ScannerActivity.this);
                        //database.clearDB(AccountTreeInfo.class);

                        if(assign_type == Constants.ASSIGN_RECEIVING)
                        {
                            for (ItemAdapter itemAdapter : items) {
                                Item item1 = itemAdapter.getItemModel();
                                dbHelper.addItem(item1);
                            }
                        }else
                        {
                            for (ItemAdapter itemAdapter : items) {
                                Item item1 = itemAdapter.getItemModel();
                                dbHelper.updateItemLocation(item1.getSN(), item1.getLocation(), item1.getZoneCoe());
                            }
                        }


                    }
                }).setPositiveButton(getString(R.string.g_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                }
            })
        ;
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
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

                String sn = info.substring(contentIndex + 28,contentIndex + 44);
                if(assign_type == Constants.ASSIGN_RECEIVING)
                    receivingAssignItems(sn);
                else
                    movingAssignItems(sn);

                itemCount.setText(getString(R.string.txt_container_location_count) + items.size() + "/" + count);
                Toast.makeText(this, "Scanned: " + result.toString(), Toast.LENGTH_LONG).show();
                //if(items.size() < count)
                    XzingScannerHelper.scannerXzing(ScannerActivity.this);
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //Add items into table
    private void receivingAssignItems(String sn){

        if(!ItemAdapterMapping.containsKey(sn)) {
            ItemAdapter item = new ItemAdapter();
            Item item1 = new Item();
            item1.setID(container.getId());
            item1.setSN(sn);
            //item.setSN(rawResult.getContents());
            item1.setLocation("000");
            item1.setZoneCoe(1);
            item1.setModelNo(sn.substring(Constants.FG_MODEL_STR_START_INDEX,Constants.FG_MODEL_STR_START_INDEX+Constants.FG_MODEL_STR_LEN));
            item1.setFGDateIn(sn.substring(Constants.FG_DATE_IN_STR_START_INDEX,Constants.FG_DATE_IN_STR_START_INDEX+Constants.FG_DATE_IN_STR_LEN));
            item1.setFGSerial(sn.substring(Constants.FG_SERIAL_STR_START_INDEX,Constants.FG_SERIAL_STR_START_INDEX + Constants.FG_SERIAL_STR_LEN));

            item.setItemModel(item1);


            ItemAdapterMapping.put(sn,item);
            items.add(item);

        }
    }

    //Update items into table
    private void movingAssignItems(String sn)
    {

        DbHelper dbHelper = new DbHelper(ScannerActivity.this);
        RealmResults<Item> itemsData =  dbHelper.queryItemsList(Constants.ITEM_SN_COLUMN, sn);

        for (Item item1 : itemsData)
        {
            Item item2 = new Item();
            item2.setID(item1.getID());
            item2.setSN(item1.getSN());
            item2.setLocation(item1.getLocation());
            item2.setZoneCoe(item1.getZoneCoe());
            item2.setModelNo(item1.getModelNo());
            item2.setFGDateIn(item1.getFGDateIn());
            item2.setFGSerial(item1.getFGSerial());
            ItemAdapter itemAdapter = new ItemAdapter();
            itemAdapter.setItemModel(item2);
            items.add(itemAdapter);
        }
    }
}
