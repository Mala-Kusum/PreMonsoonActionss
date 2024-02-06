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

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> s;

    public LocationAdapter(Context context, ArrayList<String> s) {
        this.context = context;
        this.s = s;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.locationcard,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(s.get(position)!=null){
            try{
                //Log.e("inside Location Adapter", s.get(position));
                holder.text.setText((CharSequence) s.get(position));
            }
            catch(Exception e){
                Log.e("inside location adapter error",e.toString() + "String: "+ s.get(position));
            }
        }

    }

    @Override
    public int getItemCount() {
        return s.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.loc);
        }
    }
}
