package com.example.hack2hire20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hack2hire20.Model.trainings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventDetails extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference Details;
    String name = "";
    String date = "";
    String time = "";
    String description = "";
    String location = "";

    private TextView trainingname, trainingdate, trainingtime, trainingdescription, traininglocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        database = FirebaseDatabase.getInstance();
        name = getIntent().getStringExtra("Name");
        date = getIntent().getStringExtra("Date");
        time = getIntent().getStringExtra("Time");
        description = getIntent().getStringExtra("Description");
        location = getIntent().getStringExtra("Location");

        trainingname = (TextView)findViewById(R.id.Name);
        trainingdate = (TextView)findViewById(R.id.dateData);
        trainingtime = (TextView)findViewById(R.id.timeData);
        trainingdescription = (TextView)findViewById(R.id.description);
        traininglocation = (TextView)findViewById(R.id.locationData);

        trainingname.setText(name);
        trainingdate.setText(date);
        trainingtime.setText(time);
        trainingdescription.setText(description);
        traininglocation.setText(location);
    }
}