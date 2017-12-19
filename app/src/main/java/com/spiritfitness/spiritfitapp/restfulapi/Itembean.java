package com.spiritfitness.spiritfitapp.restfulapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spiritfitness.spiritfitapp.model.Item_inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/1/2017.
 */

public class Itembean {



    @SerializedName("seq")
    public Integer seq;
    @SerializedName("saleOrderNo")
    public String saleOrderNo;
    @SerializedName("SN")
    public String SN;
    @SerializedName("ModelNo")
    public String ModelNo;
    @SerializedName("Location")
    public String Location;
    @SerializedName("dateIn")
    public String dateIn;
    @SerializedName("dateOut")
    public String dateOut;
    @SerializedName("trackingNo")
    public String trackingNo;


    @Override
    public String toString() {
        return "Feed{" +
                "Item SN ='" + SN + '\'' +
                ", Item Location=" + Location +
                '}';
    }
}
