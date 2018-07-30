package com.wondrousapps.flagquiz.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jackandphantom.circularprogressbar.CircleProgressbar;

import com.wondrousapps.flagquiz.LocaleHelper;
import com.wondrousapps.flagquiz.R;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    TextView textView,totalque;
    TextView total_ask,total_wrong,attempt_que;
    int persent;
    int postion;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d("lang_demo", String.valueOf(Locale.getDefault()));
        id=String.valueOf(Locale.getDefault());
        Log.d("lang_demo_id",id);
        //hi_IN en, de_DE,it_IT,fr_FR
        Bundle b = getIntent().getExtras();
        final int score = b.getInt("score",0);
        final int attempt = b.getInt("attempt",0);
        final int total = b.getInt("total");
        postion = b.getInt("postion",0);
        textView= (TextView) findViewById(R.id.result);
        totalque= (TextView) findViewById(R.id.total_percent);
        total_ask= (TextView) findViewById(R.id.total_ask);
        total_wrong= (TextView) findViewById(R.id.worng_ans);
        attempt_que= (TextView) findViewById(R.id.attempt_que);
//        review_test= (TextView) findViewById(R.id.review_test);
        Locale.getDefault();

        persent = (score*100)/total;
        totalque.setText(persent+"%");

        if (id.equals("en")){
            total_ask.setText(getResources().getString(R.string.total_que)+total+"");
            attempt_que.setText(getResources().getString(R.string.attempt)+attempt);
        }
        if (id.equals("hi_IN")){
            total_ask.setText(getResources().getString(R.string.total_que_hi)+total+"");
            attempt_que.setText(getResources().getString(R.string.attempt_hi)+attempt);
        }if (id.equals("de_DE")){
            total_ask.setText(getResources().getString(R.string.total_que_de)+total+"");
            attempt_que.setText(getResources().getString(R.string.attempt_de)+attempt);
        }if (id.equals("it_IT")){
            total_ask.setText(getResources().getString(R.string.total_que_es)+total+"");
            attempt_que.setText(getResources().getString(R.string.attempt_es)+attempt);
        }if (id.equals("fr_FR")){
            total_ask.setText(getResources().getString(R.string.total_que_fr)+total+"");
            attempt_que.setText(getResources().getString(R.string.attempt_fr)+attempt);
        }

        CircleProgressbar circleProgressbar = (CircleProgressbar)findViewById(R.id.circularProgressBar);
//        if (persent<=25){
//            circleProgressbar.setForegroundProgressColor(Color.RED);
//        }else if (persent<40){
//            circleProgressbar.setForegroundProgressColor(Color.YELLOW);
//        }else if (persent<70){
//            circleProgressbar.setForegroundProgressColor(Color.BLUE);
//        }else {
//            circleProgressbar.setForegroundProgressColor(Color.GREEN);
//        }
        circleProgressbar.setBackgroundColor(Color.WHITE);
        circleProgressbar.enabledTouch(false);
        circleProgressbar.setRoundedCorner(true);
        circleProgressbar.setClockwise(false);
        int animationDuration = 2000; // 2500ms = 2,5s
        circleProgressbar.setProgressWithAnimation(persent, animationDuration); // Default duration = 1500ms

        if (id.equals("en")){
            textView.setText(getResources().getString(R.string.score)+score+"");
            total_wrong.setText(getResources().getString(R.string.worng)+(total-score));
        }
        if (id.equals("hi_IN")){
            textView.setText(getResources().getString(R.string.score_hi)+score+"");
            total_wrong.setText(getResources().getString(R.string.worng_hi)+(total-score));
        }if (id.equals("de_DE")){
            textView.setText(getResources().getString(R.string.score_de)+score+"");
            total_wrong.setText(getResources().getString(R.string.worng_de)+(total-score));
        }if (id.equals("it_IT")){
            textView.setText(getResources().getString(R.string.score_es)+score+"");
            total_wrong.setText(getResources().getString(R.string.worng_es)+(total-score));
        }if (id.equals("fr_FR")){
            textView.setText(getResources().getString(R.string.score_fr)+score+"");
            total_wrong.setText(getResources().getString(R.string.worng_fr)+(total-score));
        }


//        scoretv.setText(score);
    }
    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(getApplicationContext(),StartActivity.class);
        startActivity(intent1);
        super.onBackPressed();
    }
}
