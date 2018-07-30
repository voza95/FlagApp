package com.wondrousapps.flagquiz.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.webkit.WebView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.wondrousapps.flagquiz.R

class PolicyActivity : AppCompatActivity() {


    private var webView: WebView?=null
    private var adView: AdView?=null
    private var adRequest: AdRequest?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_policy)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Privacy Policy"

        //code for ads
        adView = findViewById(R.id.adView) as AdView
        adRequest = AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                //.addTestDevice("0D18E7ADF186A5703273874B522EF74B")
                .build()
        adView!!.loadAd(adRequest)

        //casting webView
        webView = findViewById(R.id.webView) as WebView
        webView!!.getSettings().javaScriptEnabled = true
        //on long click should not copy thr content
        webView!!.setOnLongClickListener(View.OnLongClickListener { true })
        webView!!.loadUrl("file:///android_asset/policy.html")

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}