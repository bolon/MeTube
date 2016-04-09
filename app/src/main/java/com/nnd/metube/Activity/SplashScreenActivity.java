package com.nnd.metube.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nnd.metube.Helper.CallbackVideo;
import com.nnd.metube.Helper.RESTClient;
import com.nnd.metube.Model.ModelVideo;
import com.nnd.metube.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    List<ModelVideo> list_vid = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressDialog = new ProgressDialog(SplashScreenActivity.this);
        progressDialog.setMessage("Fetching data...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Call<CallbackVideo> call = RESTClient.getRestClient().getVideoList("snippet", "mostPopular", 12, null, RESTClient.API_KEY); //12 -> itm retrieved per request
        call.enqueue(new Callback<CallbackVideo>() {
            @Override
            public void onResponse(Call<CallbackVideo> call, Response<CallbackVideo> response) {
                progressDialog.dismiss();
                list_vid = response.body().getItems();
                CallbackVideo m_callbackVideo = response.body();
                Log.d("ITEM 1", String.valueOf(list_vid.get(0).getSnippet().getThumbnails().getStandard().getUrl()));

                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                mIntent.putExtra("callback_video", (Serializable) m_callbackVideo);

                startActivity(mIntent);
                finish();
            }

            @Override
            public void onFailure(Call<CallbackVideo> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("FAIL", t.getMessage());
            }
        });
    }
}
