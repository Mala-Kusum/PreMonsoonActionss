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

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyViewHolder>{
    Context context;
    ArrayList<ModelEquipment> l;

    public MaterialAdapter(Context context, ArrayList<ModelEquipment> list) {
        this.context = context;
        this.l=list;
    }
    public void filterList(ArrayList<ModelEquipment> filterlist) {
        l = filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MaterialAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.materialcard,parent,false);
        MaterialAdapter.MyViewHolder viewHolder = new MaterialAdapter.MyViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MaterialAdapter.MyViewHolder holder, int position) {
        ModelEquipment m=l.get(position);
        holder.name.setText(m.name);
        holder.no.setText(m.no);
        holder.pmu.setText(m.pmu);
    }
    @Override
    public int getItemCount() {
        return l.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,no,pmu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Name);
            no=itemView.findViewById(R.id.No);
            pmu=itemView.findViewById(R.id.PMU);
        }
    }
}
