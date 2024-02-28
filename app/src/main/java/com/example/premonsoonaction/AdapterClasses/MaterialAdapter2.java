package com.example.premonsoonaction.AdapterClasses;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premonsoonaction.Activities.AddInsufficiency;
import com.example.premonsoonaction.Models.ModelEquipment;
import com.example.premonsoonaction.Models.PmuNo;
import com.example.premonsoonaction.R;

import java.util.ArrayList;
import java.util.List;

public class MaterialAdapter2 extends RecyclerView.Adapter<MaterialAdapter2.MyViewHolder>{
    Context context;
    public List<PmuNo> eqList;
    private RecyclerView recyclerView;
    private PmuNoAdapter pmuAdapter;
    private List<PmuNo> pmuList;

    public MaterialAdapter2(Context context, List<PmuNo> eqList) {
        this.context = context;
        this.eqList = eqList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.materialcard, parent, false);
        return new MaterialAdapter2.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PmuNo m = eqList.get(position);
        holder.name.setText(m.getPMU());
        try{
            holder.no.setText(m.getNO());
        }
        catch(Exception e){
            Log.e("onBindViewHolder no: ", e.toString());
        }
        holder.insuf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddInsufficiency.class);
                context.startActivity(i);
            }
        });
        ArrayList<PmuNo> pmuList = new ArrayList<>();
        PmuNo pmu1 = new PmuNo("PMU-Bongaigaon", 1);
        pmuList.add(pmu1);
        PmuNo pmu2 = new PmuNo("PMU-Dhubri", 2);
        pmuList.add(pmu2);
        PmuNo pmu3 = new PmuNo("PMU-Diphu", 1);
        pmuList.add(pmu3);

        // Use a separate instance of PmuNoAdapter for each item
        PmuNoAdapter ad = new PmuNoAdapter(context,pmuList);

        try {
            holder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.r.setLayoutManager(new LinearLayoutManager(context));
                    holder.r.setAdapter(ad);
                    if (holder.r.getVisibility() == View.GONE) {
                        holder.r.setVisibility(View.VISIBLE);
                        holder.materialcard.setElevation(0);
                    } else {
                        holder.r.setVisibility(View.GONE);
                        holder.materialcard.setElevation(5);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("onBindViewHolder: ", e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return eqList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout materialcard;
        TextView name,no;
        ImageView imageButton;
        ImageView insuf;
        RecyclerView r;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            materialcard=itemView.findViewById(R.id.materialcard);
            name=itemView.findViewById(R.id.Name);
            no=itemView.findViewById(R.id.No);
            insuf=itemView.findViewById(R.id.insuf);
            imageButton=itemView.findViewById(R.id.drawer);
            r=itemView.findViewById(R.id.pmuwise);
            r.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false));
        }
    }
}
