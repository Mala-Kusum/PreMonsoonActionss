package com.example.premonsoonaction.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premonsoonaction.Models.ModelEquipment;
import com.example.premonsoonaction.Models.Vulnerable;
import com.example.premonsoonaction.R;

import java.util.ArrayList;
import java.util.List;

public class AddLocAdapter extends RecyclerView.Adapter<AddLocAdapter.MyViewHolder> {
    Context context;
    ArrayList<Vulnerable> list;
    RecyclerView recyclerView;

    public AddLocAdapter(Context context, ArrayList<Vulnerable> list) {
        this.context = context;
        this.list=list;
    }
    @NonNull
    @Override
    public AddLocAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.locationcard,parent,false);
        AddLocAdapter.MyViewHolder viewHolder = new AddLocAdapter.MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddLocAdapter.MyViewHolder holder, int position) {
             Vulnerable ob = list.get(position);
             holder.type.setText(ob.getTYPE().trim());
             holder.loc.setText(ob.getLOCATION().trim());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView type,loc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            loc = itemView.findViewById(R.id.location);
        }
    }
}

