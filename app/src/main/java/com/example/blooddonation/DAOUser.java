package com.example.blooddonation;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOUser {
    private DatabaseReference databaseReference;


    public DAOUser() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://blooddonation-dfcf9-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = db.getReference("User");
    }

    public Task<Void> register(User user) {

        return databaseReference.push().setValue(user);

    }

    public Query checkAvailableUser(String userName) {

        return databaseReference.orderByChild("userName").equalTo(userName);

    }
    public Task<Void> add(Organizer org)
    {
        return databaseReference.push().setValue(org);
    }

    public Query getAllDonors() {

        return databaseReference.orderByChild("category");

    }


    public DatabaseReference getRef(){
        return  databaseReference;
    }

}
