package com.example.eliaschang8.tabsandnavdrawer.Presenter;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.eliaschang8.tabsandnavdrawer.Modler.PostAdapter;
import com.example.eliaschang8.tabsandnavdrawer.Modler.PostItem;
import com.example.eliaschang8.tabsandnavdrawer.R;

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
 * Created by eliaschang8 on 4/26/17.
 */

public class MostRecent extends Fragment {


    private static final String TAG = "TAG";
    private ArrayList<PostItem>postsArray;
    private ListView list;


    private static final String URL = "http://tigernewspaper.com/wordpress/wp-json/wp/v2/posts";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mostrecent, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = (ListView)view.findViewById(R.id.listView_recent);
        JSONParser parser = new JSONParser();
        parser.execute(URL);
        list.setNestedScrollingEnabled(true);

    }

    public void fillList(){
        PostAdapter adapter = new PostAdapter(getActivity(), postsArray);

        list.setAdapter(adapter);
    }

    private class JSONParser extends AsyncTask<String, Void, String>{
        private static final String TAG = "TAG";
        String postJSON = "";

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
                Log.d("onAsyncClass" , "NOT WORKING");
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
                        if(id == 39){
                            author = "Alina";
                        } else {
                            author = "Author";
                        }

                        String date  = objectPost.getString("date");

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
    }
}
