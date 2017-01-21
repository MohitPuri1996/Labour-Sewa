package com.developers.laboursewa.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.developers.laboursewa.Config;
import com.developers.laboursewa.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements YouTubePlayer.OnInitializedListener{


    public Home() {
        // Required empty public constructor
    }
    private ListView newslist,ratinglist;
    private static final int RECOVERY_DIALOG=1;
    private YouTubePlayerView mplayer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        mplayer= (YouTubePlayerView) v.findViewById(R.id.player);
        mplayer.initialize(Config.DEVELOPER_KEY,this);
        newslist= (ListView) v.findViewById(R.id.newsList);
        ratinglist= (ListView) v.findViewById(R.id.rating);
        return v;
    }
    private class NewsData extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.loadVideo(Config.YOUTUBE_VIDEO_CODE);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
       if(youTubeInitializationResult.isUserRecoverableError()){
           youTubeInitializationResult.getErrorDialog(getActivity(),RECOVERY_DIALOG).show();
       }
        else {
           String errormsg=String.format("Error in Initialization",youTubeInitializationResult.toString());
           Toast.makeText(getActivity(),""+errormsg,Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RECOVERY_DIALOG){
            getYoutubePlayerProvider().initialize(Config.DEVELOPER_KEY,this);
        }
    }
    private YouTubePlayer.Provider getYoutubePlayerProvider(){
        return (YouTubePlayerView) getView().findViewById(R.id.player);
    }
}
