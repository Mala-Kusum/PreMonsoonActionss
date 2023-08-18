package com.example.premonsoonaction;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> s;

    public LocationAdapter(Context context, ArrayList<String> s) {
        this.context = context;
        this.s = s;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.listlocation,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text.setText(s.get(position));
    }

    @Override
    public int getItemCount() {
        return s.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.loc);
        }
    }
}
