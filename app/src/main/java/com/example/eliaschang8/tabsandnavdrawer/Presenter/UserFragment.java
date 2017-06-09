package com.example.eliaschang8.tabsandnavdrawer.Presenter;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eliaschang8.tabsandnavdrawer.Modler.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Macbookuser on 5/15/17.
 */
public class UserFragment extends ListFragment {
    private List<User> users;
    public String comment;
    public String timestamp;
    public String username;
    public boolean getNewComment;
    public UserAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        //create our list of heroes
        users = new ArrayList<>();


        populateList();

        //fill the custom adapter
        adapter = new UserAdapter(getActivity(), users);



        //set the listView's adapter
        setListAdapter(adapter);



        return rootView;
    }





    private void populateList() {

        users.add(new User("j7de5s", "Wed, 4 March 2017 12:08:56", "Finally! I can't wait to see the newly renovated Tiger Patio!"));
        users.add(new User("ILoveSwift", "Wed, 4 March 2017 12:15:14", "While I like the idea of a fresh, clean Tiger Patio" +
                ", I think spending one million dollars on it is just ridiculous."));
        users.add(new User("Smile4032", "Thurs, 5 March 2017 09:17:04", "Yeah I agree with @ILoveSwift. Our school honestly" +
                " has so many better things to spend that money on its ridiculous."));
        users.add(new User("anonymousUser", "Thurs, 5 March 2017 11:11:11", "I think the school should have asked for our opinion on this " +
                "matter but since the deal is already set and things are in motion, we should support and respect" +
                "the school's decision"));



    }

    public void addComments(String timestamps, String comments, String usernames) {

        // Bundle b = getArguments();
        //if (b!= null){
        //  comment = b.getString("COMMENT_TEXT");
        //timestamp = b.getString("TIME_STAMP");
        //username = b.getString("USERNAME_TEXT");
        comment = comments;
        timestamp = timestamps;
        username = usernames;
        Log.d("TAG", "package received");


        users.add(new User(username, timestamp, comment));
        adapter.notifyDataSetChanged();
    }
    // else
    //    {
    //  Log.d("TAG", "package not received");
    //}

    public void deleteComment(){
        adapter.remove(users.remove(users.size()-1));
        adapter.notifyDataSetChanged();
    }
}