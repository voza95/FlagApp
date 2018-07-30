package com.wondrousapps.flagquiz.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wondrousapps.flagquiz.App;
import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.utils.AppLanguageUtils;

import static com.wondrousapps.flagquiz.activity.SettingsActivity.CHANGE_LANGUAGE_REQUEST_CODE;

public class LangSelectActivity extends AppCompatActivity {
TextView en,hi,fr,it,de;
Button button;
Boolean clicked=false;
String lang;
CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang_select);

        en= (TextView) findViewById(R.id.en_EN);
        hi= (TextView) findViewById(R.id.hi_IN);
        fr= (TextView) findViewById(R.id.fr_FR);
        de= (TextView) findViewById(R.id.de_DE);
        it= (TextView) findViewById(R.id.it_IT);
        button= (Button) findViewById(R.id.submit_btn);
        coordinatorLayout= (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clicked=true;
//                hi.setClickable(false);
//                fr.setClickable(false);
//                de.setClickable(false);
                en.setBackgroundColor(Color.parseColor("#00E676"));
                hi.setBackgroundColor(Color.parseColor("#FFFFFF"));
                fr.setBackgroundColor(Color.parseColor("#FFFFFF"));
                it.setBackgroundColor(Color.parseColor("#FFFFFF"));
                de.setBackgroundColor(Color.parseColor("#FFFFFF"));
                AppLanguageUtils.changeAppLanguage(getApplicationContext(), "en");
                AppLanguageUtils.changeAppLanguage(App.getContext(), "en");
                lang="en";
        //        recreate();
            }
        });
        hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
//                en.setClickable(false);
//                fr.setClickable(false);
//                de.setClickable(false);
                hi.setBackgroundColor(Color.parseColor("#00E676"));
                en.setBackgroundColor(Color.parseColor("#FFFFFF"));
                fr.setBackgroundColor(Color.parseColor("#FFFFFF"));
                it.setBackgroundColor(Color.parseColor("#FFFFFF"));
                de.setBackgroundColor(Color.parseColor("#FFFFFF"));
                AppLanguageUtils.changeAppLanguage(getApplicationContext(), "hi");
                AppLanguageUtils.changeAppLanguage(App.getContext(), "hi");
                lang="hi";
          //      recreate();
            }
        });
        fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
//                en.setClickable(false);
//                hi.setClickable(false);
//                de.setClickable(false);
                fr.setBackgroundColor(Color.parseColor("#00E676"));
                hi.setBackgroundColor(Color.parseColor("#FFFFFF"));
                en.setBackgroundColor(Color.parseColor("#FFFFFF"));
                it.setBackgroundColor(Color.parseColor("#FFFFFF"));
                de.setBackgroundColor(Color.parseColor("#FFFFFF"));

                AppLanguageUtils.changeAppLanguage(getApplicationContext(), "fr");
                AppLanguageUtils.changeAppLanguage(App.getContext(), "fr");
                lang="fr";
            //    recreate();
            }
        });
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
//                en.setClickable(false);
//                fr.setClickable(false);
//                hi.setClickable(false);
                de.setBackgroundColor(Color.parseColor("#00E676"));
                en.setBackgroundColor(Color.parseColor("#FFFFFF"));
                fr.setBackgroundColor(Color.parseColor("#FFFFFF"));
                hi.setBackgroundColor(Color.parseColor("#FFFFFF"));
                it.setBackgroundColor(Color.parseColor("#FFFFFF"));
                AppLanguageUtils.changeAppLanguage(getApplicationContext(), "de");
                AppLanguageUtils.changeAppLanguage(App.getContext(), "de");
                lang="de";
              //  recreate();
            }
        });
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked=true;
                de.setBackgroundColor(Color.parseColor("#FFFFFF"));
                en.setBackgroundColor(Color.parseColor("#FFFFFF"));
                fr.setBackgroundColor(Color.parseColor("#FFFFFF"));
                hi.setBackgroundColor(Color.parseColor("#FFFFFF"));
                it.setBackgroundColor(Color.parseColor("#00E676"));
                AppLanguageUtils.changeAppLanguage(getApplicationContext(), "es");
                AppLanguageUtils.changeAppLanguage(App.getContext(), "es");
                lang="es";
                //recreate();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked==true){
                    startActivity(new Intent(getApplicationContext(),StartActivity.class));
//                    Intent intent=new Intent(getApplicationContext(),StartActivity.class);
////                    intent.putExtra("MESSAGE",lang);
////                    setResult(CHANGE_LANGUAGE_REQUEST_CODE,intent);
//                    startActivity(intent);
//                   // finish();
                }else {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
            }
        });
//        if(savedInstanceState == null) {
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//          //  fragmentTransaction.replace(R.id.fragment_container2, new LanguagesPreferenceFragment2()).commitAllowingStateLoss();
//        }
    }


//    public static class LanguagesPreferenceFragment2 extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.languages_preference);
//            ListPreference preference = (ListPreference) findPreference(getString(R.string.app_language_pref_key));
////            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
////                @Override
////                public boolean onPreferenceClick(Preference preference) {
////                    return false;
////                }
////            });
//            preference.setSummary(preference.getEntry());
//            SharedPreferences prefs = getPreferenceManager().getSharedPreferences();
//            prefs.registerOnSharedPreferenceChangeListener(mPreferenceChangeListener);
//        }
//        @Override
//        public void onDestroy() {
//            super.onDestroy();
//            getPreferenceManager().getSharedPreferences()
//                    .unregisterOnSharedPreferenceChangeListener(mPreferenceChangeListener);
//        }
//        private final SharedPreferences.OnSharedPreferenceChangeListener mPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
//            @Override
//            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//                if (getString(R.string.app_language_pref_key).equals(key)){
//                    ListPreference preference = (ListPreference) findPreference(key);
//                    preference.setSummary(preference.getEntry());
//                    CharSequence language = preference.getValue();
//                    if (!TextUtils.isEmpty(language)) {
//                        onChangeAppLanguage(language.toString());
//                    }
//                }
//            }
//        };
//        private void onChangeAppLanguage(String newLanguage) {
//            AppLanguageUtils.changeAppLanguage(getActivity(), newLanguage);
//            AppLanguageUtils.changeAppLanguage(App.getContext(), newLanguage);
//            getActivity().recreate();
//        }
//    }

}
