package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Organizations extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    OrganizationsAdapter adapter;
    List<Organizer> organizers = new ArrayList<Organizer>();
    EditText name;
    EditText venue;
    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizations);
        name = findViewById(R.id.organizations_name);
        venue = findViewById(R.id.organizations_venue);
        time = findViewById(R.id.organizations_time);

        recyclerView = findViewById(R.id.org_recycler);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        OrganizationsAdapter.name = name;
        OrganizationsAdapter.venue = venue;
        OrganizationsAdapter.time = time;

        DAOOrganizer DAOOrg = new DAOOrganizer();
        DAOOrg.getAllOrganizers().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Organizer organizer = data.getValue(Organizer.class);
                    organizers.add(organizer);
                }
                adapter = new OrganizationsAdapter(organizers);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        DAOOrganizer org = new DAOOrganizer();

        findViewById(R.id.org_add).setOnClickListener(v->{
            Organizer organizer = new Organizer(name.getText().toString(),time.getText().toString(),venue.getText().toString());
            org.add(organizer).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->
            {

            });
        });

    }
}