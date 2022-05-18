package com.example.blooddonation;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOOrganizer {
    private DatabaseReference databaseReference;


    public DAOOrganizer() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://blooddonation-dfcf9-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = db.getReference("Organizer");
    }

    public Task<Void> add(Organizer org)
    {
        return databaseReference.push().setValue(org);
    }

    public DatabaseReference getAllOrganizers() {

        return databaseReference;

    }

    public DatabaseReference getRef() {

        return databaseReference;

    }

}
