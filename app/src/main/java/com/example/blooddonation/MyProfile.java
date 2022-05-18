package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MyProfile extends AppCompatActivity {

    Button btn;
    EditText phone;
    EditText name;
    EditText address;
    EditText district;
    EditText bloodGroup;
    StorageReference storageRef;
    ImageView img;
    TextView userNameEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        NavigationView nav = findViewById(R.id.nav_view_my_profile);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        break;

                    case R.id.nav_bloodRequest:
                        startActivity(new Intent(getApplicationContext(), BloodRequestActivity.class));
                        break;

                    case R.id.nav_logout:
                        startActivity(new Intent(getApplicationContext(), BloodInfo.class));
                        break;

                    case R.id.nav_myProfile:
                        startActivity(new Intent(getApplicationContext(), MyProfile.class));
                        break;
                    case R.id.nav_organization:
                        startActivity(new Intent(getApplicationContext(), Organizations.class));
                        break;
                }
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }


        });

        btn = findViewById(R.id.button);
        phone = findViewById(R.id.my_profile_contact_no);
        name = findViewById(R.id.my_profile_name);
        address = findViewById(R.id.my_profile_address);
        district = findViewById(R.id.my_profile_district);
        bloodGroup = findViewById(R.id.my_profile_blood_group);
        userNameEditText = findViewById(R.id.my_profile_user_name);
        img = findViewById(R.id.my_prifile_image);

//        Intent i = getIntent();
        String userName = SignIn.usernameStatic;

        storageRef = FirebaseStorage.getInstance().getReference().child("images/"+userName);

        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                img.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


        DAOUser db = new DAOUser();
        db.checkAvailableUser(userName).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    name.setText(ds.child("name").getValue().toString());
                    phone.setText(ds.child("phoneNumber").getValue().toString());
                    address.setText(ds.child("address").getValue().toString());
                    bloodGroup.setText(ds.child("bloodGroup").getValue().toString());
                    userNameEditText.setText(ds.child("userName").getValue().toString());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        btn.setOnClickListener(v->{

            DatabaseReference ref = new DAOUser().getRef();
            Query pendingTasks = ref.orderByChild("userName").equalTo((userNameEditText.getText().toString().trim()));
            pendingTasks.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot childNodes: snapshot.getChildren()) {
                        childNodes.getRef().child("name").setValue(getStringValue(name));
                        childNodes.getRef().child("phoneNumber").setValue(getStringValue(phone));
                        childNodes.getRef().child("address").setValue(getStringValue(address));
                        childNodes.getRef().child("bloodGroup").setValue(getStringValue(bloodGroup));
                    }
                    Toast.makeText(MyProfile.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });

    }

    private String getStringValue(EditText txt) {
        return txt.getText().toString().trim();
    }
}