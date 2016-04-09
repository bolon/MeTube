package com.nnd.metube.Helper;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface of YoutubeAPI
 * Created by nnd on 4/5/2016.
 */
public interface YoutubeAPI {
    @GET("videos")
    Call<CallbackVideo> getVideoList(@Query("part")String part, @Query("chart")String chart, @Query("maxResults") int count,
                                     @Query("pageToken")String pageToken, @Query("key")String key);


    @GET("commentThreads")
    Call<CallbackComment> getCommentList(@Query("part")String part, @Query("maxResults")int count,
                                         @Query("videoId")String video_id, @Query("key")String key);
}
