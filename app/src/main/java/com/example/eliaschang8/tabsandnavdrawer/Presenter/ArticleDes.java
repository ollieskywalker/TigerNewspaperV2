package com.example.eliaschang8.tabsandnavdrawer.Presenter;

/**
 * Created by csaper6 on 5/23/17.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.eliaschang8.tabsandnavdrawer.R;

import java.io.InputStream;
import java.util.ArrayList;
//TODO FIRST!!!!!!!!!!!!! change scrollable images into a single image view which unloads prev image. (for slow RAM devices)
public class ArticleDes extends AppCompatActivity {
    ArrayList<ImageView> pictureArray;
    ArrayList<String> urls; //TODO @integration - may have to change from arraylist to _...
    TextView articleContent, date, authorName, title;
    ScrollView contentScrollView;
    HorizontalScrollView imageScrollView;
    CountDownTimer timer;
    int width, height, numbOfPics, currentImageIndex;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        initialize();//loadPictureArray()
        setUpPictures();
        setOnClickListeners();
    }

    private void wireWidgets() {
        articleContent=(TextView)findViewById(R.id.textView_article_content);
        date=(TextView)findViewById(R.id.textView_date);
        authorName=(TextView)findViewById(R.id.textView_writer_name);
        title=(TextView)findViewById(R.id.textView_article_title);
        imageScrollView= (HorizontalScrollView) findViewById(R.id.scrollView_images);
        contentScrollView=(ScrollView) findViewById((R.id.scrollView_screen));
        image1 = (ImageView) findViewById(R.id.imageView_shiba_inu);
//        image2 = (ImageView) findViewById(R.id.imageView_code_statements);
//        image3 = (ImageView)findViewById(R.id.imageView_so_pas);
        //TODO (after adding all url images to scrollview + scaling) wire the rest of the images and remove when too many image#
//        timer = new CountDownTimer() {
//            @Override
//            public void onTick(long l) {
//                if(l % 4 ==0)
//                    //change image displayed
//            }
//
//            @Override
//            public void onFinish() {

//            }
//        };
    }
    private void initialize() {
        loadUrls();
        loadPictureArray();
        numbOfPics = pictureArray.size();
        //initialize variables
        currentImageIndex = 0;
    }
    private void loadUrls() {
        urls = new ArrayList<String>();
        urls.add("http://cdn.akc.org/akcdoglovers/ShibaInu_hero.jpg");
        urls.add("http://uat-wp-offload-aws-sacsconsult-com.s3-ap-southeast-2.amazonaws.com/wp-content/uploads/2015-09-02-Why-is-everything-code-1024x680.jpg");
        urls.add("http://spvnews.com/sites/default/files/styles/article/public/Murphy%20SPHS%20Cheer%201.jpg?itok=6vJtF-Rp");
    }
    private void loadPictureArray() {
        pictureArray = new ArrayList<ImageView>();
        if(image1 == null){}
        else
            pictureArray.add(image1);
        if(image2 == null){}
        else
            pictureArray.add(image2);
        if(image3 == null){}
        else
            pictureArray.add(image3);
        if(image4 == null){}
        else
            pictureArray.add(image4);
        if(image5 == null){}
        else
            pictureArray.add(image5);
        if(image6 == null){}
        else
            pictureArray.add(image6);
        if(image7 == null){}
        else
            pictureArray.add(image7);
        if(image8 == null){}
        else
            pictureArray.add(image8);
        if(image9 == null){}
        else
            pictureArray.add(image9);
        if(image10 == null){}
        else
            pictureArray.add(image10);
    }
    private void setUpPictures() {
        //importPics();
        new DownloadImageTask(image1)//set image1 //TODO (last priority) picture array may be unnecessary
                .execute(urls.get(0));//to url at position 1 of the url array

        //scale each picture in PictureArray
        for(int i = 0; i < 1; i++) //TODO 1: change back to i < numberOfPics so it cycles thru all images
        //TODO 2: keep loading pics with this for loop (may have to move line 48 "new Down..." into setUpPictures)
        {
            //currentImageIndex = 0 at initialization.
            ImageView image_view = pictureArray.get(i);
            //set width / height
            image_view.getLayoutParams().height = height;
            image_view.getLayoutParams().width = width;
            image_view.requestLayout();
            Log.d("ELEMENT 1", height + "" );
            Log.d("ELEMENT 1 SHOULD BE",""+ image1.getHeight());
        }
    }
    private void setOnClickListeners() {
        //on swipe single image view
        imageScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                //it does get to this point
                currentImageIndex++;
                if(currentImageIndex == 3){currentImageIndex =0;}
                new DownloadImageTask(image1).execute(urls.get(currentImageIndex));

            }
        });

        //unload prev picture
        //load next image
        //transition: slide
    }
    //TODO format images to fit screen, store picture h/w in array so u can make the height adjustable depending on the picture. Set roof for super big pics / crop

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap newImage = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                newImage = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return newImage;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = width*683/1024;
        }
    }

}
