package com.example.finalproject0531;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuthException;

import java.io.IOException;

public class PlaySong extends AppCompatActivity {
    float songBpm,secPerBeat,songPosition,songPositionInBeats,getSongPositonInBeat,dspSongTime;
    long a;
    double[] music2TrumpetHardNotes = {77,77.5,78,78.5,79,79.5,80,80.5,81,81.5,82,82.5,83,83.5,84,84.5,85,86,87,88.5,89,89.5,90,90.5,91,91.5,92,92.5,93,96,96.5,97,97.5,98,98.5,99,99.5,100,100.5,101,102,103,112,112.5,113,113.5,114,114.5,115,115.5,116,116.5,117,121,121.5,122,122.5,123,164.5,165,169,170,172.5,173,177,178,179,180,181,185,186,188,188.5,189,189.5,190,190.5,191,191.5,192,192.5,193,194,195,196,197,199,200.5,200.75,201,201.5,202,203,204,204.5,205,206,206.5,208,208.5,209,210.5,211,213,214,215,216,217,217.5,218,218.5,219,219.5,220,220.5,221,222,223,224.5,225,225.5,226,226.5,227,227.5,228,228.5,229,231.5,232,232.5,233,233.5,234,234.5,235,235.5,236,236.5,237,238,240,240.5,241,241.5,242,242.5,243,243.5,244,244.5,245,256,256.5,257,257.5,258,258.5,259,259.5,260};  //困難等級的音符位置
    double[] music2TrumpetHardNotesLength = {0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,1,1,1.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,3,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,1,1,1,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,4,0.5,0.5,0.5,0.5,2,0.5,4,1,2,0.5,4,1,1,1,1,4,1,2,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,1,1,1,1,2,1.5,0.25,0.25,0.5,0.5,1,1,0.5,0.5,1,0.5,1.5,0.5,0.5,1.5,0.5,2,1,1,1,1,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,1,1.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,2,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.51,2,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,3,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,1};  //困難版的音符長度
    double[] music2TrumpetHardNotesSound = {0.79,0.89,0.89,0.79,0.79,0.89,1,1.19,1.59,1.5,1.5,1.33,1.33,1.19,1,0.89,1,1.19,1.33,1.19,0.79,1.5,1.5,1.33,1.33,1.19,1.19,1,1.19,1,1.19,1.59,1.5,1.5,1.33,1.33,1.19,1.19,1.33,1.19,0.79,0.79,1.33,1.59,1.78,2,2,1.78,1.78,1.59,1.33,1.59,1.59,1.33,1.5,1.5,1.33,1.33,1.19,1.59,1.78,1.33,1.19,1.59,1.5,1.19,1.33,1.5,1.59,1.78,1.33,1.59,2,1.78,1.59,1.59,2,1.78,1.59,1.33,1.59,1.59,1.5,1.33,1.19,1.19,1.5,1.33,1.19,1.19,1.33,1.19,1.59,1.33,1.59,1.78,2,1.78,1.33,1.59,2,2,1.78,1.33,1.5,1.59,1.78,1.59,1.5,1.5,1.33,1.33,1.19,1,0.89,1,1.19,1.33,1.19,1.59,1.5,1.5,1.33,1.33,1.19,1.19,1,1.19,1,1,1.19,1.59,1.5,1.5,1.33,1.33,1.19,1.19,1.33,1.19,0.79,1.33,1.59,1.78,2,2,1.78,1.78,1.59,1.59,1.33,1.78,1.19,1.33,1.59,1.5,1.33,1.19,1,0.89,0.79};
    double[] music2TrumpetNormalNotes = {77,77.5,78,78.5,79,79.5,80,80.5,81,83,85,86,87,88,89,89.5,90,90.5,91,91.5,92,92.5,93,96,96.5,97,99,101,102,103,113,115,117,121,121.5,122,122.5,123,164.5,165,169,170,172.5,173,177,178,179,180,181,185,186,189,191,193,194,195,196,197,199,201,203,205,206,206.5,208,208.5,209,210.5,211,213,214,215,216,217,220,220.5,221,222,223,224,225,229,231.5,232,232.5,233,235,237,238,241,245,256,256.5,257,259,259.5,260};    //中等版的音符位置
    double[] getMusic2TrumpetNormalNotesLength = {0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,2,2,1,1,1,1,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,3,0.5,0.5,2,2,1,1,1,2,2,4,0.5,0.5,0.5,0.5,2,0.5,4,1,2,0.5,4,1,1,1,1,4,1,3,2,2,1,1,1,1,2,2,2,2,1,0.5,1.5,0.5,0.5,1.5,0.5,2,1,1,1,1,3,0.5,0.5,1,1,1,1,4,2,0.5,0.5,0.5,2,2,1,2,4,3,0.5,0.5,2,0.5,0.5,1};   //中等版音符長度
    double[] music2TrumpetNormalSounds = {0.79,0.89,0.89,0.79,0.79,0.89,1,1.19,1.33,1,1,1,1.19,1.06,1.59,1.5,1.5,1.33,1.33,1.19,1.19,1,1,1,1.19,1.19,0.89,1,0.79,0.79,1.19,1.06,1,0.79,1,1,0.79,0.79,1.19,1.19,1.33,1,1.19,1.19,1.19,1,1.11,1.19,1,1.33,1.06,1.33,1.33,1.19,1.19,1.19,1,1,1.06,0.89,1,1.41,1.41,1.41,1.33,1.59,1.33,1.33,1.5,1.06,1.19,1.33,1.5,1,1,0.89,1,1,1.19,1.06,1,1,1,1,1.19,1.06,0.89,1,0.79,1.33,1.19,1.19,1.33,1,1,0.89,0.79};   //中等版按鍵音
    double[] music2BaritoneHardNotes = {1,5,9,10.5,11,13,14.5,15,17,18.5,19,21,22.5,23,25,26.5,27,29,30.5,31,33,34.5,35,37,38.5,39,41,42.5,43,45,46.5,47,49,50.5,51,53,54.5,55,57,58.5,59,61,62.5,63,65,66.5,67,69,70.5,71,73,74.5,75,77,79,80,81,83,85,87,89,91,93,94.5,95,97,99,101,105,107,109,110.5,111,113,115,117,120,121,124,125,126.5,127,129,130.5,131,132,133,133.5,134,134.5,135,135.5,136,136.5,137,137.5,138,138.5,139,139.5,140,140.5,141,141.5,142,142.5,143,143.5,144,144.5,145,145.5,146,146.5,147,147.5,148,148.5,149,149.5,150,150.5,151,151.5,152,152.5,153,153.5,154,154.5,155,155.5,156,156.5,157,157.5,158,158.5,159,159.5,160,160.5,161,161.5,162,162.5,163,163.5,164,165,166.5,167,169,170.5,171,173,174.5,175,177,178.5,179,181,182.5,183,185,186.5,187,189,191,193,194.5,195,197,199,201,203,205,209,211,213,214,215,216,217,218.5,219,221,223,225,226.5,227,229,230.5,231,233,234.5,235,237,239,241,243,245,249,251,253,254,255,257,259,260,261,264,265,268,269,271,277};  //Baritone困難等級音符位置
    int mImageArray[] = {R.drawable.empty,R.drawable.half_note,R.drawable.dot_half_note, R.drawable.quarter_note,R.drawable.dot_quarter_note, R.drawable.eighth_note,R.drawable.whole_note};
    //int music2TrumpetNormalImg[]={3,3,3,3,3,3,3,3,1,1,0,0,0,3,0,0,0,3,0,3,0,3,0,3,0,3,3,3,3,3,3,3,2,3,0,0,0,0,0,3,1,1,0,0,0,3,0,0,0,3,0,3,0,1,0,1,0,0,0,6,0,0,0,3,0,0,0,0,0,0,0,3,3,3,1,5,0,0,0,6,3,0,0,0,0,0,0,0,1,0,5,0,0,0,6,3,0,0,0,0,0,0,0,3,0,3,0,3,0,6,0,3,0,0,0,0,0,0,0,2,0,1,0,0,0,0,0,1,0,0,0,3,0,0,0,3,0,3,0,3,0,1,0,1,0,0,0,1,0,0,0,1,0,0,0,3,0,0,0,3,0,3,3,0,0,3,3,4,0,0,5,1,0,0,0,3,0,3,0,3,0,3,0,2,0,0,0,0,0,3,3,3,0,3,0,3,0,3,0,6,0,0,0,0,0,0,0,1,0,0,0,5,3,3,1,0,0,0,1,0,0,0,3,0,1,0,0,0,6,0,0,0,0,0,0,0,2,0,0,0,0,0,3,3,1,0,0,0,3,3,3,0,0,0,0,0,0,0,0,0};
    //double[] music2TrumpetNormalFirstImg[];
    int music2TrumpetNormalImg[] = {5,5,5,5,5,5,5,5,1,1,3,3,3,3,5,5,5,5,5,5,5,5,2,5,5,1,1,3,3,3,1,1,6,5,5,5,5,1,5,6,3,1,5,6,3,3,3,3,6,3,2,1,1,3,3,3,3,1,1,1,1,3,5,4,5,5,4,5,1,3,3,3,3,2,5,5,3,3,3,3,6,1,5,5,5,1,1,3,1,6,2,5,5,1,5,5,3};
    int[] beatspicture={R.drawable.fourthbeats,R.drawable.firstbeat,R.drawable.secondbeats,R.drawable.thirdbeats};
    int i,combo,score,maxcombo,great,good,bad,miss,x,y,z;
    private Handler mHandler = new Handler();    //控制update()
    private Handler mHandler2 = new Handler();
    double[][] music2TrumpetNotes = {music2TrumpetHardNotes,music2TrumpetNormalNotes};
    double[][] music2TrumpetSounds = {music2TrumpetHardNotesSound,music2TrumpetNormalSounds};
    double[][] music2BaritoneNotes = {music2BaritoneHardNotes};
    double[][] getMusic2BaritoneSounds = {};
    double[][][] music2Notes = {music2TrumpetNotes,music2BaritoneNotes};
    double[][][] music2Sounds = {music2TrumpetSounds,getMusic2BaritoneSounds};
    double[][][][] allMusicNotes = {music2Notes};
    double[][][][] allMusicSounds = {music2Sounds};
    int[] allSounds = {R.raw.trumpet2};
    double sec;
    MediaPlayer mediaPlayer;                     //放音樂
    TextView mTextView1,mTextView2,mTextView3;   //顯示打擊等級,分數,combo數
    ImageButton mImageButton;                    //打擊按鈕
    ImageView mImageView,mImageView1,mImageView2,mImageView3,mImageView4,mImageView5,mImageView6,mImageView7,mImageView8,mImageView13;
    String[] musicLink = {"https://firebasestorage.googleapis.com/v0/b/finalproject10902.appspot.com/o/Colors%20of%20the%20Wind.wav?alt=media&token=5c672614-21f3-4586-b1ff-9e6ef0086607","https://firebasestorage.googleapis.com/v0/b/finalproject10902.appspot.com/o/Beauty%20and%20the%20Beast.wav?alt=media&token=4cb60fd4-bf52-41f1-8d69-0b063dd1427c"};



