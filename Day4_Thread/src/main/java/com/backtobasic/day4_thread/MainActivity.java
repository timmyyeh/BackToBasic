package com.backtobasic.day4_thread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. Using AsyncTask to download image from internet and pass it to UI thread
 * 2. Using the Volley to download image, which creates
 */

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MAIN_ACTIVITY";
    private String[] random = new String[]{"HAPPY!", "SAD!", "CRY!"};
    Button img_button;
    Button thread_button;
    TextView textView;
    private AtomicInteger count = new AtomicInteger(0);

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
    }

    public void onDownloadImage(View view) {
        Log.d(TAG, "Downloading Image...");
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

//    private class DownloadImage extends AsyncTask<>
}
