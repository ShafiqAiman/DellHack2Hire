package com.example.hack2hire20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hack2hire20.Model.trainings;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEvent extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference id;

    EditText name, date, time, description, location;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        name = (EditText)findViewById(R.id.nameofevent);
        date = (EditText)findViewById(R.id.dateofevent);
        time = (EditText)findViewById(R.id.timeofevent);
        description = (EditText)findViewById(R.id.desriptionofevent);
        location = (EditText)findViewById(R.id.locationofevent);

        create = (Button)findViewById(R.id.createButton);



        String Status = "Planned";

        database = FirebaseDatabase.getInstance();
        id = database.getReference("Training");

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Date = date.getText().toString();
                String Time = time.getText().toString();
                String Description = description.getText().toString();
                String Location = location.getText().toString();

                trainings data = new trainings(Date, Name, Status, Time, Description, Location);
                id.child(Name).setValue(data);

                Toast.makeText(CreateEvent.this,"Event is created!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateEvent.this, categories.class);
                startActivity(intent);
            }
        });
    }
}