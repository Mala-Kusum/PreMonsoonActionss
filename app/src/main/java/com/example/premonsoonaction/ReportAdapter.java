package com.example.premonsoonaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelReport> l;

    public ReportAdapter(Context context, ArrayList<ModelReport> l) {
        this.context = context;
        this.l = l;
    }
    public void filterList(ArrayList<ModelReport> filterlist) {
        l = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.reportcard,parent,false);
        ReportAdapter.MyViewHolder viewHolder = new ReportAdapter.MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.MyViewHolder holder, int position) {
        ModelReport m=l.get(position);
        holder.date.setText(m.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return l.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.Date);
        }
    }
}
