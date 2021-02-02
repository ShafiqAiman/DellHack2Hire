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

public class OthersList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference others;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<trainings, TrainingViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_list);

        database = FirebaseDatabase.getInstance();
        others = database.getReference("Others");

        recyclerView = findViewById(R.id.otherList);
        recyclerView.setHasFixedSize(true);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FirebaseRecyclerAdapter<trainings, TrainingViewHolder>(trainings.class, R.layout.training_item_layout, TrainingViewHolder.class, others) {
            @Override
            protected void populateViewHolder(TrainingViewHolder trainingViewHolder, trainings otherlist, int i) {
                trainingViewHolder.Name.setText(otherlist.getName());
                trainingViewHolder.Date.setText(otherlist.getDate());
                trainingViewHolder.Time.setText(otherlist.getTime());

                trainingViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent details = new Intent(OthersList.this, EventDetails.class);
                        details.putExtra("Name", otherlist.getName());
                        details.putExtra("Date", otherlist.getDate());
                        details.putExtra("Time", otherlist.getTime());
                        details.putExtra("Description", otherlist.getDescription());
                        details.putExtra("Location", otherlist.getLocation());
                        startActivity(details);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

    }
}