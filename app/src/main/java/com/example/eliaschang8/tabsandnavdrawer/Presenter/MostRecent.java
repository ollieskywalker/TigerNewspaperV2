package com.example.eliaschang8.tabsandnavdrawer.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eliaschang8.tabsandnavdrawer.Modler.DesActivity;
import com.example.eliaschang8.tabsandnavdrawer.Modler.JSONParser;
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
    private static final String URL = "http://tigernewspaper.com/wordpress/wp-json/wp/v2/posts?_embed";

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mostrecent, container, false);

        ListView list = (ListView) view.findViewById(R.id.listView_recent);
        JSONParser parser = new JSONParser(this, list);
        parser.execute(URL);

        if(list != null){
            Log.d("MostRecent", "Yes");
        }

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
