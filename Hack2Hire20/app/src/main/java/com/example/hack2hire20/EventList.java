package com.example.hack2hire20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hack2hire20.Interface.ItemClickListener;
import com.example.hack2hire20.Model.trainings;
import com.example.hack2hire20.ViewHolder.TrainingViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference event;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<trainings, TrainingViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        database = FirebaseDatabase.getInstance();
        event = database.getReference("Event");

        recyclerView = findViewById(R.id.eventList);
        recyclerView.setHasFixedSize(true);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FirebaseRecyclerAdapter<trainings, TrainingViewHolder>(trainings.class, R.layout.training_item_layout, TrainingViewHolder.class, event) {
            @Override
            protected void populateViewHolder(TrainingViewHolder trainingViewHolder, trainings eventlist, int i) {
                trainingViewHolder.Name.setText(eventlist.getName());
                trainingViewHolder.Date.setText(eventlist.getDate());
                trainingViewHolder.Time.setText(eventlist.getTime());

                trainingViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent details = new Intent(EventList.this, EventDetails.class);
                        details.putExtra("Name", eventlist.getName());
                        details.putExtra("Date", eventlist.getDate());
                        details.putExtra("Time", eventlist.getTime());
                        details.putExtra("Description", eventlist.getDescription());
                        details.putExtra("Location", eventlist.getLocation());
                        startActivity(details);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}