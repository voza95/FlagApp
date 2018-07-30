package com.wondrousapps.flagquiz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.reward.RewardedVideoAd;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.adapter.RecyclerViewAdapter;
import com.wondrousapps.flagquiz.db.DatabaseOpenHelper;
import com.wondrousapps.flagquiz.model.FlagModel;

public class FlagInfo extends AppCompatActivity {
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111";
    public static final int ITEMS_PER_AD = 5;
    private static final int NATIVE_EXPRESS_AD_HEIGHT = 150;
    private AdView mBannerAd;
   // public static final String TEST_DEVICE_ID = "DB07DDA7FB3B42C3FD6DC85ACBD34693";
    RecyclerView recyclerView;
    private InterstitialAd mInterstitialAd;
    DatabaseOpenHelper helper;
    ArrayList<FlagModel> flagModels;
    FlagModel model;
    private List<Object> mCommentItem;
    private RewardedVideoAd mAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_info);

        helper=new DatabaseOpenHelper(this);
        recyclerView= (RecyclerView) findViewById(R.id.flag_rv);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        flagModels=new ArrayList<FlagModel>();
        flagModels=helper.getPoses(String.valueOf(Locale.getDefault()));
        MobileAds.initialize(getApplicationContext(),
                getString(R.string.admob_app_id));
//        FlagAdapter adapter=new FlagAdapter(getApplicationContext(),flagModels);
//        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(),flagModels));

      //  mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

//        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
              //  .addTestDevice(TEST_DEVICE_ID)
                .build();
        //You can add the following code if you are testing in an emulator
        /*AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();*/
        mInterstitialAd.loadAd(adRequest);
    }
    /**
     * Sets up and loads the Native Express ads.
     */
    private void setUpAndLoadNativeExpressAds() {
        // Use a Runnable to ensure that the RecyclerView has been laid out before setting the
        // ad size for the Native Express ad. This allows us to set the Native Express ad's
        // width to match the full width of the RecyclerView.
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                final float scale = FlagInfo.this.getResources().getDisplayMetrics().density;
                // Set the ad size and ad unit ID for each Native Express ad in the items list.
                for (int i = ITEMS_PER_AD; i <= mCommentItem.size(); i += ITEMS_PER_AD) {
                    final NativeExpressAdView adView =
                            (NativeExpressAdView) mCommentItem.get(i);
                    final CardView cardView = (CardView) findViewById(R.id.ad_card_view);
                    final int adWidth = cardView.getWidth() - cardView.getPaddingLeft()
                            - cardView.getPaddingRight();
                    AdSize adSize = new AdSize((int) (adWidth / scale), NATIVE_EXPRESS_AD_HEIGHT);
                    adView.setAdSize(adSize);
                    adView.setAdUnitId(AD_UNIT_ID);
                }

                // Load the first Native Express ad in the items list.
                loadNativeExpressAd(ITEMS_PER_AD);
            }
        });
    }

    /**
     * Loads the Native Express ads in the items list.
     */
    private void loadNativeExpressAd(final int index) {

        if (index >= mCommentItem.size()) {
            return;
        }

        Object item = mCommentItem.get(index);
        if (!(item instanceof NativeExpressAdView)) {
            throw new ClassCastException("Expected item at index " + index + " to be a Native"
                    + " Express ad.");
        }

        final NativeExpressAdView adView = (NativeExpressAdView) item;

        // Set an AdListener on the NativeExpressAdView to wait for the previous Native Express ad
        // to finish loading before loading the next ad in the items list.
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // The previous Native Express ad loaded successfully, call this method again to
                // load the next ad in the items list.
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // The previous Native Express ad failed to load. Call this method again to load
                // the next ad in the items list.
                Log.e("MainActivity", "The previous Native Express ad failed to load. Attempting to"
                        + " load the next Native Express ad in the items list.");
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }
        });
        // Load the Native Express ad.
        adView.loadAd(new AdRequest.Builder().build());
    }

}
