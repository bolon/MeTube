package com.nnd.metube.Helper;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lenovo on 4/5/2016.
 */
public interface YoutubeAPI {

    @GET("videos")
    Call<CallbackAPI> getVideoList(@Query("part")String part, @Query("chart")String chart, @Query("maxResults") int count, @Query("key")String key);

    //void getVideoList(@Query("part")String part, @Query("chart")String chart, @Query("key")String key, Callback<CallbackAPI> callback);
}
