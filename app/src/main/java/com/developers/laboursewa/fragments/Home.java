package com.developers.laboursewa.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.developers.laboursewa.Config;
import com.developers.laboursewa.R;
import com.developers.laboursewa.adapters.NewsAdapter;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements YouTubePlayer.OnInitializedListener{


    public Home() {
        // Required empty public constructor
    }
    private ListView newslistview,ratinglist;
    private static final int RECOVERY_DIALOG=1;
    private YouTubePlayerView mplayer;
    private ImageView play;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        play= (ImageView) v.findViewById(R.id.play);
        newslistview= (ListView) v.findViewById(R.id.newsList);
        new NewsData().execute();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/user/punjabiradiousa")));
            }
        });
        return v;
    }
    private class NewsData extends AsyncTask<Void,Void,Integer>{
        ArrayList<String> newslist=new ArrayList<>();
        ArrayList<String> linkslist=new ArrayList<>();
        ProgressDialog progress;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress=new ProgressDialog(getActivity());
            progress.setMessage("Loading....");
            progress.show();
        }

        @Override
        protected Integer doInBackground(Void... objects) {
            int res=0;
            try {
                String resu="[{\"title\":\"Revision of minimum rates of wages in the Central Sphere - Final Notification\",\"NewLink\":\"http://labour.nic.in//whatsnew/revision-minimum-rates-wages-central-sphere-final-notification\"},{\"title\":\"Appointment of AWCs and DWCs as Registering and Licensing Officers and nomination of Welfare Commissioners as Appellate Officers under the CL(R&A) Act, 1970, the BOCW(RECS) Act, 1996, and the ISMW(RECS) Act, 1979\",\"NewLink\":\"http://labour.nic.in//whatsnew/appointment-awcs-and-dwcs-registering-and-licensing-officers-and-nomination-welfare\"},{\"title\":\"Submission of Immovable Property Return for the year 2016 (as on 31.12.2016) in respect of all CLS Gr. A Officers - reg\",\"NewLink\":\"http://labour.nic.in//whatsnew/submission-immovable-property-return-year-2016-31122016-respect-all-cls-gr-officers-reg\"},{\"title\":\"Filling up the post of Presiding Officer of Central Government Industrial Tribunal-cum-Labour Court, Jaipur.\",\"NewLink\":\"http://labour.nic.in//whatsnew/filling-post-presiding-officer-central-government-industrial-tribunal-cum-labour-court-2\"},{\"title\":\"Filling up the post of Presiding Officer of Central Government Industrial Tribunal-cum-Labour Court, Ernakulam\",\"NewLink\":\"http://labour.nic.in//whatsnew/filling-post-presiding-officer-central-government-industrial-tribunal-cum-labour-court-1\"},{\"title\":\" Filling up the post of Presiding Officer of Central Government Industrial Tribunal-cum-Labour Court, Chandigarh-II (corrigendum)\",\"NewLink\":\"http://labour.nic.in//whatsnew/filling-post-presiding-officer-central-government-industrial-tribunal-cum-labour-court-0\"},{\"title\":\"Filling up the post of Presiding Officer of Central Government Industrial Tribunal-cum-Labour Court, Chandigarh-II\",\"NewLink\":\"http://labour.nic.in//whatsnew/filling-post-presiding-officer-central-government-industrial-tribunal-cum-labour-court\"},{\"title\":\"Draft Notification of the Ease of Compliance to Maintain Registers under various Labour Laws Rules, 2016- inviting comments from stakeholders.\",\"NewLink\":\"http://labour.nic.in//whatsnew/draft-notification-ease-compliance-maintain-registers-under-various-labour-laws-rules-2016\"},{\"title\":\"Submission oflmmovable property Retum for the yea.20l6 (as on 31.12.2016) in respect of all Officers of Minislry of Labour & Employment submission through cscms.nic-iD - regarding\",\"NewLink\":\"http://labour.nic.in//whatsnew/submission-oflmmovable-property-retum-yea20l6-31122016-respect-all-officers-minislry-labour\"},{\"title\":\"The Payment of Wages (Amendment) Ordinance, 2016\",\"NewLink\":\"http://labour.nic.in//whatsnew/payment-wages-amendment-ordinance-2016\"}]";
                Log.d("Home",resu);
                if(resu.length()>0){
                    parseDetail(resu);
                    res=1;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return res;
        }

        private void parseDetail(String res) {
            try{
                JSONArray resjson=new JSONArray(res);
                for(int i=0;i<resjson.length();i++){
                    JSONObject obj=resjson.getJSONObject(i);
                    String title=obj.getString("title");
                    newslist.add(title);
                    String links=obj.getString("NewLink");
                    linkslist.add(links);
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        @Override
        protected void onPostExecute(Integer o) {
            super.onPostExecute(o);
            if(o==1){
                NewsAdapter adapter1=new NewsAdapter(getActivity(),newslist,linkslist);
                newslistview.setAdapter(adapter1);
                progress.cancel();
            }
        }
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(b){
            youTubePlayer.loadVideo(Config.YOUTUBE_VIDEO_CODE);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
            youTubePlayer.play();
            Log.d("Homee","inonitialize");
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
}
