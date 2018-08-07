package com.spiritfitness.spiritfitapp.restfulapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spiritfitness.spiritfitapp.model.Item_inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/1/2017.
 */

public class ZoneItemInfo {



    @SerializedName("seq")
    public Integer seq;
    @SerializedName("SN")
    public String SN;
    @SerializedName("date")
    public String date;
    @SerializedName("Location")
    public String Location;
    @SerializedName("ModelNo")
    public String ModelNo;


    @Override
    public String toString() {
        return "Feed{" +
                "Item SN ='" + SN + '\'' +
                ", Item Location=" + Location +
                '}';
    }
}
