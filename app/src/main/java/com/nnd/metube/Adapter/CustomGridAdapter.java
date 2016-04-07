package com.nnd.metube.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nnd.metube.Model.ModelVideo;
import com.nnd.metube.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 4/6/2016.
 */
public class CustomGridAdapter extends BaseAdapter {
    List<ModelVideo> m_listVid = new ArrayList<>();
    Context m_context;
    LayoutInflater m_layoutInflater;

    public CustomGridAdapter(Context context, List<ModelVideo> list){
        this.m_listVid = list;
        this.m_context = context;
        this.m_layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return m_listVid.size();
    }

    @Override
    public Object getItem(int position) {
        return m_listVid.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String getVidId(int position) {
        return m_listVid.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = m_layoutInflater.inflate(R.layout.cust_item_main, null);
            holder = new ViewHolder();
            holder.thumb = (ImageView) convertView.findViewById(R.id.vid_thumb);
            holder.title = (TextView) convertView.findViewById(R.id.vid_title);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(m_listVid.get(position).getSnippet().getTitle());

        //set img & transformation
        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = holder.thumb.getWidth();

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        Picasso.with(m_context)
                .load(m_listVid.get(position).getSnippet().getThumbnails().getStandard().getUrl())
                .placeholder(R.mipmap.placeholder)
                        //.fit()
                        //.centerCrop()
                 .transform(transformation)
                .into(holder.thumb);

        return convertView;
    }

    //cust viwholder for item
    static class ViewHolder {
        ImageView thumb;
        TextView title;
    }
}
