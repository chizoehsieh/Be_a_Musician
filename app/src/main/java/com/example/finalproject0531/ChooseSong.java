package com.example.finalproject0531;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseSong extends AppCompatActivity {
    Button mButton,mButton2,mButton3,mButton4,mButton5,mButton6,mButton7;
    ImageView mImageView;
    TextView mTextView1,mTextView2;
    int i=0,j=0,k=0;
    int[] img=new int[]{R.drawable.trumpet1,R.drawable.baritone1,R.drawable.clarinet1,R.drawable.drumset1,R.drawable.flute1,
            R.drawable.horn1,R.drawable.sax1,R.drawable.trombone1,R.drawable.tuba};
    String[] level=new String[]{"困難","中等","簡單"};
    String[] music=new String[]{"風中奇緣","美女與野獸",""};

    Bundle bundletoPlay = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_song);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        mButton = findViewById(R.id.button2);
        mButton2 = findViewById(R.id.button3);
        mButton3 = findViewById(R.id.button4);
        mButton4 = findViewById(R.id.button5);
        mButton5 = findViewById(R.id.button6);
        mButton6 = findViewById(R.id.button7);
        mButton7 = findViewById(R.id.button8);
        mTextView1 = findViewById(R.id.textView7);
        mTextView2 = findViewById(R.id.textView8);
        mImageView = findViewById(R.id.imageView12);
        mImageView.setImageResource(img[0]);
        mTextView2.setText(level[0]);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChooseSong.this,PlaySong.class);
                bundletoPlay.putInt("instrument",i);
                bundletoPlay.putInt("rank",j);
                bundletoPlay.putInt("music",k);
                intent.putExtras(bundletoPlay);
                startActivity(intent);
            }
        });
    }

    public void img_last(View view) {
        if (i>0)
        {
            i--;
            mImageView.setImageResource(img[i]);

        }
        else
        {
            i=img.length-1;
            mImageView.setImageResource(img[i]);
        }
    }

    public void img_next(View view) {
        if (i<img.length-1)
        {
            i++;
            mImageView.setImageResource(img[i]);
        }
        else
        {
            i=0;
            mImageView.setImageResource(img[i]);
        }
    }

    public void level_last(View view) {
        if (j>0)
        {
            j--;
            mTextView2.setText(level[j]);

        }
        else
        {
            j=level.length-1;
            mTextView2.setText(level[j]);
        }
    }

    public void level_next(View view) {
        if (j<level.length-1)
        {
            j++;
            mTextView2.setText(level[j]);

        }
        else
        {
            j=0;
            mTextView2.setText(level[j]);
        }
    }

    public void music_last(View view) {
        if (k>0)
        {
            k--;
            mTextView1.setText(music[k]);

        }
        else
        {
            k=music.length-1;
            mTextView1.setText(music[k]);
        }
    }

    public void music_next(View view) {
        if (k<music.length-1)
        {
            k++;
            mTextView1.setText(music[k]);

        }
        else
        {
            k=0;
            mTextView1.setText(music[k]);
        }
    }
}