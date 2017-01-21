package com.developers.laboursewa;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gagandeep on 1/21/2017.
 */

public class CustomSkillAdapter extends BaseAdapter {
    Context c;
    ArrayList<String> skills;
    int count;

    CustomSkillAdapter(Context c, ArrayList<String> skills){
        this.c=c;
        this.skills=skills;
    }

    @Override
    public int getCount() {
        return skills.size();
    }

    @Override
    public Object getItem(int i) {
        return skills.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            view = LayoutInflater.from(c).inflate(R.layout.skill_category_adapter,viewGroup,false);
        }
        String skill = (String) this.getItem(i);
        TextView skillName = (TextView) view.findViewById(R.id.textView);
        skillName.setText(skill);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        switch (skill){
            case "Carpentry": imageView.setImageResource(R.drawable.carpentry);
                break;
            case "Blacksmith": imageView.setImageResource(R.drawable.blacksmith);
                break;
            case "Painter": imageView.setImageResource(R.drawable.painter);
                break;
            case "Fitter": imageView.setImageResource(R.drawable.fitter);
                break;
            case "Plumber": imageView.setImageResource(R.drawable.plumber);
                break;
            case "Brick Layer": imageView.setImageResource(R.drawable.bricklaying);
                break;
            case "Electrician": imageView.setImageResource(R.drawable.electrician);
                break;
            case "Concrete": imageView.setImageResource(R.drawable.concrete
            );
                break;

        }
        return view;
    }
}
