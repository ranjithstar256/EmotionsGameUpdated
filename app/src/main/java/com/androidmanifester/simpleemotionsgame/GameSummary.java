package com.androidmanifester.simpleemotionsgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class GameSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_summary);
        ((TextView) findViewById(R.id.textView15)).setText(getIntent().getStringExtra("scr"));
    }

    public void gotohome(View view) {
        startActivity(new Intent(GameSummary.this, GameMode.class));

    }
}
