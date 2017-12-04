package com.spiritfitness.spiritfitapp.delegate.query.location;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.spiritfitness.spiritfitapp.common.Constants;
import com.spiritfitness.spiritfitapp.InQuiryActivity;
import com.spiritfitness.spiritfitapp.R;
import com.spiritfitness.spiritfitapp.adapter.ContainerAdapter;
import com.spiritfitness.spiritfitapp.adapter.ItemAdapter;
import com.spiritfitness.spiritfitapp.delegate.query.InQuiryViewDelegateBase;
import com.spiritfitness.spiritfitapp.model.FGModel;
import com.spiritfitness.spiritfitapp.model.Item;
import com.spiritfitness.spiritfitapp.util.DbHelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.realm.RealmResults;

/**
 * Created by geminihsu on 02/12/2017.
 */

public class InQuiryViewDelegateLocation extends InQuiryViewDelegateBase {
    private final String TAG= this.getClass().getSimpleName();
    LinkedHashMap<String, Integer> modelQuantityMap = new LinkedHashMap<String, Integer>();

    public InQuiryViewDelegateLocation(InQuiryActivity mainActivity,ContainerAdapter _containerAdapter,List<ItemAdapter> _itemAdapters,TextView _queryByTitle, TextView _queryQuantity) {
        super(mainActivity,_containerAdapter,_itemAdapters,_queryByTitle,_queryQuantity);

    }

    @Override
    public void setInitialActivity() {
        queryByTitle.setText(inQuiryActivity.getString(R.string.txt_inquiry_items_model));
        queryQuantity.setText(inQuiryActivity.getString(R.string.txt_inquiry_items_quantity));


        AlertDialog.Builder builder = new AlertDialog.Builder(inQuiryActivity);
        builder.setTitle(inQuiryActivity.getString(R.string.inquiry_location));

        // Set up the input
        final EditText input = new EditText(inQuiryActivity);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DbHelper dbHelper = new DbHelper(inQuiryActivity);
                RealmResults<Item> items = dbHelper.queryItemsList(Constants.ITEM_LOCATION_COLUMN, input.getText().toString());

                int totalItem = 0;
                for(Item item1 : items)
                {

                    FGModel fgModel = dbHelper.queryModel(Constants.MODEL_NO, item1.getModelNo());

                    if(!modelQuantityMap.containsKey(fgModel.getModelTitle()))
                         modelQuantityMap.put(fgModel.getModelTitle(), 1);
                    else
                         modelQuantityMap.put(fgModel.getModelTitle(), modelQuantityMap.get(fgModel.getModelTitle()) + 1);
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

                queryByTitle.setText(queryByTitle.getText().toString());
                queryQuantity.setText(queryQuantity.getText().toString());
                inQuiryActivity.setTitle(inQuiryActivity.getString(R.string.txt_inquiry_items_location) + input.getText().toString() + " total :" + totalItem);
                containerAdapter.notifyDataSetChanged();


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


}
