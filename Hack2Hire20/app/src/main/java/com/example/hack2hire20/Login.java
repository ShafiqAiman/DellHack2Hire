package com.example.hack2hire20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hack2hire20.Model.User;
import com.example.hack2hire20.Model.trainings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText phonenum, password;
    Button Login, SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phonenum = (EditText)findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);

        Login = (Button)findViewById(R.id.buttonLogin);
        SignUp = (Button)findViewById(R.id.buttonSignup);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(phonenum.getText().toString()).exists()) {
                            User user = snapshot.child(phonenum.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(password.getText().toString())){
                                Toast.makeText(Login.this,"Login Successfully!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainMenu.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Login.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Login.this, "This Phone Number is not Exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login.this, "Failed to Connect!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}