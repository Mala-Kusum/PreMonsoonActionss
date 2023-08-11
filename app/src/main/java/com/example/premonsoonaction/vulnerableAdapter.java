package com.example.premonsoonaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class vulnerableAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder>{
    Context context;
    ArrayList<Vulnerable> l;
    public vulnerableAdapter(Context context, ArrayList<Vulnerable> l) {
        this.context = context;
        this.l = l;
    }

    @NonNull
    @Override
    public ReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.listvulnerable,parent,false);
        vulnerableAdapter.MyViewHolder viewHolder = new  vulnerableAdapter.MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Date;
        LinearLayout card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card=itemView.findViewById(R.id.cardReport);
            Date = itemView.findViewById(R.id.Date);
        }
    }
}
