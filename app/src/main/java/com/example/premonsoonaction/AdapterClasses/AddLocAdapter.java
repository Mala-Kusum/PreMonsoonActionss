package com.example.premonsoonaction.AdapterClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premonsoonaction.Models.Vulnerable;
import com.example.premonsoonaction.R;

import java.util.ArrayList;

public class AddLocAdapter extends RecyclerView.Adapter<AddLocAdapter.MyViewHolder> {
    Context context;
    ArrayList<Vulnerable> list;

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
             if(ob!=null){
                 if(ob.getTYPE()!=null){
                     Log.e("inside getType()", ob.getTYPE());
                     holder.type.setText(ob.getTYPE().trim());
                 }
                 if(ob.getLOCATION()!=null){
                     Log.e("inside getLocation()", ob.getLOCATION());
                     holder.loc.setText(ob.getLOCATION().trim());
                 }
             }
        Log.e("object ","000000000000  "+ob+" "+ob.getTYPE()+" "+ob.getLOCATION()+" 000000000000000000\n");
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

