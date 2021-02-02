package com.example.createevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.createevent.Model.trainings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateEvent extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference id;

    EditText name, description, location, date, time;
    RadioButton training, socialhangout, event, others;
    RadioGroup radiogroup;
    Button create;
    //Integer declare
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        //EditText Declaration
        name = (EditText)findViewById(R.id.Name);
        date = (EditText)findViewById(R.id.Date);
        time = (EditText)findViewById(R.id.Time);
        description = (EditText)findViewById(R.id.Description);
        location = (EditText)findViewById(R.id.Location);
        //RadioButton Declaration
        radiogroup = (RadioGroup)findViewById(R.id.radioGroup);
        training = (RadioButton)findViewById(R.id.Training);
        socialhangout = (RadioButton)findViewById(R.id.SocialHangout);
        event = (RadioButton)findViewById(R.id.Event);
        others = (RadioButton)findViewById(R.id.Others);

        //Register Event Button Declaration
        create = (Button)findViewById(R.id.create);

        //Database declaration
        database = FirebaseDatabase.getInstance();
        //id = database.getReference("Others");

        //For RadioButton
        id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    i = (int)dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Ni kau boleh ignore je, tak function lagi
                Message.message(getApplicationContext(),"Error! Please select your type of event.");
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Date = date.getText().toString();
                String Time = time.getText().toString();
                String Description = description.getText().toString();
                String Location = location.getText().toString();
                //Dummy Status: only to compromised the details needed in database, Not a real data that is going to be displayed
                String Status = "Planned";
                String Type;

                //To initiate Type variable
                if(training.isChecked()){
                    Type = "Training";
                    Toast.makeText(CreateEvent.this,Type,Toast.LENGTH_SHORT).show();
                }else if(event.isChecked()){
                    Type = "Event";
                    Toast.makeText(CreateEvent.this,Type,Toast.LENGTH_SHORT).show();
                }else if(socialhangout.isChecked()){
                    Type = "SocialHangout";
                    Toast.makeText(CreateEvent.this,Type,Toast.LENGTH_SHORT).show();
                }else{
                    Type = "Others";
                    Toast.makeText(CreateEvent.this,Type,Toast.LENGTH_SHORT).show();
                }

                //To find which path categories in database
                if(Type == "Training"){
                    id = database.getReference("Training");

                }else if(Type == "Event"){
                   id = database.getReference("Events");

                }else if(Type == "SocialHangout"){
                    id = database.getReference("SocialHangout");

               }else{
                   id = database.getReference("Others");

               }

               trainings data = new trainings(Date,Name,Status,Time,Description,Location,Type);
                id.child(Name).setValue(data);


                Toast.makeText(CreateEvent.this,"Event is created!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateEvent.this, Categories.class);
                startActivity(intent);
            }
        });
    }
}