    int instrument;
    int rank;
    int music;

    Bundle bundleToResult = new Bundle();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        mTextView1 = findViewById(R.id.textView);
        mTextView2 = findViewById(R.id.textView2);
        mTextView3 = findViewById(R.id.textView3);
        mImageButton = findViewById(R.id.imageButton);
        mImageView = findViewById(R.id.imageView);
        mImageView1 = findViewById(R.id.imageView2);
        mImageView2 = findViewById(R.id.imageView3);
        mImageView3 = findViewById(R.id.imageView4);
        mImageView4= findViewById(R.id.imageView5);
        mImageView5 = findViewById(R.id.imageView6);
        mImageView6 = findViewById(R.id.imageView7);
        mImageView7 = findViewById(R.id.imageView8);
        mImageView8 = findViewById(R.id.imageView9);
        mImageView13 = findViewById(R.id.imageView13);
        i=0;                                          //下一個音符是陣列中第幾個
        combo=0;
        score=0;
        maxcombo=0;           //最大連擊數
        great=0;              //打擊為great的數量
        good=0;               //打擊為good的數量
        miss=0;               //打擊為miss的數量
        bad=0;                //打擊為bad的數量
        songBpm = 100;        //這首歌的速度
        secPerBeat = 60 / songBpm;

        Bundle bundleFromChoose = this.getIntent().getExtras();
        instrument = bundleFromChoose.getInt("instrument");
        rank = bundleFromChoose.getInt("rank");
        music = bundleFromChoose.getInt("music");

