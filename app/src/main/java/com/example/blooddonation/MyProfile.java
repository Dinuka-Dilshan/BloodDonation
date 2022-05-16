package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends AppCompatActivity {

    Button btn;
    EditText phone;
    EditText name;
    EditText address;
    EditText district;
    EditText bloodGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        btn = findViewById(R.id.button);
        phone = findViewById(R.id.my_profile_contact_no);
        name  =findViewById(R.id.my_profile_name);
        address = findViewById(R.id.my_profile_address);
        district = findViewById(R.id.my_profile_district);
        bloodGroup = findViewById(R.id.my_profile_blood_group);


        Intent i = getIntent();
        String userName = i.getStringExtra("userName");

        DAOUser db = new DAOUser();
        db.checkAvailableUser(userName).addListenerForSingleValueEvent(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()){
                    name.setText(ds.child("name").getValue().toString());
                    phone.setText(ds.child("phoneNumber").getValue().toString());
                    address.setText(ds.child("address").getValue().toString());
                    bloodGroup.setText(ds.child("bloodGroup").getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private String getStringValue(EditText txt) {
        return txt.getText().toString().trim();
    }
}