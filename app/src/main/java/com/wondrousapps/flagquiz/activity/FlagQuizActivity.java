package com.wondrousapps.flagquiz.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.wondrousapps.flagquiz.App;
import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.db.DatabaseOpenHelper;
import com.wondrousapps.flagquiz.model.FlagModel;
import com.wondrousapps.flagquiz.utils.AppLanguageUtils;

public class FlagQuizActivity extends AppCompatActivity {
    FlagModel model;
    List<FlagModel> flagModels;
    ImageView flag_holder;
    TextView total_que;
    String path_name;
    TextView timer;
    int total=10,remaining=10;
    int score=0;
    MyCountDownTimer myCountDownTimer;
    TextView optA,optB,optC,optD,Ans;
    int questionId;
    int position=1;
    int resID;
    int attempt=0;
    String check;
    String checkto;
    String id;//.
    Boolean onA=false,onB=false,onC=false,onD=false;
    LinearLayout group;
    //RadioButton answer;
    DatabaseOpenHelper helper;
    Button next;
    // public static final String TEST_DEVICE_ID = "DB07DDA7FB3B42C3FD6DC85ACBD34693";
    private AdView mBannerAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_quiz);
        getSupportActionBar().setElevation(0);
        //hi_IN en, de_DE,it_IT,fr_FR

        id = String.valueOf(Locale.getDefault());

        Log.d("lang_demo", String.valueOf(Locale.getDefault()));
        flagModels=new ArrayList<FlagModel>();
        mBannerAd = (AdView) findViewById(R.id.banner_ad3);
        AdRequest adRequest = new AdRequest.Builder()
                .build();


        mBannerAd.loadAd(adRequest);
        mBannerAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                //    Toast.makeText(getApplicationContext(), "Closing the Banner Ad", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onAdLoaded() {
                //  Toast.makeText(getApplicationContext(), "Banner Ad is loaded", Toast.LENGTH_LONG).show();
            }
        });
        myCountDownTimer = new MyCountDownTimer(120000, 1000);
        myCountDownTimer.start();
        timer= (TextView) findViewById(R.id.test_timer);
        flag_holder= (ImageView) findViewById(R.id.flag_holder);
        optA= (TextView) findViewById(R.id.option_a);
        optB= (TextView) findViewById(R.id.option_b);
        optC= (TextView) findViewById(R.id.option_c);
        optD= (TextView) findViewById(R.id.option_d);
        next= (Button) findViewById(R.id.next_btn);
        group= (LinearLayout) findViewById(R.id.option_list);
        total_que= (TextView) findViewById(R.id.total_que);
        total_que.setText(remaining+"/"+total);

        helper = new DatabaseOpenHelper(this);
        Log.d("path",String.valueOf(Locale.getDefault()));
        flagModels=helper.getPoses(String.valueOf(Locale.getDefault()));

        Log.d("img",resID+"");
        if (flagModels!=null && flagModels.size()!=0){
            model=flagModels.get(position);
            path_name=model.getImg().substring(4).replace("-","_");
            Log.d("my path",path_name);
            Log.d("score", "Your score" + score);
        }
        setModelView();

        optA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onA=true;
                onB=false;
                onC=false;
                onD=false;
                if (questionId < 10){
                    optB.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optC.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optD.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optA.setBackgroundColor(Color.parseColor("#00E676"));
                }
            }
        });

        optB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                onB=true;
                onA=false;
                onC=false;
                onD=false;
                if (questionId < 10){
                    optA.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optC.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optD.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optB.setBackgroundColor(Color.parseColor("#00E676"));


                }
            }
        });

        optC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onC=true;
                onA=false;
                onB=false;
                onD=false;
                if (questionId < 10){
                    optA.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optB.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optD.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optC.setBackgroundColor(Color.parseColor("#00E676"));
                }
            }
        });

        optD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onD=true;
                onA=false;
                onB=false;
                onC=false;
                if (questionId < 10){
                    optA.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optB.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optC.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    optD.setBackgroundColor(Color.parseColor("#00E676"));
                    //    score++;

                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt++;
                optA.setBackgroundColor(Color.parseColor("#FFFFFF"));
                optB.setBackgroundColor(Color.parseColor("#FFFFFF"));
                optC.setBackgroundColor(Color.parseColor("#FFFFFF"));
                optD.setBackgroundColor(Color.parseColor("#FFFFFF"));
                remaining=remaining-1;
                total_que.setText(remaining+"/"+total);

                if (position <= 10) {
                    if (onA==true){
                        if (model.getAns().equals(optA.getText())) {
                            checkto=optA.getText().toString();
                            score++;
                        }
                    }
                    if (onB==true){
                        if (model.getAns().equals(optB.getText())) {
                            checkto=optB.getText().toString();
                            score++;
                        }
                    }
                    if (onC==true){
                        if (model.getAns().equals(optC.getText())) {
                            checkto=optC.getText().toString();
                            score++;
                        }
                    }if (onD==true){
                        if (model.getAns().equals(optD.getText())){
                            checkto=optD.getText().toString();
                            score++;
                        }
                    }

                    //check = (String) answer.getText();
//                        String checkBy=model.getAns();
//                        Log.d("ans1",checkBy);
//                        if (checkBy.equals(check)) {
//                            score++;
//                            Log.d("score", score+":"+check);
//                        }
                    Log.d("score", ""+check);
                    model = flagModels.get(position);
//                        position++;
//                        resID++;
                    //model=flagModels.get(position);
                    path_name=model.getImg().substring(4).replace("-","_");
                    //Log.d("path",path_name);
                    // Log.d("model",model.getAns());
                    //Log.d("ans2",answer.toString());
                    resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
                    flag_holder.setImageDrawable(getResources().getDrawable(resID));
                    optA.setText(model.getOptA());
                    optB.setText(model.getOptB());
                    optC.setText(model.getOptC());
                    optD.setText(model.getOptD());
                    position++;

                    //setQuestionView();
                } else {
                    myCountDownTimer.cancel();
                    Intent intent = new Intent(FlagQuizActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    b.putInt("total",10);
                    b.putInt("attempt",attempt);
                    b.putInt("postion",position);
                    Log.d("score",score+":"+position);
                    Log.d("total",flagModels.size()+"");
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void setModelView() {
        resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
        flag_holder.setImageDrawable(getResources().getDrawable(resID));
        optA.setText(model.getOptA());
        optB.setText(model.getOptB());
        optC.setText(model.getOptC());
        optD.setText(model.getOptD());
        position++;
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            int seconds = (int) (millisUntilFinished / 1000) % 60 ;
            int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
          //  Toast.makeText(getApplicationContext(),""+id,Toast.LENGTH_SHORT).show();
            if (id.equals("en")){
                timer.setText(getResources().getString(R.string.time_left)+minutes+":"+seconds);
            }else
            if (id.equals("hi_IN")){
                timer.setText(getResources().getString(R.string.time_left_hi)+minutes+":"+seconds);
            }else
            if (id.equals("de_DE")){
                timer.setText(getResources().getString(R.string.time_left_de)+minutes+":"+seconds);
            }else
            if (id.equals("it_IT")){
                timer.setText(getResources().getString(R.string.time_left_es)+minutes+":"+seconds);
            }else
            if (id.equals("fr_FR")){
                timer.setText(getResources().getString(R.string.time_left_fr)+minutes+":"+seconds);
            }

            //hi_IN en, de_DE,it_IT,fr_FR
            Log.d("time", String.valueOf(timer.getText()));
            //progressBar.setProgress((int) (progressBar.getMax()-millisUntilFinished/15000));
        }
        @Override
        public void onFinish() {
            timer.setText(getResources().getString(R.string.time_finish));
            //progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"times up your score:"+score,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(FlagQuizActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score); //Your score
            b.putInt("total",15);
            b.putInt("attempt",attempt);
            b.putInt("postion",position);
            intent.putExtras(b); //Put your score to your next Intent
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myCountDownTimer.cancel();
        Intent intent=new Intent(getApplicationContext(),StartActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        myCountDownTimer.cancel();
        super.onPause();
    }

    @Override
    protected void onStop() {
        myCountDownTimer.cancel();
        super.onStop();
    }
}
