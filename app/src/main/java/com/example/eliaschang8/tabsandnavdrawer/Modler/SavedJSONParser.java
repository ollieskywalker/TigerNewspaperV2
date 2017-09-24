package com.example.eliaschang8.tabsandnavdrawer.Modler;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by kmyohan0 on 9/24/2017.
 */

public class SavedJSONParser extends AsyncTask<String, Void, String> {

    private static final String TAG = "TAG";
    private ArrayList<String> imageUrlList = new ArrayList<>();
    private String[] ActualURL = null;


    String postJSON = "";

    private ArrayList<PostItem>postsArray;
    Fragment fragmentActivity;
    ListView list;
    private int articleId;

    public SavedJSONParser(Fragment fragmentActivity, ListView list){
        this.fragmentActivity = fragmentActivity;
        this.list = list;
    }

    @Override
    protected String doInBackground(String... urls) {
        try {
            java.net.URL url = new java.net.URL(urls[0]);
            ActualURL = urls;
            URLConnection connection = url.openConnection();

            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line = reader.readLine()) != null){
                postJSON += line;
                Log.d("onAsyncClass" , "Looping");
            }
            return postJSON;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("onAsyncClass" , "NOT WORKING dsfsdfsdfs");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        postsArray = new ArrayList<PostItem>();

        if(s != null){
            try {
                JSONArray postsJSON = new JSONArray(postJSON);

                for(int i = 0; i < postsJSON.length(); i++){

                    JSONObject objectPost =  postsJSON.optJSONObject(i);

                    //Get the Title
                    JSONObject renderedTitle = objectPost.optJSONObject("title");
                    String title = renderedTitle.optString("rendered");
                    title = android.text.Html.fromHtml(title).toString();
                    //Get the excerpt
                    JSONObject renderedExcerpt = objectPost.optJSONObject("excerpt");
                    String excerpt = renderedExcerpt.optString("rendered");
                    excerpt = android.text.Html.fromHtml(excerpt).toString();
                    //Get the author
                    JSONObject _embed = objectPost.optJSONObject("_embedded");
                    JSONArray array = _embed.optJSONArray("author");
                    JSONObject author = array.optJSONObject(0);
                    String name = author.optString("name");

                    String date  = objectPost.getString("date");
                    date = "Date: " + date.substring(date.indexOf("-")+1, date.indexOf("T")) + "-" + date.substring(0, date.indexOf("-"));

                    JSONObject betterFeaturedImage = objectPost.optJSONObject("better_featured_image");
                    JSONObject mediaDetail = betterFeaturedImage.optJSONObject("media_details");
                    JSONObject sizes = mediaDetail.optJSONObject("sizes");
                    JSONObject thumbnail = sizes.optJSONObject("thumbnail");
                    String ImgURL = thumbnail.optString("source_url");

                    JSONObject content = objectPost.optJSONObject("content");
                    String contentRendered = content.optString("rendered");
                    //contentRendered = android.text.Html.fromHtml(contentRendered).toString();

                    JSONObject mainImage = sizes.optJSONObject("medium");
                    String featured = mainImage.optString("source_url");

                    //ArticleID for Saving Article
                    articleId = objectPost.optInt("id");


                    PostItem currentPost = new PostItem(title, excerpt, name, date, ImgURL, contentRendered, featured);

                    postsArray.add(currentPost);

                    //Log.d(TAG, "onPostExecute: " + ImgURL);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
