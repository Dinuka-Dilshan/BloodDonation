package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

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
    ImageView img;


    Uri imageUri;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_form);

        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.txtPhone);
        address = findViewById(R.id.txtAddress);
        radioGroup = findViewById(R.id.radioCategory);
        bloodGroup = findViewById(R.id.txtBloodGroup);
        userName = findViewById(R.id.txtUserName);
        password = findViewById(R.id.txtPassword);
        confirmPassword = findViewById(R.id.txtConfirmPassword);
        signUp = findViewById(R.id.btnRegister);
        goBack = findViewById(R.id.txtGoback);
        img = findViewById(R.id.imageView2);


        DAOUser DAO = new DAOUser();


        img.setOnClickListener(v->{
            selectImage();
        });




        signUp.setOnClickListener(v -> {


            if (getStringValue(name).length() == 0) {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            } else if (phone.getText().toString().trim().length() != 10) {
                Toast.makeText(this, "Phone number is invalid", Toast.LENGTH_SHORT).show();
            } else if (getStringValue(address).length() == 0) {
                Toast.makeText(this, "Address number cannot be empty", Toast.LENGTH_SHORT).show();
            } else if (getStringValue(bloodGroup).length() == 0) {
                Toast.makeText(this, "Blood Group is Invalid", Toast.LENGTH_SHORT).show();
            } else if (getStringValue(userName).length() == 0) {
                Toast.makeText(this, "User Name cannot be empty", Toast.LENGTH_SHORT).show();
            } else if (getStringValue(password).length() == 0) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            } else if (getStringValue(confirmPassword).length() == 0) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            } else if (!getStringValue(confirmPassword).equals(getStringValue(password))) {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            }else if(imageUri == null){
                Toast.makeText(this, "Please select a photo", Toast.LENGTH_SHORT).show();
            }
            else {

                uploadImage(getStringValue(userName));
                int selectedId = radioGroup.getCheckedRadioButtonId();


                radioButton = (RadioButton) findViewById(selectedId);

                User user = new User(getStringValue(name), phone.getText().toString(), getStringValue(address), getStringValue(bloodGroup), getStringValue(userName), getStringValue(password), radioButton.getText().toString().trim());
                DAO.checkAvailableUser(getStringValue(userName)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getChildrenCount() > 0) {
                            Toast.makeText(SignUpForm.this, "Username is not available", Toast.LENGTH_SHORT).show();
                        } else {
                            DAO.register(user).addOnSuccessListener(suc -> {
                                Toast.makeText(getApplicationContext(), "Registered ! Sign In to login", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), SignIn.class));
                                finish();
                            }).addOnFailureListener(er -> {
                                Toast.makeText(SignUpForm.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(SignUpForm.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }


        });

        goBack.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SignIn.class));
        });


    }

    private void uploadImage(String username) {



        storageReference = FirebaseStorage.getInstance().getReference("images/" + username);


        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        img.setImageURI(null);
                        Toast.makeText(getApplicationContext(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        Toast.makeText(getApplicationContext(), "Failed to Upload", Toast.LENGTH_SHORT).show();


                    }
                });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){

            imageUri = data.getData();
            img.setImageURI(imageUri);


        }
    }

    private String getStringValue(EditText txt) {
        return txt.getText().toString().trim();
    }


}