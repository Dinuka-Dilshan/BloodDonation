package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Year;

public class AddBloodRequest extends AppCompatActivity {

    EditText name;
    EditText phone;
    EditText description;
    EditText town;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blood_request);


        name = findViewById(R.id.blood_name);
        description = findViewById(R.id.blood_description);
        phone  = findViewById(R.id.blood_phone);
        town = findViewById(R.id.blood_town);
        btn = findViewById(R.id.btnAddBloodReq);

        btn.setOnClickListener(v->{
            if(getStringValue(description).length() == 0){
                Toast.makeText(this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
            }else if(getStringValue(name).length() == 0){
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            }else if(getStringValue(phone).length() == 0){
                Toast.makeText(this, "Phone number cannot be empty", Toast.LENGTH_SHORT).show();
            }else if(getStringValue(town).length() == 0){
                Toast.makeText(this, "Town cannot be empty", Toast.LENGTH_SHORT).show();
            }else {

                BloodRequest req = new BloodRequest(getStringValue(description), getStringValue(name), getStringValue(phone), getStringValue(town));

                FirebaseDatabase db = FirebaseDatabase.getInstance("https://blooddonation-dfcf9-default-rtdb.asia-southeast1.firebasedatabase.app");
                DatabaseReference databaseReference = db.getReference("BloodRequest");

                databaseReference.push().setValue(req).addOnSuccessListener(suc->{
                    Toast.makeText(this, "Request Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), BloodRequestActivity.class));
                }).addOnFailureListener(vv->{
                    Toast.makeText(this, "Failed To add !", Toast.LENGTH_SHORT).show();
                });



            }
        });
    }




    private String getStringValue(EditText txt) {
        return txt.getText().toString().trim();
    }
}