        Uri uri = Uri.parse(musicLink[music]);
        if(mediaPlayer != null)
        {
            mediaPlayer.stop();
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(PlaySong.this, uri);
        mHandler.postDelayed(runnable3,3000);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                try {
                    Thread.sleep(3000);                       //延遲3秒後才跳轉畫面
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(PlaySong.this,Result.class);
                bundleToResult.putInt("Score",score);
                bundleToResult.putInt("Maxcombo",maxcombo);
                bundleToResult.putInt("Great",great);
                bundleToResult.putInt("Good",good);
                bundleToResult.putInt("Miss",miss);
                bundleToResult.putInt("Bad",bad);
                intent.putExtras(bundleToResult);
                startActivity(intent);
            }
        });

        SoundPool snd = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        int sound = snd.load(PlaySong.this,allSounds[instrument],1);

        mHandler.post(runnable);                               //啟動runnable()
        sec=music2TrumpetNormalNotes[0]*secPerBeat*1000-2700;
        mHandler2.post(runnable2);
        mHandler2.postDelayed(runnable2,(long) sec);



        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<allMusicNotes[music][instrument][rank].length)                //若音符還沒打擊完
                {
                    double m = allMusicNotes[music][instrument][rank][i]*secPerBeat*1000;
                    if(m-songPosition > 500 && m-songPosition < 600)        //m和songPosition大概誤差550左右，在音符的前50~後50時間內點擊，判定為Great
                    {
                        snd.play(sound,1,1,1,0, (float) allMusicSounds[music][instrument][rank][i]);
                        mTextView1.setText("Great");
                        combo+=1;
                        score+=5;
                        great+=1;
                        mTextView2.setText(Integer.toString(score));
                        mTextView3.setText(Integer.toString(combo));
                        i+=1;
                    }
                    else if(m-songPosition > 300 && m-songPosition < 700)      //m和songPosition大概誤差550左右，在音符的前250~後250時間內點擊，判定為Great
                    {
                        snd.play(sound,1,1,1,0, (float) music2TrumpetSounds[rank][i]);
                        mTextView1.setText("Good");
                        combo+=1;
                        score+=3;
                        good+=1;
                        mTextView2.setText(Integer.toString(score));
                        mTextView3.setText(Integer.toString(combo));
                        i+=1;
                    }
                    else if(m-songPosition > 250 && m-songPosition < 800)    //m和songPosition大概誤差550左右，在音符的前350~後400時間內點擊，判定為Great
                    {
                        snd.play(sound,1,1,1,0, (float) music2TrumpetSounds[rank][i]);
                        mTextView1.setText("Bad");
                        combo=0;
                        score-=2;
                        bad+=1;
                        if(score<0)
                        {
                            score=0;
                        }
                        mTextView2.setText(Integer.toString(score));
                        mTextView3.setText("");
                        i+=1;
                    }
                    else
                    {
                        mTextView1.setText("Time out");
                    }
                }

            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                songPosition = (mediaPlayer.getCurrentPosition());          //音樂撥放到哪裡
                songPositionInBeats = songPosition / (secPerBeat*1000);           //暫時用不到，本來是算音樂放到第幾拍，可是因為單位，所以誤差值很大
                int s = (int) ((songPositionInBeats+1)%4);
                mImageView13.setImageResource(beatspicture[s]);
                if(i<allMusicNotes[music][instrument][rank].length)
                {
                    double m = allMusicNotes[music][instrument][rank][i]*secPerBeat*1000;
                    if(m-songPosition<200)                                //若在音符後400沒有點擊，判定為miss
                    {
                        mTextView1.setText("Miss");
                        i+=1;
                        combo=0;
                        score-=4;
                        miss+=1;
                        if(score<0)
                        {
                            score=0;
                        }
                        mTextView2.setText(Integer.toString(score));
                        mTextView3.setText("");
                    }
                }
                if(combo>maxcombo)
                {
                    maxcombo = combo;                                     //計算最大連擊數
                }

                mHandler.postDelayed(runnable,1);              //每一毫秒執行一次runnable()
            }
            catch (Exception e)
            {

            }

        }
    };

