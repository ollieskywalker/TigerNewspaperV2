package com.example.eliaschang8.tabsandnavdrawer.Modler;

import android.widget.ImageView;

/**
 * Created by csaper6 on 4/27/17.
 */

public class PostItem {
    private String title, excerpt, author, date;
    private String thumbnail;

    public PostItem(String title, String excerpt, String author, String date, String thumbnail) {
        this.title = title;
        this.excerpt = excerpt;
        this.author = author;
        this.date = date;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
