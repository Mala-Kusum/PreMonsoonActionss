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
import com.example.premonsoonaction.R;

import java.util.ArrayList;

public class EqPMUWiseAdapter extends RecyclerView.Adapter<EqPMUWiseAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelEquipment> l;
    TextView detail;
    public EqPMUWiseAdapter(Context context, ArrayList<ModelEquipment> list) {
        this.context = context;
        this.l=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.eqpmuwisecard,parent,false);
        EqPMUWiseAdapter.MyViewHolder viewHolder = new EqPMUWiseAdapter.MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelEquipment m=l.get(position);
        holder.no.setText(m.getNo());
        holder.pmu.setText(m.getPmu());
    }

    @Override
    public int getItemCount() {
        return l.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout materialcard;
        TextView no,pmu;
        ImageView imageButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            materialcard=itemView.findViewById(R.id.eqpmuwisecard);
            no=itemView.findViewById(R.id.no);
            pmu=itemView.findViewById(R.id.pmu);
        }
    }
}
