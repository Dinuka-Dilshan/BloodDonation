package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        User newUser = new User("Lasith",0770543422, "address", "A" , "userName",  "password",  "category");
        User newUser2 = new User("Lasith",0770543422, "address", "A" , "userName",  "password",  "category");
        users.add(newUser);
        users.add(newUser2);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter(users);
        recyclerView.setAdapter(adapter);

    }
}