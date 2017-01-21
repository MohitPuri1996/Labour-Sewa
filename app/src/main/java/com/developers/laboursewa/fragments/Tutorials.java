package com.developers.laboursewa.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.developers.laboursewa.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tutorials extends Fragment {


    public Tutorials() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2= inflater.inflate(R.layout.fragment_tutorials, container, false);
        final TextView title=(TextView) v2.findViewById(R.id.vidtitle);
        title.setText("Plumbing");
        final TextView link=(TextView) v2.findViewById(R.id.link);
        link.setText("https://www.youtube.com/watch?v=-YCp-msm3oM&list=PL9Q_x6Cmsav-s_OkSsAdCmkVfnB8vZmwy");
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link.getText().toString())));
            }
        });
        return v2;
    }

}
