package com.wondrousapps.flagquiz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


import java.util.ArrayList;
import java.util.Locale;

import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.model.FlagModel;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<FlagModel> itemsData;
    static Context context;
    private InterstitialAd mInterstitialAd;
    private boolean isInternetConnection;
    private static final int Content_ITEM_VIEW_TYPE = 0;
   // public FrameLayout fl_adplaceholder;
    private static final int LIST_AD_DELTA = 5;
    private static final int NATIVE_EXPRESS_AD_VIEW_TYPE = 1;

    public RecyclerViewAdapter(Context ctx,ArrayList<FlagModel> itemsData) {
        this.itemsData = itemsData;
        this.context = ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView;
        if (viewType == Content_ITEM_VIEW_TYPE) {
            // case Content_ITEM_VIEW_TYPE:
                /*View contentItemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.content_row_item, viewGroup, false);
                */
            // create a new view
            itemLayoutView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.flag_details, parent, false);

            return new ViewHolder(itemLayoutView);

            // case NATIVE_EXPRESS_AD_VIEW_TYPE:
            // fall through
            // default:

        } else {
            itemLayoutView = LayoutInflater.from(
                    parent.getContext()).inflate(R.layout.activity_native_app_install_ad,
                    parent, false);
            return new NativeAppInstallHolder(itemLayoutView);
        }
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position){
        int viewType = getItemViewType(position);
        if (viewType == Content_ITEM_VIEW_TYPE) {
            // case Content_ITEM_VIEW_TYPE:
            ViewHolder viewHolder = (ViewHolder) holder;
            //   viewHolder
            // - get data from your itemsData at this position
            // - replace the contents of the view with that itemsData
            //final FlagModel dataWrapper = itemsData.get(position);
            //int i = obj.getImage();
            //viewHolder.tv_app_name.setText(dataWrapper.getDetails());
            FlagModel model=itemsData.get(position);
            if (position%2==0){
            viewHolder.card_shade.setBackgroundColor(Color.parseColor("#9FA8DA"));
            }else {
            viewHolder.card_shade.setBackgroundColor(Color.parseColor("#F48FB1"));
            }
//            String[] demo=context.getResources().getStringArray(R.array.demo);
//            //hi_IN en_US
//            String id = "tab_Books_" + Locale.getDefault();//.getDisplayLanguage();
//            Log.d("id",id);
//            if (id.equals("tab_Books_en")){
//                viewHolder.flagname.setText(model.getAns());
//            }else {
//                if (id.equals("tab_Books_hi_IN")) {
//                    String[] demo2 = context.getResources().getStringArray(R.array.demo_hi);
//                    String a1 = model.getAns();
//                    Log.d("a1", a1);
//                    if (position < demo2.length) {
//                        viewHolder.flagname.setText(demo2[position]);
//                    }
//                }if (id.equals("tab_Books_de_DE")){
//                    String[] demo2=context.getResources().getStringArray(R.array.demo);
//                    String a1 = model.getAns();
//                    Log.d("a1", a1);
//                    if (position < demo2.length) {
//                        viewHolder.flagname.setText(demo2[position]);
//                    }
//                }if (id.equals("tab_Books_fr_FR")){
//                    String[] demo2=context.getResources().getStringArray(R.array.demo);
//                    String a1 = model.getAns();
//                    Log.d("a1", a1);
//                    if (position < demo2.length) {
//                        viewHolder.flagname.setText(demo2[position]);
//                    }
//                }
//                else {
//                    viewHolder.flagname.setText(model.getAns());
//                }
//            }
            viewHolder.flagname.setText(model.getAns());

            String path_name=model.getImg().substring(4).replaceAll("-","_");
            Log.d("path",path_name);
            int  resID = context.getResources().getIdentifier(path_name , "drawable", context.getPackageName());
            viewHolder.flagimg.setImageDrawable(context.getResources().getDrawable(resID));
        }
    }

    @Override
    public int getItemCount() {

        if (itemsData != null)
            return itemsData.size();
        else
            return 0;
        /*
        int additionalContent = 0;
        if (itemsData.size() > 0 && LIST_AD_DELTA > 0 && itemsData.size() > LIST_AD_DELTA) {
            additionalContent = itemsData.size() / LIST_AD_DELTA;
            Log.d("FDCHJDCGHKJ",""+additionalContent);
        }
        return itemsData.size() + additionalContent;*/
        //  return itemsData.size();
    }
    @Override
    public int getItemViewType(int position) {

        if (position > 0 && position % LIST_AD_DELTA == 0) {
            return NATIVE_EXPRESS_AD_VIEW_TYPE;
        }
        return Content_ITEM_VIEW_TYPE;
        /*if (context.toString().contains("SingleListActivity")) {
            return (position % 3 == 0 && position != 0) ? NATIVE_EXPRESS_AD_VIEW_TYPE
                    : Content_ITEM_VIEW_TYPE;
        } else {
            return Content_ITEM_VIEW_TYPE;
        }*/
    }
    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView flagname;
        ImageView flagimg;
        RelativeLayout card_shade;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            flagname= (TextView) itemView.findViewById(R.id.country_title);
            flagimg= (ImageView) itemView.findViewById(R.id.flag_iv);
            card_shade= (RelativeLayout) itemView.findViewById(R.id.card_shade);
            //        tv_app_name = (TextView) itemLayoutView.findViewById(R.id.tv_app_name);
           // detailTV = (TextView) itemLayoutView.findViewById(R.id.codeDetailTV);
        }
    }

    public class NativeAppInstallHolder extends RecyclerView.ViewHolder{

        public AdView fl_adplaceholder;
        NativeAppInstallHolder(View view) {
            super(view);
            fl_adplaceholder=(AdView) view.findViewById(R.id.fl_adplaceholder);
            AdRequest adRequest = new AdRequest.Builder().build();
            //.addTestDevice(TEST_DEVICE_ID)

            fl_adplaceholder.loadAd(adRequest);
            fl_adplaceholder.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    //    Toast.makeText(getApplicationContext(), "Closing the Banner Ad", Toast.LENGTH_LONG).show();
                }
                @Override
                public void onAdLoaded() {
                    //  Toast.makeText(getApplicationContext(), "Banner Ad is loaded", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
