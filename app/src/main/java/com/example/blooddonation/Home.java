package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button btn_donor;
    private Button btn_blood_req;
    private Button btn_organization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_donor = findViewById(R.id.btn_donor);
        btn_blood_req = findViewById(R.id.btn_blood_req);
        btn_organization = findViewById(R.id.btn_organization);

        btn_donor.setOnClickListener(v->{
            Intent i = new Intent(getApplicationContext(),Donors.class);
            startActivity(i);
        });

        btn_blood_req.setOnClickListener(v->{
            Intent i = new Intent(getApplicationContext(),BloodRequestActivity.class);
            startActivity(i);
        });

        btn_organization.setOnClickListener(v->{
            Intent i = new Intent(getApplicationContext(),Organizations.class);
            startActivity(i);
        });



    }
}