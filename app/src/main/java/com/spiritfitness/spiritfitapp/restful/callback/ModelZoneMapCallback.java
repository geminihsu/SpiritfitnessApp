package com.spiritfitness.spiritfitapp.restful.callback;

import com.spiritfitness.spiritfitapp.model.ModelDailyReportbean;
import com.spiritfitness.spiritfitapp.model.ModelZone2bean;
import com.spiritfitness.spiritfitapp.model.PickUpZoneMap;

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
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by User on 5/1/2017.
 */

public interface ModelZoneMapCallback {
    
	@Headers("Content-Type: application/json")

    @GET("FGService/api/ModelZoneMaps")
	Call<List<ModelZone2bean>> getAllModelMapZone2();
	
	@GET("FGService/api/ModelZoneMaps/Qty")
	Call<List<ModelZone2bean>> getAllModelQtyReport();
	
	@GET("FGService/api/ModelZoneMaps/DailyReport/date/{date}")
	Call<List<ModelDailyReportbean>> getAllModelDailyReport(@Path("date") String date);
	
	@GET("FGService/api/ModelZoneMaps/{modelNo}")
	Call<List<ModelZone2bean>> getItemsByModelNo(@Path("modelNo") Integer modelNo);
	
    @GET("FGService/api/ModelZoneMaps/zone/{zoneCode}")
    Call<List<PickUpZoneMap>> getPickUpByZone(@Path("zoneCode") Integer zoneCode);

	//Post one item
	/*@POST("/api/FGInventory")
	Call<Itembean> createItem(@Body Itembean itembean);*/
	
	

}
