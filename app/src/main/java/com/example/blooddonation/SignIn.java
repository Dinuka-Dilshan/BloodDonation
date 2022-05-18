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

public class SignIn extends AppCompatActivity {

    EditText password;
    EditText userName;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        password = findViewById(R.id.signin_password);
        userName = findViewById(R.id.signin_username);
        btn = findViewById(R.id.btnSignIn);
        DAOUser DAO = new DAOUser();

        btn.setOnClickListener(v->{
            DAO.checkAvailableUser(getStringValue(userName)).addListenerForSingleValueEvent(new ValueEventListener() {

                boolean isValid = false;

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot ds : snapshot.getChildren()){
                        if(ds.child("password").getValue().toString().equals(getStringValue(password)) && ds.child("userName").getValue().toString().equals(getStringValue(userName))){
                            isValid = true;
                            break;
                        }
                    }

                    if(isValid){
                        Intent i = new Intent(getApplicationContext(),Home.class);
                        i.putExtra("userName",getStringValue(userName));
                        startActivity(i);
                    }else {
                        Toast.makeText(SignIn.this, "Username or password incorrect !", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }


    private String getStringValue(EditText txt) {
        return txt.getText().toString().trim();
    }
}