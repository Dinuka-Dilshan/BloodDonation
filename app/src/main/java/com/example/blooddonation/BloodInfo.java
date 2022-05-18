package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import android.widget.Button;


public class BloodInfo extends AppCompatActivity {

    private Button btn_info;
    private Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_info);




        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        break;

                    case R.id.nav_bloodRequest:
                        startActivity(new Intent(getApplicationContext(), BloodRequestActivity.class));
                        break;

                    case R.id.nav_logout:
                        startActivity(new Intent(getApplicationContext(), BloodInfo.class));
                        break;

                    case R.id.nav_myProfile:
                        startActivity(new Intent(getApplicationContext(), MyProfile.class));
                        break;
                    case R.id.nav_organization:
                        startActivity(new Intent(getApplicationContext(), Organizations.class));
                        break;
                }
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }


        });

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