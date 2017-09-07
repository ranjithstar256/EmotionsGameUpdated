package com.androidmanifester.simpleemotionsgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class SelectModeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RatingBar ratingBar;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
        sharedPreferences = getSharedPreferences("sfname", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setProgress(1);

    }

    public void gotogame(View view) {
        float t = ratingBar.getRating();
        int r = (int) t;
        Toast.makeText(this, t + "   " + r, Toast.LENGTH_SHORT).show();
        editor.putInt("skilevel", r).putString("mode", "Game").commit();
        startActivity(new Intent(SelectModeActivity.this, AboutGameMode.class));
    }

    public void gotolearn(View view) {
        float t = ratingBar.getRating();
        int r = (int) t;

        editor.putInt("skilevel", r).putString("mode", "Learning").commit();
        Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SelectModeActivity.this, AboutLearningMode.class));

    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
