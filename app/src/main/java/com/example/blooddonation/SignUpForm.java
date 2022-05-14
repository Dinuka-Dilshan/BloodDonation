package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpForm extends AppCompatActivity {

    private EditText name;
    private EditText phone;
    private EditText address;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText bloodGroup;
    private EditText userName;
    private EditText password;
    private EditText confirmPassword;
    private Button signUp;
    private TextView goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_form);

        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtPhone);
        address = findViewById(R.id.txtAddress);
        radioGroup = findViewById( R.id.radioCategory);
        bloodGroup = findViewById(R.id.txtBloodGroup);
        userName = findViewById(R.id.txtUserName);
        password = findViewById(R.id.txtPassword);
        confirmPassword = findViewById(R.id.txtConfirmPassword);
        signUp = findViewById(R.id.btnRegister);
        goBack = findViewById(R.id.txtGoback);

        DAOUser DAO = new DAOUser();

        signUp.setOnClickListener(v->{
            User user = new User(getStringValue(name),Integer.parseInt(phone.getText().toString()),getStringValue(address),getStringValue(bloodGroup),getStringValue(userName),getStringValue(password),"user");
            DAO.register(user).addOnSuccessListener(suc->{
                Toast.makeText(getApplicationContext(),"ADDED",Toast.LENGTH_LONG).show();
            }).addOnFailureListener(er->{
                Toast.makeText(SignUpForm.this, er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

    }

    private String getStringValue(EditText txt){
        return  txt.getText().toString();
    }


}