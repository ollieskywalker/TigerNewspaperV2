package com.example.eliaschang8.tabsandnavdrawer.Presenter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eliaschang8.tabsandnavdrawer.Modler.User;
import com.example.eliaschang8.tabsandnavdrawer.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ForumActivity extends AppCompatActivity  {
    private Button submit_Button;
    private EditText editText_comment;
    public String inputText;
    private boolean checkSignedIn;
    public String timeStamp;
    public TextView commentText;
    public TextView timeStampText;
    public TextView usernameText;
    public String logInURL;
    public User tempUser;
    public boolean RUN = false;
    public UserFragment userFragment;
    public Button delete_Button;

    /**
     public static final String TIME_STAMP = "";
     public static final String COMMENT_TEXT = "";
     public static final String USERNAME_TEXT = "";
     public static final String KEY = "123";

     **/


//    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        userFragment = null;
        final FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentByTag("UserFragment")==null){
            userFragment = new UserFragment();
            fm.beginTransaction().add(R.id.user_list_container, userFragment, "UserFragment").commit();


        }



        tempUser = new User("tigers", "Thurs, 25 May 2017 01:25:45", "Hello this is my first comment");
        wireWidgets();
        checkSign();

        submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSignedIn && editText_comment.getText().length()>0){
                    inputText = editText_comment.getText().toString();
                    DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH: mm");
                    timeStamp = df.format(Calendar.getInstance().getTime());


/**
 Bundle bundle = new Bundle();
 bundle.putString(TIME_STAMP, timeStamp);
 bundle.putString(COMMENT_TEXT, inputText);
 bundle.putString(USERNAME_TEXT, tempUser.getUsername());
 **/
                    userFragment.addComments(timeStamp, inputText, tempUser.getUsername());

//                  FragmentManager fm1 = getSupportFragmentManager();
//                   fm1.putFragment(bundle, KEY, fm1.findFragmentByTag("UserFragment"));
                    //Log.d("TAG", timeStamp);
                    // fm1.findFragmentByTag("UserFragment").

                    /**
                     * public String baseUrl;
                     * public String username;
                     * (these would be above the constructor)
                     * baseUrl = "gmail...";
                     * commentText.setText(new SWPersonSearch().execute(baseUrl));
                     **/




                    //logInURL = tempUser.getLogInURL();




                }
                else if(checkSignedIn && editText_comment.getText().length()==0){
                    Toast.makeText(ForumActivity.this, "Please add text to post a comment", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ForumActivity.this, "Please sign in to comment", Toast.LENGTH_LONG).show();
                }

                delete_Button.setVisibility(View.VISIBLE);
            }
        });

        delete_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ForumActivity.this, "You are now deleting this comment", Toast.LENGTH_LONG).show();
                userFragment.deleteComment();
            }
        });


    }

    private void checkSign() {
        /**
         * If (signed in){
         * checkSignedIn = true;}
         * else{
         * checkSignedIn = false;
         * }
         */
    }

    private void wireWidgets() {
        submit_Button = (Button)(findViewById(R.id.button_submit));
        editText_comment = (EditText)(findViewById(R.id.editText_comment));
        checkSignedIn=true;
        usernameText = (TextView) findViewById(R.id.textView_username);
        timeStampText = (TextView) findViewById(R.id.textView_timestamp);
        commentText = (TextView) findViewById(R.id.textView_comment);
        delete_Button = (Button) findViewById(R.id.button_delete);
        delete_Button.setVisibility(View.INVISIBLE);

    }

    private class SWPersonSearch extends AsyncTask<String, Void, String> {
        String jsonString = "";
        @Override
        protected String doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                URLConnection connection = null;
                connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null) {
                    jsonString += line;

                    //return "Something";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jsonData = null;
            try {
                jsonData = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (jsonData != null) {

                /**

                 username = jsonData.optJSONArray("...").optJSONObject(...).optString("...", "FAILURE");

                 Log.d(TAG, jsonData.toString());

                 instead of return null it would be return username;
                 **/



            }


            return null;
        }
    }
}