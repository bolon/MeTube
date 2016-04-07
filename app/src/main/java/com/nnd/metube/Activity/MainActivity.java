package com.nnd.metube.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.common.collect.Lists;
import com.nnd.metube.Adapter.CustomGridAdapter;
import com.nnd.metube.Helper.CallbackAPI;
import com.nnd.metube.Helper.GeneralHelper;
import com.nnd.metube.Helper.RESTClient;
import com.nnd.metube.Model.ModelVideo;
import com.nnd.metube.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent m_intent = getIntent();
        final List<ModelVideo> m_list_video = (List<ModelVideo>) m_intent.getSerializableExtra("list_video");

        //Log.d("ASD", (String) m_intent.getExtras().getSerializable("list_video"));
        Log.d("SIZE ", String.valueOf(m_list_video.size()));
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new CustomGridAdapter(getApplicationContext(), m_list_video));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //GeneralHelper.makeToast(getApplicationContext(), m_list_video.get(position).getSnippet().getTitle(), true);
                Intent mIntent = new Intent(getApplicationContext(), DetailVideoActivity.class);
                mIntent.putExtra("item", m_list_video.get(position));
                startActivity(mIntent);
            }
        });
    }
}
