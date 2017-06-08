package com.example.eliaschang8.tabsandnavdrawer.Modler;

import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by csaper6 on 4/27/17.
 */

public class PostItem {
    private String title, excerpt, author, date, content, featuredImage;
    private String thumbnail;
    private ArrayList<String> urls = new ArrayList<>();

    public PostItem(String title, String excerpt, String author, String date, String thumbnail, String content, String featuredImage) {
        this.title = title;
        this.excerpt = excerpt;
        this.author = author;
        this.date = date;
        this.thumbnail = thumbnail;
        this.content = content;
        this.featuredImage = featuredImage;
        //this.urls = urls;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrls() {
        String oneUrl = "";
        Log.d("NNNNNNNNNNNN", "" + urls.size());
        for (int i = 0; i < urls.size(); i++)
        {
            oneUrl += urls.get(i) + " ";
        }
        return oneUrl + "hi";
    }

    public void setUrls(ArrayList urls) {
        this.urls = urls;
    }

    public String getFeaturedImage() {
        return thumbnail;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }
}
