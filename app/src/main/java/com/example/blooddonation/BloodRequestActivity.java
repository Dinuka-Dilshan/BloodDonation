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
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BloodRequestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BloodRequestCustomAdapter adapter;
    List<BloodRequest> requests = new ArrayList<BloodRequest>();
    FloatingActionButton  btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);


        NavigationView nav = findViewById(R.id.nav_view_blood_requests);
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



        btn = findViewById(R.id.floatingActionButton);

        recyclerView = findViewById(R.id.recycler_view_blood_request);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseDatabase db = FirebaseDatabase.getInstance("https://blooddonation-dfcf9-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference databaseReference = db.getReference("BloodRequest");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    BloodRequest req = data.getValue(BloodRequest.class);
                    requests.add(req);
                }
                adapter = new BloodRequestCustomAdapter(requests);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });







        btn.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),AddBloodRequest.class));
        });

    }


}