package com.wondrousapps.flagquiz.activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.wondrousapps.flagquiz.App;
import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.utils.AppLanguageUtils;

public class LanguagesActivity extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        ActionBar actionBar = getSupportActionBar();
        button= (Button) findViewById(R.id.home_btn);
        if(actionBar != null) {
            actionBar.setTitle(R.string.pref_setting_customize);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if(savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new LanguagesPreferenceFragment()).commitAllowingStateLoss();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),StartActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(AppLanguageUtils.attachBaseContext(newBase, newBase.getString(R.string.app_language_pref_key)));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class LanguagesPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.languages_preference);
            ListPreference preference = (ListPreference) findPreference(getString(R.string.app_language_pref_key));
//            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                @Override
//                public boolean onPreferenceClick(Preference preference) {
//                    return false;
//                }
//            });
            preference.setSummary(preference.getEntry());
            SharedPreferences prefs = getPreferenceManager().getSharedPreferences();
            prefs.registerOnSharedPreferenceChangeListener(mPreferenceChangeListener);
        }
        @Override
        public void onDestroy() {
            super.onDestroy();
            getPreferenceManager().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(mPreferenceChangeListener);
        }
        private final SharedPreferences.OnSharedPreferenceChangeListener mPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (getString(R.string.app_language_pref_key).equals(key)){
                    ListPreference preference = (ListPreference) findPreference(key);
                    preference.setSummary(preference.getEntry());
                    CharSequence language = preference.getValue();
                    if (!TextUtils.isEmpty(language)) {
                        onChangeAppLanguage(language.toString());
                    }
                }
            }
        };
        private void onChangeAppLanguage(String newLanguage) {
            AppLanguageUtils.changeAppLanguage(getActivity(), newLanguage);
            AppLanguageUtils.changeAppLanguage(App.getContext(), newLanguage);
            getActivity().recreate();
        }
    }
}