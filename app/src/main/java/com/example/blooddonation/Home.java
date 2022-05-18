package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    private Button btn_donor;
    private Button btn_blood_req;
    private Button btn_organization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavigationView nav = findViewById(R.id.nav_view_home);
        System.out.println(nav);
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
                        finish();
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