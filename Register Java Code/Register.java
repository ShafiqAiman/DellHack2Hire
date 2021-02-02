package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity{

    EditText regName, regEmail, regPassword, regPhone;
    Button regbtn;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regName = findViewById(R.id.signUpName);
        regEmail = findViewById((R.id.signUpEmail));
        regPassword = findViewById(R.id.signUpPassword);
        regPhone = findViewById(R.id.signUpPhone);
        regbtn = findViewById(R.id.registerBtn);

        regbtn.setOnClickListener(v -> {
            reference = FirebaseDatabase.getInstance().getReference("users");

            String name = regName.getText().toString();
            String email = regEmail.getText().toString();
            String password = regPassword.getText().toString();
            String phone = regPhone.getText().toString();

            UserStorage userStorage = new UserStorage(name,email,password,phone);
            reference.child(phone).setValue((userStorage));

        });
    }
}