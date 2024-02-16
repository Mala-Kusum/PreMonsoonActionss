package com.example.premonsoonaction.AdapterClasses;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premonsoonaction.Models.PmuNo;
import com.example.premonsoonaction.R;

import java.util.ArrayList;
import java.util.List;

public class LocatilnNoAdapter extends RecyclerView.Adapter<LocatilnNoAdapter.PmuNoViewHolder> {

    private Context context;
    private List<PmuNo> pmuList;

    public LocatilnNoAdapter(Context context, List<PmuNo> pmuList) {
        this.context = context;
        this.pmuList = pmuList;
    }

    @NonNull
    @Override
    public PmuNoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog5card, parent, false);
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
            pmuTextView = itemView.findViewById(R.id.location);
            noTextView = itemView.findViewById(R.id.no);
        }

        public void bind(PmuNo pmu) {
            pmuTextView.setText(pmu.getPMU());
            noTextView.setText(Integer.toString(pmu.getNO()));
        }
    }
}
