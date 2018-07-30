package com.wondrousapps.flagquiz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.NativeExpressAdView;

import java.util.List;

import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.activity.FlagInfo;
import com.wondrousapps.flagquiz.model.FlagModel;

/**
 * Created by Old'ster on 2/27/2017.
 */

public class AdsNativeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int COMMENT_ITEM_VIEW_TYPE = 0;
    private static final int ADS_NATIVE_ITEM_VIEW_TYPE = 1;

    private Context mContext;
    private List<Object> mRecyclerViewItem;

    public AdsNativeAdapter(Context mContext, List<Object> mRecyclerViewItem,List<FlagModel> flagModels) {
        this.mContext = mContext;
        this.mRecyclerViewItem = mRecyclerViewItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ADS_NATIVE_ITEM_VIEW_TYPE:
                View nativeExpressLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.native_express_ad_container, parent, false);
                return new NativeExpressAdViewHolder(nativeExpressLayoutView);
            case COMMENT_ITEM_VIEW_TYPE:
            default:
                View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.flag_details, parent, false);
                return new CommentItemViewHolder(menuItemLayoutView);
        }
    }
    public class NativeExpressAdViewHolder extends RecyclerView.ViewHolder {

        public NativeExpressAdViewHolder(View itemView) {
            super(itemView);
        }
    }
    public class CommentItemViewHolder extends RecyclerView.ViewHolder {

        TextView flagname;
        ImageView flagimg;
        RelativeLayout card_shade;

        public CommentItemViewHolder(View itemView) {
            super(itemView);
            flagname= (TextView) itemView.findViewById(R.id.country_title);
            flagimg= (ImageView) itemView.findViewById(R.id.flag_iv);
            card_shade= (RelativeLayout) itemView.findViewById(R.id.card_shade);
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ADS_NATIVE_ITEM_VIEW_TYPE:
                NativeExpressAdViewHolder nativeExpressAdHolder = (NativeExpressAdViewHolder) holder;
                NativeExpressAdView adView = (NativeExpressAdView) mRecyclerViewItem.get(position);

                ViewGroup adCardView = (ViewGroup) nativeExpressAdHolder.itemView;
                adCardView.removeAllViews();

                if (adView.getParent() != null) {
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }
                adCardView.addView(adView);
                break;
            case COMMENT_ITEM_VIEW_TYPE:
            default:
                CommentItemViewHolder commentItemHolder = (CommentItemViewHolder) holder;

                if (position%2==0){
                commentItemHolder.card_shade.setBackgroundColor(Color.parseColor("#3F51B5"));
                }else {
                commentItemHolder.card_shade.setBackgroundColor(Color.parseColor("#E91E63"));
                }
                FlagModel data = (FlagModel) mRecyclerViewItem.get(position);
                commentItemHolder.flagname.setText("Country name: "+data.getAns());
                String path_name=data.getImg().substring(4).replaceAll("-","_");
                int  resID = mContext.getResources().getIdentifier(path_name , "drawable", mContext.getPackageName());
                commentItemHolder.flagimg.setImageDrawable(mContext.getResources().getDrawable(resID));
                break;
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position==0)
            return COMMENT_ITEM_VIEW_TYPE;
        return (position % FlagInfo.ITEMS_PER_AD == 0) ? ADS_NATIVE_ITEM_VIEW_TYPE : COMMENT_ITEM_VIEW_TYPE;
       // return COMMENT_ITEM_VIEW_TYPE;
    }
    @Override
    public int getItemCount() {
        return mRecyclerViewItem.size();
    }
}
