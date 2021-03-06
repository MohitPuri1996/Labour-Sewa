package com.developers.laboursewa.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.developers.laboursewa.R;

import java.util.ArrayList;

/**
 * Created by Amanjeet Singh on 21-Jan-17.
 */

public class NewsAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> news;
    ArrayList<String> links;
    String lin;
    public NewsAdapter(Context context,ArrayList<String> news,ArrayList<String> links){
        this.context=context;
        this.news=news;
        this.links=links;
    }
    @Override
    public int getCount() {
        return news.size() ;
    }

    @Override
    public Object getItem(int i) {
        return news.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.news_list,null);
        TextView title= (TextView) view.findViewById(R.id.newstitle);
        TextView tags= (TextView) view.findViewById(R.id.source);
        String t=news.get(i);
        lin=links.get(i);
        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(lin));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                context.startActivity(intent);
            }
        });
        title.setText(t);
        tags.setText(lin);
        return view;
    }
}
