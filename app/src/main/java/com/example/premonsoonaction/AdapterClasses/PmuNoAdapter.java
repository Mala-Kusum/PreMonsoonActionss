package com.example.premonsoonaction.AdapterClasses;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premonsoonaction.Models.PmuNo;
import com.example.premonsoonaction.R;

import java.util.ArrayList;
import java.util.List;

public class PmuNoAdapter extends RecyclerView.Adapter<PmuNoAdapter.PmuNoViewHolder> {

    private Context context;
    private List<PmuNo> pmuList;

    public PmuNoAdapter(Context context,List<PmuNo> pmuList) {
        this.context = context;
        this.pmuList = pmuList;
    }

    @NonNull
    @Override
    public PmuNoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eqpmuwisecard, parent, false);
        return new PmuNoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PmuNoViewHolder holder, int position) {
        PmuNo pmu = pmuList.get(position);
        holder.bind(pmu);
        holder.datal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerView r;
                Dialog customDialog;
                customDialog = new Dialog(context);
                customDialog.setContentView(R.layout.dialog5);
                customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Button saveButton = customDialog.findViewById(R.id.Save);
                Button cancelButton = customDialog.findViewById(R.id.cancel);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle save button click
                        customDialog.dismiss();  // Close the dialog if needed
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle cancel button click
                        customDialog.dismiss();  // Close the dialog if needed
                    }
                });
                r = customDialog.findViewById(R.id.List);
                r.setLayoutManager(new LinearLayoutManager(context));
                ArrayList<PmuNo> pmuList = new ArrayList<>();
                PmuNo pmu1 = new PmuNo("Location1", 1);
                pmuList.add(pmu1);
                PmuNo pmu2 = new PmuNo("Location2", 2);
                pmuList.add(pmu2);
                PmuNo pmu3 = new PmuNo("Location3", 1);
                pmuList.add(pmu3);
                LocatilnNoAdapter ad = new LocatilnNoAdapter(context,pmuList);
                r.setAdapter(ad);
                customDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pmuList.size();
    }

    public static class PmuNoViewHolder extends RecyclerView.ViewHolder {

        private TextView pmuTextView;
        private TextView noTextView;
        private LinearLayout datal;
        public PmuNoViewHolder(@NonNull View itemView) {
            super(itemView);
            datal = itemView.findViewById(R.id.datalayout);
            pmuTextView = itemView.findViewById(R.id.pmu);
            noTextView = itemView.findViewById(R.id.no);
        }

        public void bind(PmuNo pmu) {
            pmuTextView.setText(pmu.getPMU());
            noTextView.setText(Integer.toString(pmu.getNO()));
        }
    }
}