package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

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
        organizers.add(new Organizer("DSE Blood Donator","3.43422,3.4323","2022/12/12"));
        recyclerView = findViewById(R.id.org_recycler);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        OrganizationsAdapter.name = name;
        adapter = new OrganizationsAdapter(organizers);
        recyclerView.setAdapter(adapter);
    }
}