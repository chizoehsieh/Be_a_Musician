package com.example.finalproject0531;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    Button mButton;
    TextView mTextViewScore,mTextViewMaxcombo,mTextViewGreat,mTextViewGood,mTextViewBad,mTextViewMiss;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mTextViewBad = findViewById(R.id.textView21);
        mTextViewGood = findViewById(R.id.textView19);
        mTextViewGreat = findViewById(R.id.textView17);
        mTextViewMaxcombo = findViewById(R.id.textView15);
        mTextViewMiss = findViewById(R.id.textView23);
        mTextViewScore = findViewById(R.id.textView13);
        mButton = findViewById(R.id.button9);

        Bundle bundleFromPlay = this.getIntent().getExtras();
        int score = bundleFromPlay.getInt("Score");
        int maxcombo = bundleFromPlay.getInt("Maxcombo");
        int great = bundleFromPlay.getInt("Great");
        int good = bundleFromPlay.getInt("Good");
        int miss = bundleFromPlay.getInt("Miss");
        int bad = bundleFromPlay.getInt("Bad");

        mTextViewBad.setText(Integer.toString(bad));
        mTextViewScore.setText(Integer.toString(score));
        mTextViewMiss.setText(Integer.toString(miss));
        mTextViewMaxcombo.setText(Integer.toString(maxcombo));
        mTextViewGreat.setText(Integer.toString(great));
        mTextViewGood.setText(Integer.toString(good));

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this,ChooseSong.class);
                startActivity(intent);
            }
        });
    }
}