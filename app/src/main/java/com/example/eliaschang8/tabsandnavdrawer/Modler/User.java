package com.example.eliaschang8.tabsandnavdrawer.Modler;

/**
 * Created by Macbookuser on 5/5/17.
 */
public class User {
    private String username, comments;
    public String logInURL;
    private String timeStamp;

    public User (String username, String timeStamp, String comments){
        this.username = username;
        this.timeStamp = timeStamp;
        this.comments = comments;
    }

    public User (){
        this.username = "j7de5s";
        this.comments = "Finally! I can't wait to see the newly renovated Tiger Patio!";
        this.timeStamp = "Wed, 4 March 2017 12:08:56";
    }

    public String getUsername(){
        return username;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public String getComments(){
        return comments;
    }


    public String getLogInURL() {
        return logInURL;
    }






}

