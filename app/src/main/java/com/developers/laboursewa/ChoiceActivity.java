package com.developers.laboursewa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {
    Button scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        scan= (Button) findViewById(R.id.qr);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChoiceActivity.this,ScannerActivity.class);
                startActivity(intent);
            }
        });
    }
}
