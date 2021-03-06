package com.backtobasic.day3_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivityTwo extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        findViewById(R.id.button_main_two).setOnClickListener(this);

        // set the text from intent
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        String mes = bundle.getString("message");
        TextView textView = (TextView) findViewById(R.id.textView_two);
        textView.setText(mes);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_main_two) {
            Intent intent = new Intent(this, MainActivityOne.class);
            startActivity(intent);
        }
    }
}
