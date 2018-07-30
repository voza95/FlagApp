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
import java.util.List;

import com.wondrousapps.flagquiz.R;
import com.wondrousapps.flagquiz.model.FlagModel;

/**
 * Created by ntf-42 on 15/3/18.
 */

public class FlagAdapter extends RecyclerView.Adapter<FlagAdapter.FlagViewHolder> {

    Context context;
    List<FlagModel> models;
    private static final int LIST_AD_DELTA = 5;
    private static final int CONTENT = 0;
    private static final int AD = 1;



    public FlagAdapter(Context context, List<FlagModel> models) {
        Log.d("called","callled");
        this.context = context;
        this.models = models;
    }

    @Override
    public FlagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);

        View view=inflater.inflate(R.layout.flag_details,null);
        return new FlagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlagViewHolder holder, int position) {

        if (position%2==0){
            holder.card_shade.setBackgroundColor(Color.parseColor("#9FA8DA"));
        }else {
            holder.card_shade.setBackgroundColor(Color.parseColor("#F48FB1"));
        }
       FlagModel model=models.get(position);
        holder.flagname.setText(model.getAns());

        String path_name=model.getImg().substring(4).replaceAll("-","_");
        int  resID = context.getResources().getIdentifier(path_name , "drawable", context.getPackageName());
        holder.flagimg.setImageDrawable(context.getResources().getDrawable(resID));
    }
    @Override
    public int getItemCount() {
        return models.size();
    }

    class FlagViewHolder extends RecyclerView.ViewHolder{
        TextView flagname;
        ImageView flagimg;
        RelativeLayout card_shade;
        public FlagViewHolder(View itemView) {
            super(itemView);

            flagname= (TextView) itemView.findViewById(R.id.country_title);
            flagimg= (ImageView) itemView.findViewById(R.id.flag_iv);
            card_shade= (RelativeLayout) itemView.findViewById(R.id.card_shade);

        }
    }
}
