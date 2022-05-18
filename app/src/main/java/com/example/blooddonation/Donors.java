package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Donors extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomAdapter adapter;
    List<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors);


        NavigationView nav = findViewById(R.id.nav_view_donors);
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

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DAOUser DAO = new DAOUser();
        DAO.getAllDonors().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    User user = data.getValue(User.class);
                    users.add(user);
                }
                adapter = new CustomAdapter(users);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
}