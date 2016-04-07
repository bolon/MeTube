package com.nnd.metube.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.nnd.metube.Helper.RESTClient;
import com.nnd.metube.Model.ModelVideo;
import com.nnd.metube.R;

public class DetailVideoActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;

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
    }

}
