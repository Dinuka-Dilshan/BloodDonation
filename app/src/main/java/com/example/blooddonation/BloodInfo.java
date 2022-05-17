package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class BloodInfo extends AppCompatActivity {

    private Button btn_info;
    private Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_info);

        btn_info = findViewById(R.id.btn_info);
        btnGetStarted = findViewById(R.id.btnGetStarted);


        btn_info.setOnClickListener(v->{
//            Intent i = new Intent(getApplicationContext(),Donors.class);
//            startActivity(i);
        });

        btnGetStarted.setOnClickListener(v->{
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        });
    }
}