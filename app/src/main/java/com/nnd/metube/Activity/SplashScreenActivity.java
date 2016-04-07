package com.nnd.metube.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nnd.metube.Adapter.CustomGridAdapter;
import com.nnd.metube.Helper.CallbackAPI;
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

        Call<CallbackAPI> call = RESTClient.getRestClient().getVideoList("snippet", "mostPopular", 12, RESTClient.API_KEY); //12 -> itm retrieved per request
        call.enqueue(new Callback<CallbackAPI>() {
            @Override
            public void onResponse(Call<CallbackAPI> call, Response<CallbackAPI> response) {
                progressDialog.dismiss();
                list_vid = response.body().getItems();
                Log.d("ITEM 1", String.valueOf(list_vid.get(0).getSnippet().getThumbnails().getStandard().getUrl()));

                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                mIntent.putExtra("list_video", (Serializable) list_vid);

                startActivity(mIntent);
                finish();
            }

            @Override
            public void onFailure(Call<CallbackAPI> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("FAIL", t.getMessage());
            }
        });
    }
}
