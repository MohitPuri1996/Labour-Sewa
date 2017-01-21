package com.developers.laboursewa.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.developers.laboursewa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tutorials extends Fragment {


    public Tutorials() {
        // Required empty public constructor
    }

    private ListView mList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2= inflater.inflate(R.layout.fragment_tutorials, container, false);
        mList= (ListView) v2.findViewById(R.id.tutorials);

        return v2;
    }

}
