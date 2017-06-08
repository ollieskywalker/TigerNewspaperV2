package com.example.eliaschang8.tabsandnavdrawer.Modler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eliaschang8.tabsandnavdrawer.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

/**
 * Created by csaper6 on 4/27/17.
 */

public class PostAdapter extends ArrayAdapter<PostItem> {

    public PostAdapter(Context context, List<PostItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if not given a view, we need to make one
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, null);
        }
        //get the item at the position where we are
        PostItem item = getItem(position);

        //wire up our view
        TextView title = (TextView) convertView.findViewById(R.id.textView_title);
        TextView excerpt = (TextView) convertView.findViewById(R.id.textView_excerpt);
        TextView author = (TextView) convertView.findViewById(R.id.textView_author);
        TextView date = (TextView) convertView.findViewById(R.id.textView_date);
        ImageView thumbNail = (ImageView) convertView.findViewById(R.id.imageView_thumbnail);

        Picasso.with(getContext())
                .load(item.getThumbnail())
                .into(thumbNail);

        //put the text of the hero into the appropriate views
        title.setText(item.getTitle());
        excerpt.setText(item.getExcerpt());
        author.setText("By: " + item.getAuthor());
        date.setText(item.getDate());

        //new DownloadImageTask((ImageView) convertView.findViewById(R.id.imageView_thumbnail))
        //        .execute(item.getThumbnail());

        //return the view that you had edited
        return convertView;

    }

//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView viewById) {
//            bmImage = viewById;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            String urldisplay = params[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//
//    }
}
