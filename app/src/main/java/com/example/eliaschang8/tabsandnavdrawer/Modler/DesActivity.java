package com.example.eliaschang8.tabsandnavdrawer.Modler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eliaschang8.tabsandnavdrawer.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by eliaschang8 on 4/26/17.
 */

public class DesActivity extends AppCompatActivity {

    private ArrayList<String>urlList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.textView_content);

        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("CONTENT");

        int http;
        int a = 0;
        while (a < value.length()) {
            http = value.indexOf("http", a);
            if (http == -1)
                break;
            String urlOne = value.substring(http, value.indexOf(" ", http) - 1);
            Log.d("HEEEEEEEEEEEYYYYYYYYYYY", urlOne);
            urlList.add(urlOne);
            a = http + urlOne.length();
        }

        String oneUrl = "";
        Log.d("NNNNNNNNNNNN", "" + urlList.size());
        for (int i = 0; i < urlList.size(); i++)
        {
            oneUrl += urlList.get(i) + " ";
        }

        //value = android.text.Html.fromHtml(value).toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
