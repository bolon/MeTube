package com.nnd.metube.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lenovo on 4/6/2016.
 */
public class ModelSnippet implements Serializable{
    String publishedAt;
    String channelId;
    String title;
    String description;
    ImgThumb thumbnails;

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setThumbnails(ImgThumb thumbnails) {
        this.thumbnails = thumbnails;
    }

    public ImgThumb getThumbnails() {
        return thumbnails;
    }

    //youtube thumb return object , must deserialized 1st
    //have 4 type of size, instead taking all we only retrive standard size, proceed using Picasso lib
    public class ImgThumb implements Serializable{
        @SerializedName("high") //tell gson to retrieve high value for thumb
        ImgThumbType standard;

        public ImgThumbType getStandard() {
            return standard;
        }

        public void setStandard(ImgThumbType standard) {
            this.standard = standard;
        }

        public class ImgThumbType implements Serializable{
            String url;
            String width;
            String height;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }
        }
    }
}
