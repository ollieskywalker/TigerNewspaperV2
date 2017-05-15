package com.example.eliaschang8.tabsandnavdrawer.Modler;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
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
import java.util.List;

/**
 * Created by csaper6 on 5/15/17.
 */

public class JSONParser extends AsyncTask<String, Void, String> {
    private static final String TAG = "TAG";
    String postJSON = "";

    private ArrayList<PostItem>postsArray;
    FragmentActivity fragmentActivity;
    ListView list;

    public JSONParser(FragmentActivity fragmentActivity, ListView list){
        this.fragmentActivity = fragmentActivity;
        this.list = list;
    }

    @Override
    protected String doInBackground(String... urls) {
        try {
            java.net.URL url = new java.net.URL(urls[0]);
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
                    int id = objectPost.getInt("author");
                    String author = "";
                    if(id == 39) {
                        author = "Alina";
                    } else if (id == 11) {
                        author = "Riley Segal";
                    } else if (id == 18) {
                        author = "Brandon Young";
                    } else {
                        author = "Edgar";
                    }

                    String date  = objectPost.getString("date");
                    date = "Date: " + date.substring(date.indexOf("-")+1, date.indexOf("T")) + "-" + date.substring(0, date.indexOf("-"));


                    JSONObject betterFeaturedImage = objectPost.optJSONObject("better_featured_image");
                    JSONObject mediaDetail = betterFeaturedImage.optJSONObject("media_details");
                    JSONObject sizes = mediaDetail.optJSONObject("sizes");
                    JSONObject thumbnail = sizes.optJSONObject("thumbnail");
                    String ImgURL = thumbnail.optString("source_url");

                    PostItem currentPost = new PostItem(title, excerpt, author, date, ImgURL);

                    postsArray.add(currentPost);

                    Log.d(TAG, "onPostExecute: " + ImgURL);
                }

                fillList();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillList(){
        PostAdapter adapter = new PostAdapter(fragmentActivity, postsArray);

        list.setAdapter(adapter);
    }
}
