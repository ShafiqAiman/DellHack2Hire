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

public class TrainingList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference training;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<trainings, TrainingViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_list);

        database = FirebaseDatabase.getInstance();
        training = database.getReference("Training");

        recyclerView = findViewById(R.id.trainingList);
        recyclerView.setHasFixedSize(true);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FirebaseRecyclerAdapter<trainings, TrainingViewHolder>(trainings.class, R.layout.training_item_layout, TrainingViewHolder.class, training) {
            @Override
            protected void populateViewHolder(TrainingViewHolder trainingViewHolder, trainings traininglist, int i) {
                trainingViewHolder.Name.setText(traininglist.getName());
                trainingViewHolder.Date.setText(traininglist.getDate());
                trainingViewHolder.Time.setText(traininglist.getTime());

                trainingViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent details = new Intent(TrainingList.this, EventDetails.class);
                        details.putExtra("Name", traininglist.getName());
                        details.putExtra("Date", traininglist.getDate());
                        details.putExtra("Time", traininglist.getTime());
                        startActivity(details);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}