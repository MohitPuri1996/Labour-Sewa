package com.developers.laboursewa.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.developers.laboursewa.Data;
import com.developers.laboursewa.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Jobs extends Fragment{

    public Jobs() {
        // Required empty public constructor
    }
    private ListView jobs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_jobs, container, false);

        TextView name=(TextView)v.findViewById(R.id.jobname);
        name.setText("Rajesh Sharma");
        TextView rating=(TextView)v.findViewById(R.id.jobRating);
        rating.setText("4.5");
        TextView number=(TextView)v.findViewById(R.id.jobNumber);
        number.setText("8447800758");
        TextView nature=(TextView)v.findViewById(R.id.jobNature);
        nature.setText("Labour");
        TextView payCycle=(TextView)v.findViewById(R.id.pay_cycle);
        nature.setText("Pay Cycle : Per_Day");
        new JobsFetch().execute();
        return v;
        //QueryTest/pin/nature
    }
    class JobsFetch extends AsyncTask<Void,Void,Integer>{
        ArrayList<String> info;
        @Override
        protected Integer doInBackground(Void... voids) {
            int res=0;
            String url="https://6824f751.ngrok.io/QueryTest/201301/Labour";
            HttpURLConnection connection=null;
            BufferedReader bufferedReader=null;
            try{
                String response="";
                URL url1=new URL(url);
                connection= (HttpURLConnection) url1.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream inputStream=connection.getInputStream();
                if(inputStream==null){
                    Log.e("Jobs","input stream is null");
                }
                StringBuffer buffer=new StringBuffer();
                String line="";
                bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                while((line=bufferedReader.readLine())!=null){
                    buffer.append(line+"\n");
                }
                response=buffer.toString();
                if(response.length()>0){
                    Log.d("Jobs",response);
                    parseDetails(response);
                    res=1;
                }
            }
            catch(Exception e) {
                Log.e("Jobs", " " + e);
            }
            return res;
        }

        private void parseDetails(String response) {
            try {
                JSONArray arr=new JSONArray(response);
                for(int i=0;i<arr.length();i++){
                    JSONObject obj=arr.getJSONObject(i);
                    String nature=obj.getString("Nature");
                    String pin=obj.getString("Location");
                    String pay=obj.getString("Paid-cycle");
                    JSONArray ar=obj.getJSONArray("combined");
                    for(int j=0;j<ar.length();j++){
                        JSONObject o=ar.getJSONObject(j);
                        String name=o.getString("Name");
                        String number=o.getString("Contact Number");
                        String rating=o.getString("Rating");
                        Data.setName(name);
                        Data.setNumber(number);
                        Data.setRating(rating);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

        }
    }

}
