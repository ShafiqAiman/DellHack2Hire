package com.example.hack2hire20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class categories extends AppCompatActivity {

    private CardView training, events, socialhangout, others;
    private Button addEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        training = (CardView) findViewById(R.id.profile);
        events = (CardView) findViewById(R.id.category);
        socialhangout = (CardView) findViewById(R.id.history);
        others = (CardView) findViewById(R.id.logout);
        addEvent = (Button)findViewById(R.id.createEventButton);

        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categories.this, TrainingList.class);
                startActivity(intent);
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categories.this, EventList.class);
                startActivity(intent);
            }
        });

        socialhangout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categories.this, SocialHangoutList.class);
                startActivity(intent);
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categories.this, OthersList.class);
                startActivity(intent);
            }
        });

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categories.this, NewEvent.class);
                startActivity(intent);
            }
        });
    }
}