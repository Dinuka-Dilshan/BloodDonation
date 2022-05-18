package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Organizations extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    OrganizationsAdapter adapter;
    List<Organizer> organizers = new ArrayList<Organizer>();
    EditText name;
    TextView venue;
    EditText time;
    public static String updateId;
    String latitude = "";
    String longitude = "";


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



        //set data from map

        Intent intent = getIntent();
        if(intent.hasExtra("name")){

            Bundle extras = intent.getExtras();

            name.setText(extras.getString("name"));
            time.setText(extras.getString("time"));
            updateId = extras.getString("id");
            latitude = extras.getString("latitude");
            longitude = extras.getString("longitude");
            venue.setText(latitude+ " " + longitude);

        }



        //fetching data and feed adapter
        DAOOrganizer DAOOrg = new DAOOrganizer();
        DAOOrg.getAllOrganizers().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Organizer organizer = data.getValue(Organizer.class);
                    organizer.setId(data.getKey());
                    organizers.add(organizer);
                }
                adapter = new OrganizationsAdapter(organizers);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        //add org details
        DAOOrganizer org = new DAOOrganizer();

        findViewById(R.id.org_add).setOnClickListener(v->{

            if (getStringValue(name).length() == 0) {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            } else if (time.getText().toString().trim().length() == 0) {
                Toast.makeText(this, "Time cannot be empty", Toast.LENGTH_SHORT).show();
            }  else if (latitude.trim().length() == 0) {
                Toast.makeText(this, "Please select the location", Toast.LENGTH_SHORT).show();
            }else {
                Organizer organizer = new Organizer(name.getText().toString(),time.getText().toString(),latitude,longitude);
                org.add(organizer).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "Could not perform the action!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                });
            }


        });


        //update organization
        findViewById(R.id.org_update).setOnClickListener(v->{

            if (getStringValue(name).length() == 0) {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            } else if (time.getText().toString().trim().length() == 0) {
                Toast.makeText(this, "Time cannot be empty", Toast.LENGTH_SHORT).show();
            }  else if (time.getText().toString().trim().length() == 0) {
                Toast.makeText(this, "Time cannot be empty", Toast.LENGTH_SHORT).show();
            } else if (latitude.trim().length() == 0) {
                Toast.makeText(this, "Please select the location", Toast.LENGTH_SHORT).show();
            }else {

                DatabaseReference ref = new DAOOrganizer().getRef();
                Query pendingTasks = ref.orderByKey().equalTo(updateId);
                pendingTasks.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childNodes: snapshot.getChildren()) {
                            childNodes.getRef().child("name").setValue(name.getText().toString());
                            childNodes.getRef().child("time").setValue(time.getText().toString());
                            childNodes.getRef().child("latitude").setValue(latitude);
                            childNodes.getRef().child("longitude").setValue(longitude);

                        }
                        Toast.makeText(Organizations.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Organizations.this, "Could not perform the action!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());

                    }
                });

            }


        });

        venue.setOnClickListener(v->{
            Intent i = new  Intent(getApplicationContext(),Map.class);
            i.putExtra("name",name.getText().toString().trim());
            i.putExtra("time",time.getText().toString().trim());
            i.putExtra("id",updateId);

            startActivity(i);
        });


    }

    private String getStringValue(EditText txt) {
        return txt.getText().toString().trim();
    }


}