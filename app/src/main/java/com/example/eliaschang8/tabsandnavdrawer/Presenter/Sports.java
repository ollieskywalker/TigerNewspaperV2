package com.example.eliaschang8.tabsandnavdrawer.Presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eliaschang8.tabsandnavdrawer.Modler.JSONParser;
import com.example.eliaschang8.tabsandnavdrawer.Modler.PostItem;
import com.example.eliaschang8.tabsandnavdrawer.R;

import java.util.ArrayList;

/**
 * Created by csaper6 on 4/27/17.
 */

public class Sports extends Fragment{
    private static final String TAG = "TAG";
    private ArrayList<PostItem> postsArray;
    private ListView list;
    private static final String URL = "http://tigernewspaper.com/wordpress/wp-json/wp/v2/posts?filter[cat]=7&_embed";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sports, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = (ListView)view.findViewById(R.id.listView_sports);
        JSONParser parser = new JSONParser(this, list);
        parser.execute(URL);
    }
}
