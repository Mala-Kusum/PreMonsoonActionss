package com.example.premonsoonaction;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.directory.R;

public class placard_adapter extends RecyclerView.Adapter<placard_adapter.MyViewHolder>{
   Context context;
   public static String[] s;

    public placard_adapter(Context context, String[] s) {
        this.context = context;
        this.s=s;
        }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.placard,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.text.setText(s[position]);
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.RO=holder.text.getText().toString();
                Intent i=new Intent(context,Action.class);
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
            return s.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.text);
        }
    }
}
