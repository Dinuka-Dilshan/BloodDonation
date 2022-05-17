package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class BloodRequestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BloodRequestCustomAdapter adapter;
    List<BloodRequest> requests = new ArrayList<BloodRequest>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);

        BloodRequest request = new BloodRequest("I need O+ Blood dssfsfsdf ", "Kamal", "0702629599", "Galle");
        BloodRequest request1 = new BloodRequest("I need O+ Blood fg sdgsgfdsfgsdfs dfsfdsfdsf", "Kamal", "0702629599", "Galle");
        BloodRequest request2 = new BloodRequest("I need O+ Blood", "Kamal", "0702629599", "Galle");
        BloodRequest request3 = new BloodRequest("I need O+ Blood", "Kamal", "0702629599", "Galle");
        BloodRequest request4 = new BloodRequest("I need O+ Blood", "Kamal", "0702629599", "Galle");

        requests.add(request);
        requests.add(request1);
        requests.add(request2);
        requests.add(request3);
        requests.add(request4);


        recyclerView = findViewById(R.id.recycler_view_blood_request);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BloodRequestCustomAdapter(requests);
        recyclerView.setAdapter(adapter);

    }
}