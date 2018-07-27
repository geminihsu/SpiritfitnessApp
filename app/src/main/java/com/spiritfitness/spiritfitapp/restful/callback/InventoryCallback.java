package com.spiritfitness.spiritfitapp.restful.callback;

import com.spiritfitness.spiritfitapp.restfulapi.Itembean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by User on 5/1/2017.
 */

public interface InventoryCallback {


    @Headers("Content-Type: application/json")

    @GET("/api/FGInventory")
    Call<List<Itembean>> doGetItemList();

    @GET("/api/FGInventory?")
    Call<Itembean> doGetItem(@Query("id") int id);

    @DELETE("/api/FGInventory?")
    Call<Itembean> doDeleteItem(@Query("id") int id);

    @FormUrlEncoded
    @POST("/api/FGInventory?")
    Call<Itembean> doCreateItemWithField(@Field("seq") int seq, @Field("SN") String sn, @Field("Date") String date, @Field("Location") String location, @Field("ModelNo") String model_no);


    @FormUrlEncoded
    @PUT("/api/FGInventory?")
    Call<Itembean> doUpdateItemWithField(@Query("id") int id, @Field("seq") int seq, @Field("SN") String sn, @Field("Date") String date, @Field("Location") String location, @Field("ModelNo") String model_no);

}
