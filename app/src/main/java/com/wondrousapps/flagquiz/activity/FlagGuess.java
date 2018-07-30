package com.wondrousapps.flagquiz.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Random;

import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.db.DatabaseOpenHelper;
import com.wondrousapps.flagquiz.model.FlagModel;

public class FlagGuess extends AppCompatActivity {
    ImageView imgA,imgB,imgC,imgD;
    TextView flagName;
    Button next;
    TextView life_left;
    FlagModel model;
    List<FlagModel> flagModels;
    String path_name,path_name2,path_name3,path_name4,path_name5;
    int position=0;
    int max=3,min=0;
    Random rn;
    int nu;
    int resID,resID2,resID3,resID4,resID5;
    //4985574
    int score=0;
    int life=5;
    String t1;
    int attempt=0;
    int questionId;
    DatabaseOpenHelper helper;
    private RewardedVideoAd mRewardedVideoAd;
    int posin;
    int adcount=0;
   // public static final String TEST_DEVICE_ID = "DB07DDA7FB3B42C3FD6DC85ACBD34693";
    private AdView mBannerAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_word);
        getSupportActionBar().setElevation(0);
        flagModels=new ArrayList<FlagModel>();
        mBannerAd = (AdView) findViewById(R.id.banner_ad2);
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

        rn = new Random();
        life_left= (TextView) findViewById(R.id.life_left);
        nu = rn.nextInt(max - min + 1) + min;
        imgA= (ImageView) findViewById(R.id.flag_a);
        imgB= (ImageView) findViewById(R.id.flag_b);
        imgC= (ImageView) findViewById(R.id.flag_c);
        imgD= (ImageView) findViewById(R.id.flag_d);
        flagName= (TextView) findViewById(R.id.flag_name);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        next= (Button) findViewById(R.id.next_btn2);
        helper = new DatabaseOpenHelper(this);
        life_left.setText(""+life);
        flagModels=helper.getPoses("en");
