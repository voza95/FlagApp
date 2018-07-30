package com.wondrousapps.flagquiz.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.db.DatabaseOpenHelper;
import com.wondrousapps.flagquiz.model.FlagModel;

public class LifeFlagQuizActivity extends AppCompatActivity{

ImageView flag_holder;
TextView optA,optB,optC,optD;
TextView life_left,total_flag;
Button next;
String path_name;
int position=0;
int resID;
int score=0;
int life=5;
int attempt=0;
DatabaseOpenHelper helper;
int total,remaining;
int questionId;
FlagModel model;
List<FlagModel> flagModels;
int posin;
int adcount=0;
private RewardedVideoAd mRewardedVideoAd;
    //public static final String TEST_DEVICE_ID = "DB07DDA7FB3B42C3FD6DC85ACBD34693";
    private AdView mBannerAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_flag_quiz);
        getSupportActionBar().setElevation(0);
        mBannerAd = (AdView) findViewById(R.id.banner_ad4);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
      //          .addTestDevice(TEST_DEVICE_ID)


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
        flagModels=new ArrayList<FlagModel>();
        life_left= (TextView) findViewById(R.id.life_left);
        //life_left.setText(life);
        flag_holder= (ImageView) findViewById(R.id.life_flag_holder);
        total_flag= (TextView) findViewById(R.id.count_total);
        optA= (TextView) findViewById(R.id.opta);
        optB= (TextView) findViewById(R.id.optb);
        optC= (TextView) findViewById(R.id.optc);
        optD= (TextView) findViewById(R.id.optd);
        next= (Button) findViewById(R.id.life_next_btn);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        helper = new DatabaseOpenHelper(this);
        flagModels=helper.getPoses(String.valueOf(Locale.getDefault()));
        Log.d("test",String.valueOf(Locale.getDefault()));
        Log.d("img",resID+"");
        if (flagModels!=null && flagModels.size()!=0){
            model=flagModels.get(position);
            path_name=model.getImg().substring(4).replace("-","_");
            Log.d("path",path_name);
        }
        mRewardedVideoAd.loadAd(getString(R.string.ad_unit_id), new AdRequest.Builder().addTestDevice("DB07DDA7FB3B42C3FD6DC85ACBD34693").build());
        if (questionId==flagModels.size()){
            Intent intent = new Intent(LifeFlagQuizActivity.this, ResultActivity.class);
            helper.close();

            Bundle b = new Bundle();
            b.putInt("score", score); //Your score
            b.putInt("total",flagModels.size());
            b.putInt("postion",posin);
            b.putInt("attempt",attempt);
            Log.d("total",flagModels.size()+"");
            intent.putExtras(b); //Put your score to your next Intent
            startActivity(intent);
            finish();
        }
        answermethod();
    }
    private void answermethod() {
        if (flagModels!=null && flagModels.size()!=0){
            model=flagModels.get(position);
            Log.d("position",""+position);
            path_name=model.getImg().substring(4).replace("-","_");
            Log.d("path",path_name);
        }
        setModelView();
        optA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt++;
                optB.setClickable(false);
                optC.setClickable(false);
                optD.setClickable(false);
                if (questionId < flagModels.size()){
                    if (model.getAns().equals(optA.getText())){
                optA.setBackgroundColor(Color.parseColor("#00E676"));
                score++;
                    }else {
                        if (life>1){
                        life--;
                        life_left.setText(""+life);
                        optA.setBackgroundColor(Color.parseColor("#FF1744"));
                        if (model.getAns().equals(optB.getText())){optB.setBackgroundColor(Color.parseColor("#00E676"));}
                        if (model.getAns().equals(optC.getText())){optC.setBackgroundColor(Color.parseColor("#00E676"));}
                        if (model.getAns().equals(optD.getText())){optD.setBackgroundColor(Color.parseColor("#00E676"));}
                        }else {
                            life--;
                            life_left.setText(""+life);
                        optionCommen();
                        }
                    }
                }
            }
        });
        optB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt++;
                optA.setClickable(false);
                optC.setClickable(false);
                optD.setClickable(false);
                if (questionId < flagModels.size()){
                    if (model.getAns().equals(optB.getText())){
                    optB.setBackgroundColor(Color.parseColor("#00E676"));
                    score++;
                    }else {
                        if (life>1){
                            life--;
                            life_left.setText(""+life);
                    optB.setBackgroundColor(Color.parseColor("#FF1744"));
                    if (model.getAns().equals(optA.getText())){optA.setBackgroundColor(Color.parseColor("#00E676"));}
                    if (model.getAns().equals(optC.getText())){optC.setBackgroundColor(Color.parseColor("#00E676"));}
                    if (model.getAns().equals(optD.getText())){optD.setBackgroundColor(Color.parseColor("#00E676"));}
                        }else {
                            life--;
                            life_left.setText(""+life);
                        optionCommen();
                        }
                    }
                }
            }
        });
        optC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt++;
                optA.setClickable(false);
                optB.setClickable(false);
                optD.setClickable(false);
                if (questionId < flagModels.size()){
    if (model.getAns().equals(optC.getText())){
    optC.setBackgroundColor(Color.parseColor("#00E676"));
    score++;
    }else {
    if (life>1){
    life--;
    life_left.setText(""+life);
    optC.setBackgroundColor(Color.parseColor("#FF1744"));
    if (model.getAns().equals(optA.getText())){optA.setBackgroundColor(Color.parseColor("#00E676"));}
    if (model.getAns().equals(optB.getText())){optB.setBackgroundColor(Color.parseColor("#00E676"));}
    if (model.getAns().equals(optD.getText())){optD.setBackgroundColor(Color.parseColor("#00E676"));}
                        }else {
                            life--;
                            life_left.setText(""+life);
                            optionCommen();
                        }
                    }
                }
            }
        });
        optD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt++;
                optA.setClickable(false);
                optC.setClickable(false);
                optB.setClickable(false);
                if (questionId < flagModels.size()){
                    if (model.getAns().equals(optD.getText())){
                        optD.setBackgroundColor(Color.parseColor("#00E676"));
                        score++;
                    }else {

                        if (life>1){
                            life--;
                            life_left.setText(""+life);
        optD.setBackgroundColor(Color.parseColor("#FF1744"));
        if (model.getAns().equals(optA.getText())){optA.setBackgroundColor(Color.parseColor("#00E676"));}
        if (model.getAns().equals(optB.getText())){optB.setBackgroundColor(Color.parseColor("#00E676"));}
        if (model.getAns().equals(optC.getText())){optC.setBackgroundColor(Color.parseColor("#00E676"));}
                        }else {
                            life--;
                            life_left.setText(""+life);
                            optionCommen();
                        }
                    }
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remaining--;
                questionId++;
                optA.setClickable(true);
                optA.setBackgroundColor(Color.parseColor("#FFFFFF"));
                optB.setClickable(true);
                optB.setBackgroundColor(Color.parseColor("#FFFFFF"));
                optC.setClickable(true);
                optC.setBackgroundColor(Color.parseColor("#FFFFFF"));
                optD.setClickable(true);
                optD.setBackgroundColor(Color.parseColor("#FFFFFF"));
                if (questionId < flagModels.size()) {
                    Log.d("ans2",score+"");
                    model = flagModels.get(questionId);
                    path_name=model.getImg().substring(4).replace("-","_");
                    resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
                    flag_holder.setImageDrawable(getResources().getDrawable(resID));
                    optA.setText(model.getOptA());
                    optB.setText(model.getOptB());
                    optC.setText(model.getOptC());
                    optD.setText(model.getOptD());
                    position++;
                    total_flag.setText(""+position);
                    //setQuestionView();
                } else {
                    Intent intent = new Intent(LifeFlagQuizActivity.this, ResultActivity.class);
                    helper.close();
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    b.putInt("total",flagModels.size());
                    b.putInt("postion",posin);
                    b.putInt("attempt",attempt);
                    Log.d("total",flagModels.size()+"");
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
            //}
        });
    }
    private void optionCommen() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.customdialog_layout, null);
        if (adcount==0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Continue Game...");
            alert.setView(alertLayout);
            alert.setCancelable(false);
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                    intent.putExtra("score", score); //Your score
                    intent.putExtra("total",flagModels.size());
                    intent.putExtra("attempt",attempt);
                    intent.putExtra("postion",posin);
                    startActivity(intent);
                }
            });
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mRewardedVideoAd.show();
                    adcount++;
                    MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.ad_unit_id));
                    mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getApplicationContext());
                    mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
                        @Override
                        public void onRewardedVideoAdLoaded() {
                            //Toast.makeText(getBaseContext(),"Ad loaded.", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onRewardedVideoAdOpened() {
//                        Toast.makeText(getBaseContext(),
//                                "Ad opened.", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onRewardedVideoStarted() {
//                        Toast.makeText(getBaseContext(),
//                                "Ad started.", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onRewardedVideoAdClosed() {
//                        Toast.makeText(getBaseContext(),
//                                "Ad closed.", Toast.LENGTH_SHORT).show();
                            answermethod();
                        }
                        @Override
                        public void onRewarded(RewardItem rewardItem) {
//                        Toast.makeText(getBaseContext(),
//                                "Ad triggered reward.", Toast.LENGTH_SHORT).show();
                            //addCoins(rewardItem.getAmount());
                            life=life+3;
                            life_left.setText(""+life);
                        }
                        @Override
                        public void onRewardedVideoAdLeftApplication() {
//                        Toast.makeText(getBaseContext(),
//                                "Ad left application.", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onRewardedVideoAdFailedToLoad(int i) {
//                        Toast.makeText(getBaseContext(),
//                                "Ad failed to load.", Toast.LENGTH_SHORT).show();
                            answermethod();
                        }
                    });
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        }else {
            Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
            intent.putExtra("score", score); //Your score
            intent.putExtra("total",flagModels.size());
            intent.putExtra("attempt",attempt);
            intent.putExtra("postion",posin);
            startActivity(intent);
        }
    }
    private void setModelView() {
        resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
        flag_holder.setImageDrawable(getResources().getDrawable(resID));
        //String[] country_array

        //"R.string."+


       //

//        if (Locale.getDefault().equals("fr_FR")){
//            String a2=model.getOptA().replace(" ","_")+"_fr";
//            int resId=getResources()
//                    .getIdentifier(a2,"string",
//                            getApplicationContext().getPackageName());
//            Log.d("test",resId+"");
//
//            if (resId!=0){
//                optA.setText(getString(resId));
//                Log.d("test2",getString(resId));
//            }
//            else {
//                optA.setText(model.getOptA());
//            }
//        }else {
//
//        }
        optA.setText(model.getOptA());
        optB.setText(model.getOptB());
        optC.setText(model.getOptC());
        optD.setText(model.getOptD());
        position++;
    }
    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(getApplicationContext(),StartActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);
        finish();
        super.onBackPressed();
    }
    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }
    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }
    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }
}
