package com.wondrousapps.flagquiz.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.utils.AppLanguageUtils;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
Spinner spinner;
TextView demo;
Locale locale;
   static final int CHANGE_LANGUAGE_REQUEST_CODE = 1;
    Configuration config;
    private static final String LOCALE_KEY = "localekey";
    private static final String LOCALE_PREF_KEY = "localePref";
    private static final String ENGLISH_LOCALE = "en_US";
    String[] country={"english","hindi","spanish","franch","german"};
    String[] lang = { "en_US", "hi", "it_IT", "fr_FR", "de_DE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            actionBar.setTitle(R.string.pref_setting_customize);
        }
        TextView textView1 = (TextView) findViewById(R.id.tv_content1);
        textView1.setText(R.string.pref_setting_customize);
        textView1.setOnClickListener(this);
        TextView textView2 = (TextView) findViewById(R.id.tv_content2);
        textView2.setText(getApplication().getString(R.string.pref_setting_customize));
        textView2.setOnClickListener(this);
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
    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(SettingsActivity.this, LanguagesActivity.class), CHANGE_LANGUAGE_REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),StartActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
