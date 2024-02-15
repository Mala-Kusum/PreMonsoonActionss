package com.example.premonsoonaction.Models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premonsoonaction.R;

import java.util.List;

public class PmuNoAdapter extends RecyclerView.Adapter<PmuNoAdapter.PmuNoViewHolder> {

    private List<PmuNo> pmuList;

    public PmuNoAdapter(List<PmuNo> pmuList) {
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
    }

    @Override
    public int getItemCount() {
        return pmuList.size();
    }

    public static class PmuNoViewHolder extends RecyclerView.ViewHolder {

        private TextView pmuTextView;
        private TextView noTextView;

        public PmuNoViewHolder(@NonNull View itemView) {
            super(itemView);
            pmuTextView = itemView.findViewById(R.id.pmu);
            noTextView = itemView.findViewById(R.id.no);
        }

        public void bind(PmuNo pmu) {
            pmuTextView.setText("PMU: " + pmu.getPMU());
            noTextView.setText("NO: " + pmu.getNO());
        }
    }
}
