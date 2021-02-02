package com.example.hack2hire20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hack2hire20.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText regName, regEmail, regPassword, regPhone;
    Button regbtn;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.signUpName);
        regEmail = findViewById((R.id.signUpEmail));
        regPassword = findViewById(R.id.signUpPassword);
        regPhone = findViewById(R.id.signUpPhone);
        regbtn = findViewById(R.id.buttonSignup);

        regbtn.setOnClickListener(v -> {
            reference = FirebaseDatabase.getInstance().getReference("User");

            String name = regName.getText().toString();
            String email = regEmail.getText().toString();
            String password = regPassword.getText().toString();
            String phone = regPhone.getText().toString();

            User user = new User(name,email,password,phone);
            reference.child(phone).setValue((user));
            Toast.makeText(SignUp.this,"Your Account is Created Successfully!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUp.this, Login.class);
            startActivity(intent);
        });
    }
}