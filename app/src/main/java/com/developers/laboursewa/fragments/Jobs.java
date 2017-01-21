package com.developers.laboursewa.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.developers.laboursewa.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Jobs extends Fragment implements YouTubePlayer.OnInitializedListener{


    public Jobs() {
        // Required empty public constructor
    }

    private static final int RECOVERY_DIALOG=2;
    private YouTubePlayerView mplayer1;
    private ListView jobs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_jobs, container, false);
        mplayer1= (YouTubePlayerView) v.findViewById(R.id.toolbar_video);
        jobs=(ListView)v.findViewById(R.id.joblisting);
        return v;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){

        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
