package com.backtobasic.myapplication;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Toolbar toolbar;
    StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ConstraintLayout view = (ConstraintLayout) findViewById(R.id.main_view);
        switch (item.getItemId()) {
            case R.id.red:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                view.setBackgroundColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Red", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.blue:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                view.setBackgroundColor(Color.BLUE);
                Toast.makeText(getApplicationContext(), "Blue", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.green:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                view.setBackgroundColor(Color.GREEN);
                Toast.makeText(getApplicationContext(), "Green", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
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

    public void onStartAnimation(View view) {
        TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.animate_view));
        Button button = (Button) findViewById(R.id.animation_button);

        RelativeLayout.LayoutParams positions = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        positions.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        button.setLayoutParams(positions);
    }
}
