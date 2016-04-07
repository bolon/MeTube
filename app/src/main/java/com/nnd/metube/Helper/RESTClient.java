package com.nnd.metube.Helper;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 4/5/2016.
 */
public class RESTClient {
    private static YoutubeAPI REST_CLIENT;
    public static final String API_KEY = "AIzaSyDQwgIBg2ttUYNGuAmJFqL5kDiyndojpz0";
    public static String preURL = "www.googleapis.com/youtube/v3/";    //change this to access address of API
    public static String port = "";
    public static String postUrl = "";
    public static String URL = "https://"+preURL+port+postUrl;
    private static Context mContext;
    private static long SIZE_OF_CACHE = 50 * 1024 * 1024; // 50 MB

    static {
        setRestClient();
    }
    private RESTClient(){}
    public static YoutubeAPI getRestClient(){
        return REST_CLIENT;
    }

    //setup resclient
    //using OkHttpClient we can customize access method of restclient
    private static void setRestClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT = retrofit.create(YoutubeAPI.class);
    }
}
