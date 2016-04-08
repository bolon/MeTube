package com.nnd.metube.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.nnd.metube.Adapter.CustomVideoAdapter;
import com.nnd.metube.Model.ModelVideo;
import com.nnd.metube.R;

import java.util.List;

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
        gridView.setAdapter(new CustomVideoAdapter(getApplicationContext(), m_list_video));

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
