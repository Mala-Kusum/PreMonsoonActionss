package com.example.premonsoonaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {
    Context context;
    ArrayList<Date> l;

    public ReportAdapter(Context context, ArrayList<Date> l) {
        this.context = context;
        this.l = l;
    }
    public void filterList(ArrayList<Date> filterlist) {
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
        Date m=l.get(position);
        holder.date.setText(m.toString());
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
