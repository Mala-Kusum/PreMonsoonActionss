package com.example.premonsoonaction.AdapterClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premonsoonaction.R;
import com.example.premonsoonaction.Models.Vulnerable;

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
        v= LayoutInflater.from(context).inflate(R.layout.locationcard,parent,false);
        vulnerableAdapter.MyViewHolder viewHolder = new  vulnerableAdapter.MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull vulnerableAdapter.MyViewHolder holder, int position) {
        Vulnerable v=l.get(position);
        holder.type.setText(v.getTYPE());
        try {
            holder.loc.setText(v.getLOCATION());
        }
        catch(Exception e){
            Log.e("onBindViewHolder: ",e.toString() );
        }
    }

    @Override
    public int getItemCount() {
        return l.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView type,loc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.type);
            loc=itemView.findViewById(R.id.location);
        }
    }
}
