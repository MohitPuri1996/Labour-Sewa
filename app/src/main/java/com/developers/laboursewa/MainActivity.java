package com.developers.laboursewa;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup languagePref;
    private int r;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectLanguage();
    }
    public void selectLanguage(){
        final Dialog dialog = new Dialog(MainActivity.this);
        //setting custom layout to dialog
        dialog.setContentView(R.layout.dialog_for_language);
        languagePref = (RadioGroup) dialog.findViewById(R.id.language_pref);
        languagePref.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.english:
                        sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("language","0");
                        editor.apply();
                        Toast.makeText(MainActivity.this, "English", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences= MainActivity.this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        r =preferences.getInt("reg",0);
                        if(r==1){
                            Intent intent=new Intent(MainActivity.this,TabActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.hindi:
                        sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        editor = sharedPref.edit();
                        editor.putString("language","1");
                        editor.apply();
                        SharedPreferences prefe= MainActivity.this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        r =prefe.getInt("reg",0);
                        if(r==1){
                            Intent intent=new Intent(MainActivity.this,TabActivity.class);
                            startActivity(intent);
                        }
                        Toast.makeText(MainActivity.this, "HINDI", Toast.LENGTH_SHORT).show();
                        break;
                }
                dialog.dismiss();
                Intent in = new Intent(MainActivity.this,ChoiceActivity.class);
                startActivity(in);
            }
        });
        dialog.show();
    }
}
