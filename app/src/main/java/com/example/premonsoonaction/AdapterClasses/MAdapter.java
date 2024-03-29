package com.example.premonsoonaction.AdapterClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premonsoonaction.Activities.Action;
import com.example.premonsoonaction.Activities.MainActivity;
import com.example.premonsoonaction.Models.ModelEquipment;
import com.example.premonsoonaction.R;

import java.util.ArrayList;

public class MAdapter extends RecyclerView.Adapter<MAdapter.MyViewHolder>{
    Context context;
public static ArrayList<ModelEquipment> l;

public MAdapter(Context context, ArrayList<ModelEquipment> l) {
        this.context = context;
        this.l=l;
        }
@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.materialcard,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    ModelEquipment eq = l.get(position);
    holder.text.setText(eq.getName());
    holder.text.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
            MainActivity.RO=holder.text.getText().toString();
            Intent i=new Intent(context, Action.class);
            context.startActivity(i);
            }
        });
}
@Override
public int getItemCount() {
        return l.size();
        }
public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView text;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        text=itemView.findViewById(R.id.text);
    }
    }
}
