package com.example.eliaschang8.tabsandnavdrawer.Modler;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eliaschang8.tabsandnavdrawer.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by chens on 5/25/17.
 */

public class ImageFragment extends Fragment {
    ImageView image;
    ArrayList<String> urls = new ArrayList<String>();
    Activity activity = this.getActivity();

    private int position;
    @Override

   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //fillUrlsArray();
        // Inflate the layout resource that'll be returned
        View rootView = inflater.inflate(R.layout.fragment_demo, container, false);

        Bundle args = getArguments();
        position = args.getInt("page_position");
        urls = args.getStringArrayList("IMAGES");

        //TODO: FIRST!!!!!: populate int[] with all bitmaps? < obtained from async below.
        //setImageResource(mResources[position])

        int[] mResources = new int[10];


        if(position > getCount()-1){
             position = position%getCount();
        }
        image = ((ImageView) rootView.findViewById(R.id.image));//beware: xml labeled image(0), but R.drawable.FIRST!!!

        Picasso.with(getActivity()).load(urls.get(position)).into(image);
        //new DownloadImageTask(image).execute(urls.get(position));//to url at position 1 of the url array
//to do section ends here
        return rootView;

    }
    private void fillUrlsArray() {
        //for (int i = 0; i < urlList.size(); i++)
        //{
        //    urls.add(urlList.get(i));
        //}
        urls.add("http://cdn.akc.org/akcdoglovers/ShibaInu_hero.jpg");
        urls.add("http://tigernewspaper.com/wordpress/wp-content/uploads/2017/05/DSC_0171.jpg");
        urls.add("http://spvnews.com/sites/default/files/styles/article/public/Murphy%20SPHS%20Cheer%201.jpg?itok=6vJtF-Rp");
    }

    private void setURL(){

    }

    public int getCount(){
        Log.d("BETHANY", "" + urls.size());
        return urls.size();
    }

}
