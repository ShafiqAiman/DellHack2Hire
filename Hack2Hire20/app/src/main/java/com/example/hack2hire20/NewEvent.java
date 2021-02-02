package com.example.hack2hire20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hack2hire20.Model.trainings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewEvent extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference id;

    EditText name, date, time, description, location;
    RadioButton training, socialhangout, event, others;
    RadioGroup radiogroup;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        name = (EditText)findViewById(R.id.Name);
        date = (EditText)findViewById(R.id.Date);
        time = (EditText)findViewById(R.id.Time);
        description = (EditText)findViewById(R.id.Description);
        location = (EditText)findViewById(R.id.Location);

        radiogroup = (RadioGroup)findViewById(R.id.radioGroup);
        training = (RadioButton)findViewById(R.id.Training);
        socialhangout = (RadioButton)findViewById(R.id.SocialHangout);
        event = (RadioButton)findViewById(R.id.Event);
        others = (RadioButton)findViewById(R.id.Others);

        create = (Button)findViewById(R.id.create);
        String Status = "Planned";

        database = FirebaseDatabase.getInstance();
        //id = database.getReference("Training");

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Date = date.getText().toString();
                String Time = time.getText().toString();
                String Description = description.getText().toString();
                String Location = location.getText().toString();
                String Type = "";

                if(training.isChecked()){
                    Type = "Training";
                    Toast.makeText(NewEvent.this,Type,Toast.LENGTH_SHORT).show();
                }else if(event.isChecked()){
                    Type = "Event";
                    Toast.makeText(NewEvent.this,Type,Toast.LENGTH_SHORT).show();
                }else if(socialhangout.isChecked()){
                    Type = "SocialHangout";
                    Toast.makeText(NewEvent.this,Type,Toast.LENGTH_SHORT).show();
                }else{
                    Type = "Others";
                    Toast.makeText(NewEvent.this,Type,Toast.LENGTH_SHORT).show();
                }
                id = database.getReference(Type);
                trainings data = new trainings(Date, Name, Status, Time, Description, Location);
                id.child(Name).setValue(data);

                Toast.makeText(NewEvent.this,"Event is created!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewEvent.this, categories.class);
                startActivity(intent);
            }
        });
    }
}