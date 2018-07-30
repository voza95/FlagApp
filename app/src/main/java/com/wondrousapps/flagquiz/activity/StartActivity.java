package com.wondrousapps.flagquiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kobakei.ratethisapp.RateThisApp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.wondrousapps.flagquiz.App;
import com.wondrousapps.flagquiz.LocaleHelper;
import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.adapter.ProductAdapter;
import com.wondrousapps.flagquiz.listener.RecyclerItemClickListener;
import com.wondrousapps.flagquiz.model.Product;
import com.wondrousapps.flagquiz.utils.AppLanguageUtils;

import static com.wondrousapps.flagquiz.activity.SettingsActivity.CHANGE_LANGUAGE_REQUEST_CODE;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111";
    //public static final String TEST_DEVICE_ID = "DB07DDA7FB3B42C3FD6DC85ACBD34693";
    public static final String MyPREFERENCES = "MyPrefs";
    private AdView mBannerAd;
    Button play_quiz;
    Button life_base;
    Button word,check;
    Intent intent;
    String message;
    List<Product> productList;

    SharedPreferences sharedpreferences;

    //the recyclerview
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RateThisApp.onCreate(this);
        RateThisApp.showRateDialogIfNeeded(this);
        RateThisApp.Config config = new RateThisApp.Config();
        //config.setUrl("http://www.example.com");

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        Bundle extras = getIntent().getExtras();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        message=getSharedPreferences("PREFERENCE",MODE_PRIVATE).getString("MESSAGE",message);

        if (isFirstRun) {
            //show sign up activity
            startActivityForResult(new Intent(StartActivity.this, LanguagesActivity.class), CHANGE_LANGUAGE_REQUEST_CODE);
//            Toast.makeText(StartActivity.this, "Run only once", Toast.LENGTH_LONG)
//                    .show();
        }else {
            AppLanguageUtils.changeAppLanguage(getApplicationContext(), message);
            AppLanguageUtils.changeAppLanguage(App.getContext(), message);
            //recreate();
        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();

        config.setTitle(R.string.my_own_title);
        config.setMessage(R.string.my_own_message);
        config.setYesButtonText(R.string.my_own_rate);
        config.setNoButtonText(R.string.my_own_thanks);
        config.setCancelButtonText(R.string.my_own_cancel);
        RateThisApp.init(config);
        mBannerAd = (AdView) findViewById(R.id.banner_ad);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        //      .addTestDevice(TEST_DEVICE_ID)

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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        // recyclerView.addItemDecoration(new LinePagerIndicatorDecoration());

        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.timer_icon2,getResources().getString(R.string.time_quiz),getResources().getString(R.string.details)));
        productList.add(new Product(R.drawable.like2,getResources().getString(R.string.guess_country),getResources().getString(R.string.details2)));
        productList.add(new Product(R.drawable.flag2,getResources().getString(R.string.guess_flag),getResources().getString(R.string.details3)));
        productList.add(new Product(R.drawable.flag_icon,getResources().getString(R.string.learn),getResources().getString(R.string.details4)));
        productList.add(new Product(R.drawable.plus,getResources().getString(R.string.more_apps),getResources().getString(R.string.details5)));

        ProductAdapter adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        if (position==0){
                            intent=new Intent(getApplicationContext(),FlagQuizActivity.class);

                            startActivity(intent);
                        }
                        if (position==1){
                            intent=new Intent(getApplicationContext(),LifeFlagQuizActivity.class);
                            startActivity(intent);
                        }
                        if (position==2){
                            intent=new Intent(getApplicationContext(),FlagGuess.class);
                            startActivity(intent);
                        }
                        if (position==3){
                            intent=new Intent(getApplicationContext(),FlagInfo.class);
                            startActivity(intent);
                        }
                        if (position==4){
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://play.google.com/store/search?q=wondrousapps"));
                            startActivity(intent);
                        }
                    }
                })
        );

//        play_quiz= (Button) findViewById(R.id.flags_play);
//        life_base= (Button) findViewById(R.id.life_flags_play);
//        word= (Button) findViewById(R.id.flag_guess);
//        check= (Button) findViewById(R.id.flag_check);
//        play_quiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent=new Intent(getApplicationContext(),FlagQuizActivity.class);
//                startActivity(intent);
//            }
//        });
//        life_base.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent=new Intent(getApplicationContext(),LifeFlagQuizActivity.class);
//                startActivity(intent);
//            }
//        });
//        word.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent = new Intent(getApplicationContext(),FlagGuess.class);
//                startActivity(intent);
//            }
//        });
//        check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent=new Intent(getApplicationContext(),FlagCheck.class);
//                startActivity(intent);
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(AppLanguageUtils.attachBaseContext(newBase, newBase.getString(R.string.app_language_pref_key)));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CHANGE_LANGUAGE_REQUEST_CODE) {
            recreate();
        }
    }
    //@Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == CHANGE_LANGUAGE_REQUEST_CODE) {
//            recreate();
//            //message = data.getStringExtra("MESSAGE");
//            Bundle extras = getIntent().getExtras();
//            if(extras == null) {
//                message= null;
//            } else {
//                AppLanguageUtils.changeAppLanguage(getApplicationContext(), message);
//                AppLanguageUtils.changeAppLanguage(App.getContext(), message);
//
//                message= extras.getString("MESSAGE");
//            }
//
//        }
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.start, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id==R.id.nav_home){

        }
//        if (id==R.id.favorite_quote){
//
//        }
//        else
        if (id==R.id.nav_rate_us){
            Uri uri=Uri.parse("market://details?id=" + getPackageName());
        }
        else if (id==R.id.nav_policy){
            Intent intent1=new Intent(getApplicationContext(),PolicyActivity.class);
            startActivity(intent1);
        }
        else if (id == R.id.nav_share) {
            Intent shareIntent=new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            String shareDetail = getString(R.string.app_name) + " : \n" + "https://play.google.com/store/apps/details?id=" + getPackageName();
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareDetail);
            startActivity(Intent.createChooser(shareIntent, "Share via :"));
            return  true;
        }
        else if (id == R.id.nav_more_apps) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Aryaa+Infotech"));
            startActivity(intent);
            return true;
        }else if (id==R.id.nav_setting){
            startActivityForResult(new Intent(StartActivity.this, LanguagesActivity.class), CHANGE_LANGUAGE_REQUEST_CODE);
//            Intent intent=new Intent(getApplicationContext(),SettingsActivity.class);
//            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
