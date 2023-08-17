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

public class vulnerableAdapter extends RecyclerView.Adapter<vulnerableAdapter.MyViewHolder>{
    Context context;
    ArrayList<Vulnerable> l;
    public vulnerableAdapter(Context context, ArrayList<Vulnerable> l) {
        this.context = context;
        this.l = l;
    }

    @NonNull
    @Override
    public vulnerableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.listvulnerable,parent,false);
        vulnerableAdapter.MyViewHolder viewHolder = new  vulnerableAdapter.MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull vulnerableAdapter.MyViewHolder holder, int position) {
        Vulnerable v=l.get(position);
        holder.type.setText(v.getTYPE());
        holder.no.setText(v.getNO());
        holder.loc.setText(v.getLOCATION());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView type,no,loc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.Type);
            no=itemView.findViewById(R.id.No);
            loc=itemView.findViewById(R.id.Location);
        }
    }
}
