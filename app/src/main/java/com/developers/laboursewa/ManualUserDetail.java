package com.developers.laboursewa;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.BufferOverflowException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ManualUserDetail extends AppCompatActivity {

    private EditText dob,uid,name,street,pin,loc,state;
    private Spinner spinner;
    private Button submit;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_user_detail);
        myCalendar = Calendar.getInstance();
        uid = (EditText) findViewById(R.id.uid);
        name = (EditText) findViewById(R.id.name);
        spinner = (Spinner) findViewById(R.id.spinner);
        street = (EditText) findViewById(R.id.street);
        pin = (EditText) findViewById(R.id.pincode);
        loc=(EditText) findViewById(R.id.loc);
        state = (EditText) findViewById(R.id.state);
        dob = (EditText) findViewById(R.id.state);
        submit= (Button) findViewById(R.id.button);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ManualUserDetail.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ui=uid.getText().toString();
                String na=name.getText().toString();
                String gender=spinner.getSelectedItem().toString();
                String ho=street.getText().toString();
                String pincode=pin.getText().toString();
                String loca=loc.getText().toString();
                String st=state.getText().toString();
                String yob=dob.getText().toString();
                SharedPreferences preferences=ManualUserDetail.this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("pincode",pincode);
                editor.commit();

                new Submitting().execute(ui,na,gender,yob,ho,loca,st,pincode);
            }
        });
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(sdf.format(myCalendar.getTime()));
    }
    private class Submitting extends AsyncTask<String,Void,String> {
        ProgressDialog progress;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress=new ProgressDialog(ManualUserDetail.this);
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
            String response="";
            JSONObject jsonObject=new JSONObject();
            try {

                jsonObject.put("uid",uid);
                jsonObject.put("name",name);
                jsonObject.put("gender",gender);
                jsonObject.put("yob",yob);
                jsonObject.put("house",house);
                jsonObject.put("loc",loc);
                jsonObject.put("state",state);
                jsonObject.put("pc",pc);
                Log.d("Manual ",jsonObject+"");
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
            String r=res.trim();
            Log.d("TAG","scaaaaaaaaaaaaaaaaaaa"+r);
            if(r.equals("1")){
                Log.d("TAG","sca"+r);
                Toast.makeText(ManualUserDetail.this,"User Succsessfully Registered!!",Toast.LENGTH_SHORT).show();
                progress.cancel();
                SharedPreferences preferences=ManualUserDetail.this.getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putInt("reg",1);
                editor.commit();
                Intent intent=new Intent(ManualUserDetail.this,SkillCategory.class);
                startActivity(intent);
            }

        }
    }
}
