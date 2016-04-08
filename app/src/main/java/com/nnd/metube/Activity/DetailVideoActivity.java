package com.nnd.metube.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.nnd.metube.Adapter.CustomCommentAdapter;
import com.nnd.metube.Helper.CallbackComment;
import com.nnd.metube.Helper.RESTClient;
import com.nnd.metube.Model.ModelComment;
import com.nnd.metube.Model.ModelVideo;
import com.nnd.metube.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailVideoActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    ListView lv_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);

        //get data
        Intent mIntent = getIntent();
        final ModelVideo mVideo = (ModelVideo) mIntent.getSerializableExtra("item");

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        //init player
        youTubePlayerView.initialize(RESTClient.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(mVideo.getId());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                if(youTubeInitializationResult.isUserRecoverableError()){
                    youTubeInitializationResult.getErrorDialog(DetailVideoActivity.this, 1).show();
                }else{
                    Log.d("ERROR", youTubeInitializationResult.toString());
                }
            }
        });

        //listview init
        lv_comment = (ListView) findViewById(R.id.list_comment);

        Call<CallbackComment> call = RESTClient.getRestClient().getCommentList("snippet", 20, mVideo.getId(), RESTClient.API_KEY);
        call.enqueue(new Callback<CallbackComment>() {
            @Override
            public void onResponse(Call<CallbackComment> call, Response<CallbackComment> response) {
                CallbackComment callbackComment = response.body();
                //Log.d("SUCCESS", String.valueOf(callbackComment.getItems().size()));
                List<ModelComment> list_comment = callbackComment.getItems();

                lv_comment.setAdapter(new CustomCommentAdapter(list_comment, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<CallbackComment> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });

    }
}
