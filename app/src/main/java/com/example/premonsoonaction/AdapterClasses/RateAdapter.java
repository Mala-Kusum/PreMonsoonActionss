package com.example.premonsoonaction.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premonsoonaction.Models.RateModel;
import com.example.premonsoonaction.R;

import java.util.List;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {

    private final List<RateModel> rateModelList;
    private final LayoutInflater inflater;

    public RateAdapter(Context context, List<RateModel> rateModelList) {
        this.rateModelList = rateModelList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ratecard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RateModel rateModel = rateModelList.get(position);

        holder.typeTextView.setText(rateModel.getType());
        holder.pmisTextView.setText(Integer.toString(rateModel.getPmis()));
        holder.addressTextView.setText(rateModel.getAddress());
        holder.startTextView.setText(rateModel.getStart().toString());
        holder.endTextView.setText(rateModel.getEnd().toString());
        holder.emailTextView.setText(rateModel.getEmail());
        holder.mobileTextView.setText(rateModel.getMobile());
        holder.nameTextView.setText(rateModel.getName());
        holder.detailsTextView.setText(rateModel.getDetails());
    }

    @Override
    public int getItemCount() {
        return rateModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView typeTextView;
        TextView pmisTextView;
        TextView addressTextView;
        TextView startTextView;
        TextView endTextView;
        TextView emailTextView;
        TextView mobileTextView;
        TextView nameTextView;
        TextView detailsTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typeTextView = itemView.findViewById(R.id.type);
            pmisTextView = itemView.findViewById(R.id.pmis);
            addressTextView = itemView.findViewById(R.id.address);
            startTextView = itemView.findViewById(R.id.start);
            endTextView = itemView.findViewById(R.id.end);
            emailTextView = itemView.findViewById(R.id.email);
            mobileTextView = itemView.findViewById(R.id.mobile);
            nameTextView = itemView.findViewById(R.id.contractorname);
            detailsTextView = itemView.findViewById(R.id.details);
        }
    }
}
