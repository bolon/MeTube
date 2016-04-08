package com.nnd.metube.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * sorry for the bad structure of class. Youtube return nested object through json.
 * Created by nnd on 4/7/2016.
 */
public class ModelComment implements Serializable{
    String id;
    SnippetParent snippet;
    boolean isPublic;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSnippet(SnippetParent snippet) {
        this.snippet = snippet;
    }

    public SnippetParent getSnippet() {
        return snippet;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public class SnippetParent implements Serializable{
        String videoId;
        TopLevelComment topLevelComment;

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public TopLevelComment getTopLevelComment() {
            return topLevelComment;
        }

        public void setTopLevelComment(TopLevelComment topLevelComment) {
            this.topLevelComment = topLevelComment;
        }

    }
    public class TopLevelComment implements Serializable{
        String id;
        @SerializedName("snippet")
        SnippetDetails snippetDetails;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public SnippetDetails getSnippetDetails() {
            return snippetDetails;
        }

        public void setSnippetDetails(SnippetDetails snippetDetails) {
            this.snippetDetails = snippetDetails;
        }
    }
    public class SnippetDetails implements Serializable{
        String authorDisplayName;
        String authorProfileImageUrl;
        String textDisplay;
        String publishedAt;

        public String getAuthorDisplayName() {
            return authorDisplayName;
        }

        public String getAuthorProfileImageUrl() {
            return authorProfileImageUrl;
        }

        public void setAuthorDisplayName(String authorDisplayName) {
            this.authorDisplayName = authorDisplayName;
        }

        public void setAuthorProfileImageUrl(String authorProfileImageUrl) {
            this.authorProfileImageUrl = authorProfileImageUrl;
        }

        public String getTextDisplay() {
            return textDisplay;
        }

        public void setTextDisplay(String textDisplay) {
            this.textDisplay = textDisplay;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }
    }
}
