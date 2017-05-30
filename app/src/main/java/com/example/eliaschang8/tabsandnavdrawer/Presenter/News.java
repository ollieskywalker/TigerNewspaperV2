package com.example.eliaschang8.tabsandnavdrawer.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.eliaschang8.tabsandnavdrawer.Modler.JSONParser;
import com.example.eliaschang8.tabsandnavdrawer.Modler.PostItem;
import com.example.eliaschang8.tabsandnavdrawer.R;

import java.util.ArrayList;

/**
 * Created by eliaschang8 on 4/26/17.
 */

public class News extends Fragment {
    private static final String TAG = "TAG";
    private ArrayList<PostItem> postsArray;
    private ListView list;
    private static final String URL = "http://tigernewspaper.com/wordpress/wp-json/wp/v2/posts?filter[cat]=4&_embed";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = (ListView)view.findViewById(R.id.listView_news);
        JSONParser parser = new JSONParser(this, list);
        parser.execute(URL);

    }
}
