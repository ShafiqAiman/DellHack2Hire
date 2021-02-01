package com.example.hack2hire20.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hack2hire20.Interface.ItemClickListener;
import com.example.hack2hire20.R;

public class TrainingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView Name;
    public TextView Date;
    public TextView Time;
    public TextView Status;

    private ItemClickListener itemClickListener;

    public TrainingViewHolder(@NonNull View itemView) {
        super(itemView);

        Name = itemView.findViewById(R.id.trainingName);
        Date = itemView.findViewById(R.id.trainingDate);
        Time = itemView.findViewById(R.id.trainingTime);
        //Status = itemView.findViewById(R.id.list_item_gas);


        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
