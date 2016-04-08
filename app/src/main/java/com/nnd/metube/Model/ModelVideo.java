package com.nnd.metube.Model;

import java.io.Serializable;

/**
 * Created by lenovo on 4/5/2016.
 */
public class ModelVideo  implements Serializable {
    private String kind;
    private String id;
    private String etag;
    private ModelSnippetVideo snippet;

    public ModelSnippetVideo getSnippet() {
        return snippet;
    }

    public void setSnippet(ModelSnippetVideo snippet) {
        this.snippet = snippet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}

