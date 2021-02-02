package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity{
    TextView Name, Email, Phone;
    ImageView ProfilePic;
    private DatabaseReference reff;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Name = (TextView) findViewById(R.id.Pname);
        Email = (TextView) findViewById(R.id.Pemail);
        Phone = (TextView) findViewById(R.id.PphoneNo);

        reff = FirebaseDatabase.getInstance().getReference();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("Name").getValue().toString();
                String email = snapshot.child("Email").getValue().toString();
                String phone = snapshot.child("phoneNumbers").getValue().toString();
                String imageUri = snapshot.child("ProfilePic").getValue().toString();
                Name.setText(name);
                Email.setText(email);
                Phone.setText(phone);
                ProfilePic = (ImageView) findViewById(R.id.profilePic);
                Picasso.get().load(imageUri).fit().centerCrop().into(ProfilePic);
                Log.d(TAG,"Value is: " + name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
               Log.w(TAG,"Failed to read value.", error.toException());
            }
        });
    }
}