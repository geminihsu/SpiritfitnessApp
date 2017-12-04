package com.spiritfitness.spiritfitapp.delegate.query.model;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.decoder.Mode;
import com.spiritfitness.spiritfitapp.common.Constants;
import com.spiritfitness.spiritfitapp.InQuiryActivity;
import com.spiritfitness.spiritfitapp.R;
import com.spiritfitness.spiritfitapp.adapter.ContainerAdapter;
import com.spiritfitness.spiritfitapp.adapter.ItemAdapter;
import com.spiritfitness.spiritfitapp.delegate.query.InQuiryViewDelegateBase;
import com.spiritfitness.spiritfitapp.model.Container;
import com.spiritfitness.spiritfitapp.model.FGModel;
import com.spiritfitness.spiritfitapp.model.Item;
import com.spiritfitness.spiritfitapp.util.DbHelper;
import com.spiritfitness.spiritfitapp.util.LocationHelper;
import com.spiritfitness.spiritfitapp.util.ScannerHelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmResults;

/**
 * Created by geminihsu on 02/12/2017.
 */

public class InQuiryViewDelegateModel extends InQuiryViewDelegateBase {
    private final String TAG= this.getClass().getSimpleName();

    LinkedHashMap<String, Integer> modelQuantityMap = new LinkedHashMap<String, Integer>();


    public InQuiryViewDelegateModel(InQuiryActivity mainActivity,ContainerAdapter _containerAdapter,List<ItemAdapter> _itemAdapters,TextView _queryByTitle, TextView _queryQuantity) {
        super(mainActivity,_containerAdapter,_itemAdapters,_queryByTitle,  _queryQuantity);

    }

    @Override
    public void setInitialActivity() {
        ScannerHelper.scannerXzing(inQuiryActivity);
        queryByTitle.setText(inQuiryActivity.getString(R.string.txt_inquiry_items_location));
        queryQuantity.setText(inQuiryActivity.getString(R.string.txt_inquiry_items_quantity));

    }


    @Override
    public void queryBySN(IntentResult result)
    {
        String info = result.toString();
        int contentIndex = info.indexOf("containers");
        String SN = info.substring(contentIndex + 28,contentIndex + 44);

        String modelNo = SN.substring(Constants.FG_MODEL_STR_START_INDEX,Constants.FG_MODEL_STR_START_INDEX+Constants.FG_MODEL_STR_LEN);
        String FGDate_In = SN.substring(Constants.FG_DATE_IN_STR_START_INDEX,Constants.FG_DATE_IN_STR_START_INDEX+Constants.FG_DATE_IN_STR_LEN);
        String FGSerial = SN.substring(Constants.FG_SERIAL_STR_START_INDEX,Constants.FG_SERIAL_STR_START_INDEX + Constants.FG_SERIAL_STR_LEN);

        DbHelper dbHelper = new DbHelper(inQuiryActivity);

        RealmResults<Item> items= dbHelper.queryItemsList(Constants.MODEL_NO,"158012");
        int totalItem = 0;

        for(Item item1 : items)
        {

            if(!modelQuantityMap.containsKey(item1.getLocation()))
                modelQuantityMap.put(item1.getLocation() + "(Zone : "+ item1.getZoneCoe() +")", 1);
            else
                modelQuantityMap.put(item1.getLocation() + "(Zone : "+ item1.getZoneCoe() +")", modelQuantityMap.get(item1.getLocation()) + 1);
        }
        for (Map.Entry<String, Integer> entry : modelQuantityMap.entrySet()) {
            ItemAdapter itemAdapter = new ItemAdapter();

            Item item = new Item();
            item.setSN(entry.getKey());
            item.setLocation(String.valueOf(entry.getValue()));
            totalItem += entry.getValue();
            itemAdapter.setItemModel(item);
            itemAdapter.setCheck(false);
            itemAdapter.setCheckbox_visible(View.GONE);
            itemAdapters.add(itemAdapter);
        }

        FGModel model =  dbHelper.queryModel(Constants.MODEL_NO,modelNo);
        queryByTitle.setText(inQuiryActivity.getString(R.string.txt_inquiry_items_model) + model.getModelTitle());
        queryQuantity.setText(inQuiryActivity.getString(R.string.txt_inquiry_items_quantity) +":" + totalItem);
        containerAdapter.notifyDataSetChanged();


        Toast.makeText(inQuiryActivity, "Scanned: " + result.toString(), Toast.LENGTH_LONG).show();
        //if(items.size() < count)
    }

}
