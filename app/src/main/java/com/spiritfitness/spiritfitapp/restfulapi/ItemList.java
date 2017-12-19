package com.spiritfitness.spiritfitapp.restfulapi;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by geminih on 12/15/2017.
 */

public class ItemList {
    @SerializedName("items")
    public List<ItemList> items = new ArrayList<>();


}