//    private Runnable runnable2 = new Runnable() {
//        @Override
//        public void run() {
//            ImageView[] image ={mImageView,mImageView1,mImageView2,mImageView3,mImageView4,mImageView5,mImageView6,mImageView7,mImageView8};
//            a = (long) (secPerBeat/2*1000);
//            try {
//                if()
//            }
    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            ImageView[] image ={mImageView,mImageView1,mImageView2,mImageView3,mImageView4,mImageView5,mImageView6,mImageView7,mImageView8};
            a = (long) (secPerBeat/2*1000);
            try {
                    image[0].setImageResource(mImageArray[music2TrumpetNormalImg[x]]);
                    if (y>music2TrumpetNormalImg.length) {
                        mHandler2.removeCallbacks(this);
                    }else{
                        mHandler2.postDelayed(this, a);
                        if (x<image.length)
                        {
                            for(y=x;y>0;y--)
                            {
                                z=x;
                                image[y].setImageResource(mImageArray[music2TrumpetNormalImg[z-y]]);
                                z--;
                            }
                        }else {
                            for(y=1;y<9;y++)
                            {
                                image[y].setImageResource(mImageArray[music2TrumpetNormalImg[x-y]]);
                            }
                        }
                        x++;
                    }
            }
            catch (Exception e)
            {

            }
        }
    };

    private Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
            mediaPlayer.start();
            mHandler.post(runnable);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer != null)
        {
            mediaPlayer.release();
        }
    }
}

