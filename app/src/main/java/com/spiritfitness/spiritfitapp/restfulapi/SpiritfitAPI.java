package com.spiritfitness.spiritfitapp.restfulapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by User on 5/1/2017.
 */

public interface SpiritfitAPI {


    @Headers("Content-Type: application/json")

    @GET("/api/Item")
    Call<List<Itembean>> doGetItemList();

    @GET("/api/Item?")
    Call<Itembean> doGetItem(@Query("id") int id);

    @FormUrlEncoded
    @POST("/api/Item?")
    Call<Itembean> doCreateItemWithField(@Field("seq") int seq, @Field("SN") String sn, @Field("Location") String location);

}
