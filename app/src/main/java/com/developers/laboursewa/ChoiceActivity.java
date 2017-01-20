package com.developers.laboursewa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Button qrScan = (Button) findViewById(R.id.qr);
        Button manualDetail = (Button) findViewById(R.id.details);

        manualDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChoiceActivity.this,ManualUserDetail.class);
                startActivity(i);
            }
        });
    }
}
