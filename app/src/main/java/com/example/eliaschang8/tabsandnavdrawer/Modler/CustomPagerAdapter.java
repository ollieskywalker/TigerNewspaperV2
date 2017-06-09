package com.example.eliaschang8.tabsandnavdrawer.Modler;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by csaper6 on 5/17/17.
 */

public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    protected Context mContext;
    private ArrayList<String>imageURL = new ArrayList<>();
    //ArrayList<String>imageList = ArticlePage.urlList;

    public CustomPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    // This method returns the fragment associated with
    // the specified position.
    //
    // It is called when the Adapter needs a fragment
    // and it does not exists.
    public Fragment getItem(int position) {

        for(String x : imageURL){
            Log.d("CCCCCCCCCCCCCC", x);
        }

        Fragment fragment = new ImageFragment();

        // Attach some data to it that we'll
        // use to populate our fragment layouts
        Bundle args = new Bundle(); //things
        args.putInt("page_position", position + 1);
        args.putStringArrayList("IMAGES", imageURL);


        // Set the arguments on the fragment
        // that will be fetched in DemoFragment@onCreateView
        fragment.setArguments(args);

        return fragment;
    }

    /*
    Gets the number of views possible in th ViewPager. E.g. 3 images => 3
     */
    @Override
    public int getCount() {
        return 100;
    }

    public void sendImages(ArrayList<String>list) {
        imageURL = list;
    }
}
