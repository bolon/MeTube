package com.nnd.metube.Helper;

import com.nnd.metube.Model.ModelComment;

import java.util.List;

/**
 * Created by lenovo on 4/7/2016.
 */
public class CallbackComment {
    String kind;
    String etag;
    String id;
    String nextPageToken;
    List<ModelComment> items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getEtag() {
        return etag;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<ModelComment> getItems() {
        return items;
    }

    public void setItems(List<ModelComment> items) {
        this.items = items;
    }
}
