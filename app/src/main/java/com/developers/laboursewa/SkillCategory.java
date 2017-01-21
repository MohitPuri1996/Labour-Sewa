package com.developers.laboursewa;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
/*
* Activity not linked to any activity.
* Shows the skill category with images for workers. */
public class SkillCategory extends AppCompatActivity {

    CustomSkillAdapter customSkillAdapter;
    GridView gridView;
    ArrayList<String> skills;
    Button otherJob,dialogOtherJob, dismissButton;
    EditText newJobType;
    String newJobText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_category);
        gridView = (GridView) findViewById(R.id.gridview);

        otherJob = (Button) findViewById(R.id.add_job);
        otherJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addJob();
            }
        });

        skills = new ArrayList<>();
        skills.add("Carpentry");
        skills.add("Blacksmith");
        skills.add("Painter");
        skills.add("Fitter");
        skills.add("Plumber");
        skills.add("Brick Layer");
        skills.add("Electrician");
        skills.add("Concrete");
        customSkillAdapter = new CustomSkillAdapter(this, skills);
        gridView.setAdapter(customSkillAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences preferences=SkillCategory.this.getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putInt("number",i);
                editor.commit();
                Intent intent=new Intent(SkillCategory.this,TabActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addJob(){
        final Dialog dialog = new Dialog(SkillCategory.this);
        //setting custom layout to dialog
        dialog.setContentView(R.layout.dialog_for_job);
        dialog.setTitle("Add Your Job Type");
        newJobType = (EditText) findViewById(R.id.jobType);
        dialogOtherJob = (Button) findViewById(R.id.addJob);
        /*dialogOtherJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newJobText = newJobType.getText().toString();
                if(!(newJobText.equals(""))){
                    dialog.dismiss();
                }
                else Toast.makeText(SkillCategory.this, "Enter Some Job Type", Toast.LENGTH_SHORT).show();
            }
        });*/

        //adding button click event
        dismissButton = (Button) dialog.findViewById(R.id.dismiss);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
