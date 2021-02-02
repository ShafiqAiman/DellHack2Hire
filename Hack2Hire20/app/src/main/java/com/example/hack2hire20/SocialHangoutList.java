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

public class SocialHangoutList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference hangout;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<trainings, TrainingViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_hangout_list);

        database = FirebaseDatabase.getInstance();
        hangout = database.getReference("SocialHangout");

        recyclerView = findViewById(R.id.hangoutList);
        recyclerView.setHasFixedSize(true);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, recyclerView.VERTICAL, true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FirebaseRecyclerAdapter<trainings, TrainingViewHolder>(trainings.class, R.layout.training_item_layout, TrainingViewHolder.class, hangout) {
            @Override
            protected void populateViewHolder(TrainingViewHolder trainingViewHolder, trainings socialhangoutlist, int i) {
                trainingViewHolder.Name.setText(socialhangoutlist.getName());
                trainingViewHolder.Date.setText(socialhangoutlist.getDate());
                trainingViewHolder.Time.setText(socialhangoutlist.getTime());

                trainingViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent details = new Intent(SocialHangoutList.this, EventDetails.class);
                        details.putExtra("Name", socialhangoutlist.getName());
                        details.putExtra("Date", socialhangoutlist.getDate());
                        details.putExtra("Time", socialhangoutlist.getTime());
                        details.putExtra("Description", socialhangoutlist.getDescription());
                        details.putExtra("Location", socialhangoutlist.getLocation());
                        startActivity(details);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}