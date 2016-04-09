package com.nnd.metube.Helper;

import com.nnd.metube.Model.ModelVideo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 4/6/2016.
 */
public class CallbackVideo implements Serializable {
    String kind;
    List<ModelVideo> items;
    String nextPageToken;
    String prevPageToken;

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public void setPrevPageToken(String prevPageToken) {
        this.prevPageToken = prevPageToken;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public List<ModelVideo> getItems() {
        return items;
    }

    public void setItems(List<ModelVideo> items) {
        this.items = items;
    }
}