//        flagModels2=helper.getPoses();
//        flagModels3=helper.getPoses();
//        flagModels4=helper.getPoses();

        Log.d("img",resID+"");

        if (flagModels!=null && flagModels.size()!=0){
            model=flagModels.get(position);
//            model2=flagModels2.get(position);
//            model3=flagModels3.get(position);
//            model4=flagModels4.get(position);

            path_name=model.getImg().substring(4).replace("-","_");
            Log.d("path_nam",path_name);
            path_name2=model.getOptA().replace(" ","_").replace("-","_").toLowerCase();
            Log.d("path_nam2",path_name2);
            path_name3=model.getOptB().replace(" ","_").replace("-","_").toLowerCase();
            Log.d("path_nam3",path_name3);
            path_name4=model.getOptC().replace(" ","_").replace("-","_").toLowerCase();
            Log.d("path_nam4",path_name4);
            path_name5=model.getOptD().replace(" ","_").replace("-","_").toLowerCase();
            Log.d("path_nam5",path_name5);
//            path_name2=model2.getImg().substring(4).replace("-","_");
//            path_name3=model3.getImg().substring(4).replace("-","_");
//            path_name4=model4.getImg().substring(4).replace("-","_");
            Log.d("path",path_name);
        }
        mRewardedVideoAd.loadAd(getString(R.string.ad_unit_id), new AdRequest.Builder()
                .build());
        answermethod();
    }
    private void answermethod() {

        setModelView();
        imgA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt++;
                imgB.setClickable(false);
                imgC.setClickable(false);
                imgD.setClickable(false);
                //4985574
                if (questionId < flagModels.size()){
                    if (path_name.equals(path_name2)){
                        imgA.setBackgroundColor(Color.parseColor("#1DE9B6"));
                        score++;
                    }else {
                        if (life>1){
                            life--;
                            life_left.setText(""+life);
                imgA.setBackgroundColor(Color.parseColor("#FF1744"));
                if (path_name.equals(path_name3)){imgB.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                if (path_name.equals(path_name4)){imgC.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                if (path_name.equals(path_name5)){imgD.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                        }else {
                            life--;
                            life_left.setText(""+life);
                              optionCommen();
                        }
                    }
                }
            }
        });
        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt++;
                imgA.setClickable(false);
                imgC.setClickable(false);
                imgD.setClickable(false);
                if (questionId < flagModels.size()){

                    if (path_name.equals(path_name3)){
                        imgB.setBackgroundColor(Color.parseColor("#1DE9B6"));
                        score++;
                    }else {
                        if (life>1){
                            life--;
                            life_left.setText(""+life);
                            imgB.setBackgroundColor(Color.parseColor("#FF1744"));
                            if (path_name.equals(path_name2)){imgA.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                            if (path_name.equals(path_name4)){imgC.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                            if (path_name.equals(path_name5)){imgD.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                        }else {
                            life--;
                            life_left.setText(""+life);
                            optionCommen();
                        }
                    }
                }
            }
        });
        imgC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt++;
                imgA.setClickable(false);
                imgB.setClickable(false);
                imgD.setClickable(false);
                if (questionId < flagModels.size()){

                    if (path_name.equals(path_name4)){
                        imgC.setBackgroundColor(Color.parseColor("#1DE9B6"));
                        score++;
                    }else {
                        if (life>1){
                            life--;
                            life_left.setText(""+life);
                            imgC.setBackgroundColor(Color.parseColor("#FF1744"));
                            if (path_name.equals(path_name2)){imgA.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                            if (path_name.equals(path_name3)){imgB.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                            if (path_name.equals(path_name5)){imgD.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                        }else {
                            life--;
                            life_left.setText(""+life);
                              optionCommen();
                        }
                    }
                }
            }
        });
        imgD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt++;
                imgA.setClickable(false);
                imgC.setClickable(false);
                imgB.setClickable(false);

                if (questionId < flagModels.size()){

                   // t1 = helper.checkFlag(model.getImg());
                    if (path_name.equals(path_name5)){
                        imgD.setBackgroundColor(Color.parseColor("#1DE9B6"));
                        score++;
                    }else {

                        if (life>1){
                            life--;
                            life_left.setText(""+life);
                            imgD.setBackgroundColor(Color.parseColor("#FF1744"));
                            if (path_name.equals(path_name2)){imgA.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                            if (path_name.equals(path_name3)){imgB.setBackgroundColor(Color.parseColor("#1DE9B6"));}
                            if (path_name.equals(path_name4)){imgC.setBackgroundColor(Color.parseColor("#1DE9B6"));}
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

                nu = rn.nextInt(max - min + 1) + min;
                //remaining--;
                questionId++;
                imgA.setClickable(true);
                imgA.setBackgroundResource(0);
                //imgA.setBackgroundColor(Color.parseColor("#19057A"));
                imgB.setClickable(true);
                imgB.setBackgroundResource(0);
                //imgB.setBackgroundColor(Color.parseColor("#19057A"));
                imgC.setClickable(true);
                imgC.setBackgroundResource(0);
                //imgC.setBackgroundColor(Color.parseColor("#19057A"));
                imgD.setClickable(true);
                imgD.setBackgroundResource(0);
                //imgD.setBackgroundColor(Color.parseColor("#19057A"));
                if (questionId < flagModels.size()) {
                    Log.d("ans2",score+"");

                    model = flagModels.get(questionId);
                    path_name=model.getImg().substring(4).replace("-","_");
                    Log.d("path_name",path_name);
                    path_name2=model.getOptA().replace(" ","_").replace("-","_").toLowerCase();
                    Log.d("path_name2",path_name2);
                    path_name3=model.getOptB().replace(" ","_").replace("-","_").toLowerCase();
                    Log.d("path_nam3",path_name3);
                    path_name4=model.getOptC().replace(" ","_").replace("-","_").toLowerCase();
                    Log.d("path_name4",path_name4);
                    path_name5=model.getOptD().replace(" ","_").replace("-","_").toLowerCase();
                    Log.d("path_name5",path_name5);
                    resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
                    t1 = helper.checkFlag(model.getImg(),String.valueOf(Locale.getDefault()));
                    Log.d("t1",t1);
                    flagName.setText(t1);
                    //flagName.setText(model.getAns());
                    resID = getResources().getIdentifier(path_name2 , "drawable", getPackageName());
                    imgA.setImageDrawable(getResources().getDrawable(resID));
                    resID2=getResources().getIdentifier(path_name3,"drawable",getPackageName());
                    imgB.setImageDrawable(getResources().getDrawable(resID2));
                    resID3=getResources().getIdentifier(path_name4,"drawable",getPackageName());
                    imgC.setImageDrawable(getResources().getDrawable(resID3));
                    resID4=getResources().getIdentifier(path_name5,"drawable",getPackageName());
                    imgD.setImageDrawable(getResources().getDrawable(resID4));
                  //  position++;
//                    if (nu==0){
//                        resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
//                        imgA.setImageDrawable(getResources().getDrawable(resID));
//
//                        resID2 = getResources().getIdentifier(path_name2 , "drawable", getPackageName());
//                        imgB.setImageDrawable(getResources().getDrawable(resID2));
//                        resID3 = getResources().getIdentifier(path_name3 , "drawable", getPackageName());
//                        imgC.setImageDrawable(getResources().getDrawable(resID3));
//                        resID4 = getResources().getIdentifier(path_name4 , "drawable", getPackageName());
//
//                        imgD.setImageDrawable(getResources().getDrawable(resID4));
//                    }
//                    if(nu==1){
//                        resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
//                        imgB.setImageDrawable(getResources().getDrawable(resID));
//
//
//                        resID2 = getResources().getIdentifier(path_name2 , "drawable", getPackageName());
//                        imgA.setImageDrawable(getResources().getDrawable(resID2));
//                        resID3 = getResources().getIdentifier(path_name3 , "drawable", getPackageName());
//                        imgC.setImageDrawable(getResources().getDrawable(resID3));
//                        resID4 = getResources().getIdentifier(path_name4 , "drawable", getPackageName());
//                        imgD.setImageDrawable(getResources().getDrawable(resID4));
//                    }
//                    if (nu==2){
//                        resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
//                        imgC.setImageDrawable(getResources().getDrawable(resID));
//
//                        resID2 = getResources().getIdentifier(path_name2 , "drawable", getPackageName());
//                        imgA.setImageDrawable(getResources().getDrawable(resID2));
//                        resID3 = getResources().getIdentifier(path_name3 , "drawable", getPackageName());
//                        imgB.setImageDrawable(getResources().getDrawable(resID3));
//                        resID4 = getResources().getIdentifier(path_name4 , "drawable", getPackageName());
//                        imgD.setImageDrawable(getResources().getDrawable(resID4));
//                    }
//                    if (nu==3){
//                        resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
//                        imgD.setImageDrawable(getResources().getDrawable(resID));
//
//                        resID2 = getResources().getIdentifier(path_name2 , "drawable", getPackageName());
//                        imgA.setImageDrawable(getResources().getDrawable(resID2));
//                        resID3 = getResources().getIdentifier(path_name3 , "drawable", getPackageName());
//                        imgB.setImageDrawable(getResources().getDrawable(resID3));
//                        resID4 = getResources().getIdentifier(path_name4 , "drawable", getPackageName());
//                        imgC.setImageDrawable(getResources().getDrawable(resID4));
//                    }


                    //setModelView();
//                    flag_holder.setImageDrawable(getResources().getDrawable(resID));
//                    optA.setText(model.getOptA());
//                    optB.setText(model.getOptB());
//                    optC.setText(model.getOptC());
//                    optD.setText(model.getOptD());
//                    position++;
//                    total_flag.setText(""+position);
                    //setQuestionView();
                } else {
                    Intent intent = new Intent(FlagGuess.this, ResultActivity.class);
                    helper.close();
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    b.putInt("total",flagModels.size());
                    b.putInt("attempt",attempt);
                    //  b.putInt("postion",posin);
                    Log.d("total",flagModels.size()+"");
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
            //}
        });
    }
    private void setModelView() {
        //   resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
        t1 = helper.checkFlag(model.getImg(),String.valueOf(Locale.getDefault()));
        Log.d("t1",t1);
        flagName.setText(t1);
       // flagName.setText(model.getAns());

        resID = getResources().getIdentifier(path_name2 , "drawable", getPackageName());
        imgA.setImageDrawable(getResources().getDrawable(resID));
        resID2=getResources().getIdentifier(path_name3,"drawable",getPackageName());
        imgB.setImageDrawable(getResources().getDrawable(resID2));
        resID3=getResources().getIdentifier(path_name4,"drawable",getPackageName());
        imgC.setImageDrawable(getResources().getDrawable(resID3));
        resID4=getResources().getIdentifier(path_name5,"drawable",getPackageName());
        imgD.setImageDrawable(getResources().getDrawable(resID4));
//        if (nu==0){
//            resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
//            imgA.setImageDrawable(getResources().getDrawable(resID));
//            resID2 = getResources().getIdentifier(path_name2 , "drawable", getPackageName());
//            imgB.setImageDrawable(getResources().getDrawable(resID2));
//            resID3 = getResources().getIdentifier(path_name3 , "drawable", getPackageName());
//            imgC.setImageDrawable(getResources().getDrawable(resID3));
//            resID4 = getResources().getIdentifier(path_name4 , "drawable", getPackageName());
//            imgD.setImageDrawable(getResources().getDrawable(resID4));
//        }
//        if(nu==1){
//            resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
//            imgB.setImageDrawable(getResources().getDrawable(resID));
//            resID2 = getResources().getIdentifier(path_name2 , "drawable", getPackageName());
//            imgA.setImageDrawable(getResources().getDrawable(resID2));
//            resID3 = getResources().getIdentifier(path_name3 , "drawable", getPackageName());
//            imgC.setImageDrawable(getResources().getDrawable(resID3));
//            resID4 = getResources().getIdentifier(path_name4 , "drawable", getPackageName());
//            imgD.setImageDrawable(getResources().getDrawable(resID4));
//        }
//        if (nu==2){
//            resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
//            imgC.setImageDrawable(getResources().getDrawable(resID));
//
//            resID2 = getResources().getIdentifier(path_name2 , "drawable", getPackageName());
//            imgA.setImageDrawable(getResources().getDrawable(resID2));
//            resID3 = getResources().getIdentifier(path_name3 , "drawable", getPackageName());
//            imgB.setImageDrawable(getResources().getDrawable(resID3));
//            resID4 = getResources().getIdentifier(path_name4 , "drawable", getPackageName());
//            imgD.setImageDrawable(getResources().getDrawable(resID4));
//        }
//        if (nu==3){
//            resID = getResources().getIdentifier(path_name , "drawable", getPackageName());
//            imgD.setImageDrawable(getResources().getDrawable(resID));
//            resID2 = getResources().getIdentifier(path_name2 , "drawable", getPackageName());
//            imgA.setImageDrawable(getResources().getDrawable(resID2));
//            resID3 = getResources().getIdentifier(path_name3 , "drawable", getPackageName());
//            imgB.setImageDrawable(getResources().getDrawable(resID3));
//            resID4 = getResources().getIdentifier(path_name4 , "drawable", getPackageName());
//            imgC.setImageDrawable(getResources().getDrawable(resID4));
//        }

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

                    //730900864888
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

