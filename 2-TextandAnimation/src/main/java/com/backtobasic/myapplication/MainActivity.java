package com.backtobasic.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);


    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Main", "Item selected: " + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClickFindWorkout(View view) {
        TextView textView = (TextView) findViewById(R.id.textView);
        switch (view.getId()) {
            case R.id.button:
                String s = String.valueOf(spinner.getSelectedItem());
                sb.append(s).append("\n");
        }
        textView.setText(sb.toString());

    }
}
