package com.example.hack2hire20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.hack2hire20.Model.historyevents;
import com.example.hack2hire20.ViewHolder.eventViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class History extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference history;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<com.example.hack2hire20.Model.historyevents, eventViewHolder>adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        database = FirebaseDatabase.getInstance();
        history = database.getReference("Events");

        recyclerView = findViewById(R.id.eventlist);
        recyclerView.setHasFixedSize(true);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        adapter = new FirebaseRecyclerAdapter<historyevents, eventViewHolder>(historyevents.class,R.layout.event_item_layout, eventViewHolder.class, history) {
            @Override
            protected void populateViewHolder(eventViewHolder viewHolder, historyevents model, int position) {
                viewHolder.Name.setText(model.getName());
                viewHolder.Date.setText(model.getDate());
                viewHolder.Time.setText(model.getTime());
                //viewHolder.Status.setText(model.getDate());


            }
        };
        recyclerView.setAdapter(adapter);

    }
}