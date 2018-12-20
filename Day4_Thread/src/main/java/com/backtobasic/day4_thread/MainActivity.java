package com.backtobasic.day4_thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.net.ssl.HttpsURLConnection;

/**
 * 1. Using AsyncTask to download image from internet and pass it to UI thread
 * 2. Using the Volley to download image, which creates new threads
 */

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MAIN_ACTIVITY";
    private String[] random = new String[]{"HAPPY!", "SAD!", "CRY!"};
    private final String LINK = "https://www.w3schools.com/w3css/img_lights.jpg";
    private AtomicInteger count = new AtomicInteger(0);

    Button img_button;
    Button thread_button;
    TextView textView;
    ImageView imageView;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            textView.setText(random[new Random().nextInt(3)]);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_button = (Button) findViewById(R.id.img_btn);
        thread_button = (Button) findViewById(R.id.thread_btn);
        textView = (TextView) findViewById(R.id.main_text);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void onDownloadImage(View view) {
        Log.d(TAG, "Downloading Image...");
//        new DownloadImage().execute(LINK);

//        RequestQueue queue = Volley.newRequestQueue(this);

    }

    public void onChangeText(View view) {
        Log.d(TAG, "Changing Text...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // This will crash since we are changing UI in non-main thread;
//                textView.setText("HAPPY!");
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    public void onCountDown(View view) {
        Log.d(TAG, "Counting...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                int thread = count.getAndIncrement();
                for (int i = 0; i < 10000; i++) {
                    Log.d(TAG, "Thread: " + thread + " Current value: " + i);
                }
            }
        }).start();
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        Bitmap bitmap;
        @Override
        protected Bitmap doInBackground(String... strings) {
            Log.d(TAG, "Do in background");
            try {
                URL url = new URL(strings[0]);
                Log.d(TAG, "URL: " + url.toString());
                HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
                int response = -1;
                response = httpURLConnection.getResponseCode();
                Log.d(TAG, "Response code: " + response);
                if (response == HttpURLConnection.HTTP_OK) {
                    InputStream in = httpURLConnection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(in);
                    in.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d(TAG, "Setting image");
            imageView.setImageBitmap(bitmap);
        }
    }
}
