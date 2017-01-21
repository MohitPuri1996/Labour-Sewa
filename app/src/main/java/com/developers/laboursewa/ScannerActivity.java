package com.developers.laboursewa;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView=new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void handleResult(Result result) {
        Log.d("ScannerActivity",result.getText());
        Toast.makeText(this,""+result.getText(),Toast.LENGTH_SHORT).show();
        parseDetails(result);
        mScannerView.resumeCameraPreview(this);
    }

    private void parseDetails(Result result) {
        String res=result.getText();
        try {
            DocumentBuilderFactory doc=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=doc.newDocumentBuilder();
            InputSource is=new InputSource();
            is.setCharacterStream(new StringReader(res));
            Document d = db.parse(is);
            NodeList nodes = d.getElementsByTagName("PrintLetterBarcodeData");
            Element line = (Element) nodes.item(0);
            String uid=line.getAttribute("uid");
            String name=line.getAttribute("name");
            String gender=line.getAttribute("gender");
            String yob=line.getAttribute("yob");
            String house=line.getAttribute("house");
            String loc=line.getAttribute("loc");
            String state=line.getAttribute("state");
            String pc=line.getAttribute("pc");
            Toast.makeText(ScannerActivity.this,uid+" "+name,Toast.LENGTH_SHORT).show();
            new Submitting().execute(uid,name,gender,yob,house,loc,state,pc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
    private class Submitting extends AsyncTask<String,Void,String>{
        ProgressDialog progress;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress=new ProgressDialog(ScannerActivity.this);
            progress.setMessage("Registring....");
            progress.show();
        }

        @Override
        protected String doInBackground(String... string) {
            String uid=string[0];
            String name=string[1];
            String gender=string[2];
            String yob=string[3];
            String house=string[4];
            String loc=string[5];
            String state=string[6];
            String pc=string[7];
            JSONObject jsonObject=new JSONObject();
            String response="";
            try {
                jsonObject.put("uid",uid);
                jsonObject.put("name",name);
                jsonObject.put("gender",gender);
                jsonObject.put("yob",yob);
                jsonObject.put("house",house);
                jsonObject.put("loc",loc);
                jsonObject.put("state",state);
                jsonObject.put("pc",pc);
                Toast.makeText(ScannerActivity.this,""+jsonObject,Toast.LENGTH_SHORT).show();
                URL url = new URL("https://6824f751.ngrok.io/save-a-user"); //Enter URL here
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
                httpURLConnection.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
                httpURLConnection.connect();
                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(jsonObject.toString());
                wr.flush();
                wr.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                if(inputStream==null){
                    Log.e("Manual","input stream is null");
                }
                StringBuffer buffer=new StringBuffer();
                String line="";
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                while((line=bufferedReader.readLine())!=null){
                    buffer.append(line+"\n");
                }
                response=buffer.toString();
                Log.d("SubmitActivity",response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String res) {
            super.onPostExecute(res);
            Log.d("TAG","sca"+res);
            String r=res.trim();
            if(r.equals("WOHO!")){
                Toast.makeText(ScannerActivity.this,"User Successfully Registered",Toast.LENGTH_SHORT).show();
                progress.cancel();
            }
        }
    }
}
