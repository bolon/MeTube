package com.nnd.metube.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nnd.metube.Model.ModelComment;
import com.nnd.metube.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 4/7/2016.
 */
public class CustomCommentAdapter extends BaseAdapter {
    List<ModelComment> list_comment = new ArrayList<>();
    Context m_context;
    LayoutInflater m_layoutInflater;

    public CustomCommentAdapter(List<ModelComment>list_comment, Context context){
        this.list_comment = list_comment;
        this.m_context = context;
        m_layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list_comment.size();
    }

    @Override
    public Object getItem(int position) {
        return list_comment.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = m_layoutInflater.inflate(R.layout.cust_item_comment, null);
            holder = new ViewHolder();
            holder.iv_thumb_author = (ImageView) convertView.findViewById(R.id.iv_author_thumb);
            holder.tv_displayName_author = (TextView) convertView.findViewById(R.id.tv_author_displayName);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_comment_value);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //get comment
        ModelComment.SnippetDetails snippetDetails = list_comment.get(position).getSnippet().getTopLevelComment().getSnippetDetails();

        holder.tv_text.setText(Html.fromHtml(snippetDetails.getTextDisplay()));    //sometimes user put html tags in comment
        holder.tv_displayName_author.setText(snippetDetails.getAuthorDisplayName());

        //set img & transformation
        Transformation transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = holder.iv_thumb_author.getWidth();

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
                .load(snippetDetails.getAuthorProfileImageUrl())
                .placeholder(R.mipmap.placeholder_person)
                        //.fit()
                        //.centerCrop()
                .transform(transformation)
                .into(holder.iv_thumb_author);

        return convertView;
    }

    static class ViewHolder{
        ImageView iv_thumb_author;
        TextView tv_displayName_author;
        TextView tv_text;
    }
}
