package com.example.eliaschang8.tabsandnavdrawer.Presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eliaschang8.tabsandnavdrawer.Modler.User;
import com.example.eliaschang8.tabsandnavdrawer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Macbookuser on 5/5/17.
 */
public class UserAdapter extends ArrayAdapter<User> {
    public List<User> users= new ArrayList<>();
    public UserAdapter(Context context, List<User> objects)
    {
        super(context, 0, objects);
        users = objects;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_comments, null);
        TextView UsernameText = (TextView) convertView.findViewById(R.id.textView_username);
        TextView CommentText = (TextView) convertView.findViewById(R.id.textView_comment);
        TextView TimeStampText = (TextView) convertView.findViewById(R.id.textView_timestamp);
        UsernameText.setText(users.get(position).getUsername());
        CommentText.setText(users.get(position).getComments());
        TimeStampText.setText(users.get(position).getTimeStamp());

        ImageButton trash = (ImageButton) convertView.findViewById(R.id.imageButton_delete);
        trash.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                UserAdapter.this.remove(users.get(position));
                UserAdapter.this.notifyDataSetChanged();
            }
        });


        return convertView;
    }

       /**
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comments, null);
        }


        return convertView;
        }
        **/

}