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

                        Toast.makeText(MainActivity.this, "Engkusg", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hindi:
                        sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        editor = sharedPref.edit();
                        editor.putString("language","1");
                        editor.apply();

                        Toast.makeText(MainActivity.this, "HINID", Toast.LENGTH_SHORT).show();
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
