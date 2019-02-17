package com.ay3524.mvvmlearn.data;

import com.ay3524.mvvmlearn.pojo.PixaBay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

    @GET("api/")
    Call<PixaBay> getWallpaper(@Query("key") String apiKey);
}
