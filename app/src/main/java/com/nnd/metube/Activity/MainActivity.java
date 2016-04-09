package com.nnd.metube.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.nnd.metube.Adapter.CustomVideoAdapter;
import com.nnd.metube.Helper.CallbackVideo;
import com.nnd.metube.Helper.RESTClient;
import com.nnd.metube.Model.ModelVideo;
import com.nnd.metube.R;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    boolean flag_loading = false;
    List<ModelVideo> m_list_video;  //bucket for all vid items
    CallbackVideo m_callback;
    CustomVideoAdapter m_adapter;
    String nextPageToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get data
        Intent m_intent = getIntent();
        m_callback = (CallbackVideo) m_intent.getSerializableExtra("callback_video");
        m_list_video = m_callback.getItems();

        nextPageToken = m_callback.getNextPageToken();  //initial nextpage
        //Log.d("ASD", (String) m_intent.getExtras().getSerializable("list_video"));
        Log.d("SIZE ", String.valueOf(m_list_video.size()));
        gridView = (GridView) findViewById(R.id.gridview);
        m_adapter = new CustomVideoAdapter(getApplicationContext(), m_list_video);
        gridView.setAdapter(m_adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //GeneralHelper.makeToast(getApplicationContext(), m_list_video.get(position).getSnippet().getTitle(), true);
                Intent mIntent = new Intent(getApplicationContext(), DetailVideoActivity.class);
                mIntent.putExtra("item", m_list_video.get(position));
                startActivity(mIntent);
            }
        });

        //add new item on last item scrolled
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount==totalItemCount & totalItemCount!=0){
                    if(flag_loading == false){
                        flag_loading = true;
                        addItems(nextPageToken);
                        //Log.d("Next pag token ", m_callback.getNextPageToken());
                    }
                }
            }
        });
    }

    //additem on last index of list
    public void addItems(String _nextPageToken){
        if(nextPageToken!=""){  //check if nextpagetoken still exist
            Call<CallbackVideo> call = RESTClient.getRestClient().getVideoList("snippet", "mostPopular", 12, _nextPageToken, RESTClient.API_KEY); //12 -> itm retrieved per request
            call.enqueue(new Callback<CallbackVideo>() {
                @Override
                public void onResponse(Call<CallbackVideo> call, Response<CallbackVideo> response) {
                    //list_vid = response.body().getItems();
                    if(m_list_video.addAll(response.body().getItems())){
                        m_adapter.notifyDataSetChanged();
                        //update nextpagetoken
                        nextPageToken = response.body().getNextPageToken();
                        Log.d("SIZE OF CURRENT LIST ", String.valueOf(m_list_video.size()));
                    }
                }

                @Override
                public void onFailure(Call<CallbackVideo> call, Throwable t) {
                    Log.d("FAIL", t.getMessage());
                }
            });
            //set flag back to initial state (only if has nextpagetoken)
            flag_loading = false;
        }
    }
}
