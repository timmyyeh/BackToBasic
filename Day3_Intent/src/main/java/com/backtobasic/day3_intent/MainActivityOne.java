package com.backtobasic.day3_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivityOne extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);

        findViewById(R.id.button_main_one).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_main_one) {
            Intent intent = new Intent(this, MainActivityTwo.class);
            intent.putExtra("message", ((EditText)findViewById(R.id.editText)).getText().toString());
            startActivity(intent);
        }
    }
}
