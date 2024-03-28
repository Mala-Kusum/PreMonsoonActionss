package com.example.premonsoonaction.AdapterClasses;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.premonsoonaction.Activities.Add_RateRunning;
import com.example.premonsoonaction.Activities.Equipments;
import com.example.premonsoonaction.DatePick2;
import com.example.premonsoonaction.Models.ModelEquipment;
import com.example.premonsoonaction.Models.RateModel;
import com.example.premonsoonaction.R;

import java.util.ArrayList;
import java.util.List;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {

    private List<RateModel> rateModelList;
    private final LayoutInflater inflater;
    private Context context;

    public RateAdapter(Context context, List<RateModel> rateModelList) {
        this.rateModelList = rateModelList;
        this.inflater = LayoutInflater.from(context);
        this.context=context;
    }
    public void filterList(ArrayList<RateModel> filterlist) {
        rateModelList = filterlist;
        notifyDataSetChanged();
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
        holder.startTextView.setText(Add_RateRunning.DATE_FORMAT.format(rateModel.getStart()));
        holder.endTextView.setText(Add_RateRunning.DATE_FORMAT.format(rateModel.getEnd()));
        holder.emailTextView.setText(rateModel.getEmail());
        holder.mobileTextView.setText(rateModel.getMobile());
        holder.nameTextView.setText(rateModel.getName());
        holder.detailsTextView.setText(rateModel.getDetails());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Equipments.edit = true;
                Intent i = new Intent(context, Add_RateRunning.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rateModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public static TextView typeTextView;
        public static TextView pmisTextView;
        public static TextView addressTextView;
        public static TextView startTextView;
        public static TextView endTextView;
        public static TextView emailTextView;
        public static TextView mobileTextView;
        public static TextView nameTextView;
        public static TextView detailsTextView;
        LinearLayout card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
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
