package com.spiritfitness.spiritfitapp.delegate.query;

import android.widget.TextView;

import com.google.zxing.integration.android.IntentResult;
import com.spiritfitness.spiritfitapp.InQuiryActivity;
import com.spiritfitness.spiritfitapp.adapter.ContainerAdapter;
import com.spiritfitness.spiritfitapp.adapter.ItemAdapter;

import java.util.List;

/**
 * Created by geminihsu on 02/12/2017.
 */

public class InQuiryViewDelegateBase {
    protected InQuiryActivity inQuiryActivity;
    protected ContainerAdapter containerAdapter;
    protected List<ItemAdapter> itemAdapters;

    //display title
    protected TextView queryByTitle;
    protected TextView queryQuantity;


    public InQuiryViewDelegateBase(InQuiryActivity _inQuiryActivity,ContainerAdapter _containerAdapter,List<ItemAdapter> _itemAdapters,TextView _queryByTitle,TextView _queryQuantity) {
        inQuiryActivity = _inQuiryActivity;
        containerAdapter = _containerAdapter;
        itemAdapters = _itemAdapters;
        queryByTitle = _queryByTitle;
        queryQuantity = _queryQuantity;
    }

    public void setInitialActivity() {

    }

    public void queryBySN(IntentResult result)
    {

    }



}
