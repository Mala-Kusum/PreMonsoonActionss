package com.example.premonsoonaction.AdapterClasses;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.premonsoonaction.Activities.Action;
import com.example.premonsoonaction.Activities.MainActivity;
import com.example.premonsoonaction.Models.ModelEquipment;
import com.example.premonsoonaction.Models.PmuNo;
import com.example.premonsoonaction.Models.PmuNoAdapter;
import com.example.premonsoonaction.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyViewHolder>{
    Context context;
    private FirebaseFirestore db;
    public static CollectionReference Ref;
    Query querya;
    ArrayList<ModelEquipment> list;


    public static CollectionReference r2;
    Query queryb;
    private RecyclerView recyclerView;
    private PmuNoAdapter pmuAdapter;
    private List<PmuNo> pmuList;


    public void filterList(ArrayList<ModelEquipment> filterlist) {
        list = filterlist;
        notifyDataSetChanged();
    }
    public MaterialAdapter(Context context, ArrayList<ModelEquipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MaterialAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.materialcard, parent, false);
        return new MaterialAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialAdapter.MyViewHolder holder, int position) {
        ModelEquipment m = list.get(position);
        holder.name.setText(m.getName());
        holder.no.setText(m.getNo());

        ArrayList<PmuNo> pmuList = new ArrayList<>();
        PmuNo pmu1 = new PmuNo("PMU-Bongaigaon", 1);
        pmuList.add(pmu1);
        PmuNo pmu2 = new PmuNo("PMU-Dhubri", 2);
        pmuList.add(pmu2);
        PmuNo pmu3 = new PmuNo("PMU-Diphu", 1);
        pmuList.add(pmu3);

        // Use a separate instance of PmuNoAdapter for each item
        PmuNoAdapter ad = new PmuNoAdapter(pmuList);
        holder.r.setAdapter(ad);

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
        return list.size();
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout materialcard;
        TextView name,no,pmu,detail;
        ImageView imageButton;
        RecyclerView r;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            materialcard=itemView.findViewById(R.id.materialcard);
            name=itemView.findViewById(R.id.Name);
            no=itemView.findViewById(R.id.No);
            //pmu=itemView.findViewById(R.id.PMU);
            imageButton=itemView.findViewById(R.id.drawer);
            r=itemView.findViewById(R.id.pmuwise);
            r.setLayoutManager(new LinearLayoutManager(itemView.getContext(), RecyclerView.HORIZONTAL, false));
        }
    }
}
