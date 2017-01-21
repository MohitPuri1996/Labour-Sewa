package com.developers.laboursewa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.developers.laboursewa.R;

import java.util.ArrayList;

/**
 * Created by Amanjeet Singh on 21-Jan-17.
 */

public class RatingAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> jobname;
    ArrayList<Float> rating;
    public RatingAdapter(Context context,ArrayList<String> jobname,ArrayList<Float> rating){
        this.context=context;
        this.jobname=jobname;
        this.rating=rating;
    }
    @Override
    public int getCount() {
        return jobname.size();
    }

    @Override
    public Object getItem(int i) {
        return jobname.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.rating_list,null);
        TextView job= (TextView) view.findViewById(R.id.jobname);
        job.setText(jobname.get(i));
        RatingBar rate= (RatingBar) view.findViewById(R.id.rating);
        float val=rating.get(i);
        rate.setRating(val);
        return view;
    }
}
