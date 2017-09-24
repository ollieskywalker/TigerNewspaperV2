package com.example.eliaschang8.tabsandnavdrawer.Modler;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kmyohan0 on 9/24/2017.
 */

public class SaveToJSON extends Application{



    String json = null;

    public void editJSON() throws JSONException {
        try {
            InputStream is = getAssets().open("SavedArticle.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject(json);
    }

}

