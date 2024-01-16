package com.example.premonsoonaction;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {
    Context context;
    ArrayList<reportGetModel> l;

    public ReportAdapter(Context context, ArrayList<reportGetModel> l) {
        this.context = context;
        this.l = l;
    }
    public void filterList(ArrayList<reportGetModel> filterlist) {
        l = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.reportcard,parent,false);
        ReportAdapter.MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.MyViewHolder holder, int position) {
        //System.out.println("ob "+ ShowReport.ob.getRO()+" "+ShowReport.ob.getinst1()+ " "+ ShowReport.ob.getDate().toString());
        Date m=l.get(position).getDate();

        holder.Date.setText(m.toString());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowReport.ob=l.get(holder.getAdapterPosition());
                ShowReport.docid=l.get(holder.getAdapterPosition()).getDocid();
                Intent i = new Intent(context,ShowReport.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return l.size();
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
