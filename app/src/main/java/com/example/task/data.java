package com.example.task;

public class data {

    String url,title;
    String id;

    public String getThumbnail() {
        return url;
    }

    public void setThumbnail(String thumbnail) {
        this.url = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public data(String thumbnail, String title, String url) {
        this.url = thumbnail;
        this.title = title;
        this.id=url;
    }
}
