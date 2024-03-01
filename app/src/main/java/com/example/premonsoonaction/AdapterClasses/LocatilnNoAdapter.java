package com.example.premonsoonaction.AdapterClasses;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.premonsoonaction.Activities.AddInsufficiency;
import com.example.premonsoonaction.Activities.MainActivity;
import com.example.premonsoonaction.Models.PmuNo;
import com.example.premonsoonaction.R;
import java.util.List;

public class LocatilnNoAdapter extends RecyclerView.Adapter<LocatilnNoAdapter.PmuNoViewHolder> {

    private Context context;
    private List<PmuNo> pmuList;
    String eq;

    public LocatilnNoAdapter(Context context, List<PmuNo> pmuList,String eq) {
        this.context = context;
        this.pmuList = pmuList;
        this.eq = eq;
    }

    @NonNull
    @Override
    public PmuNoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlyout, parent, false);
        return new PmuNoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PmuNoViewHolder holder, int position) {
        try{
            PmuNo pmu = pmuList.get(position);
            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(pmu.getNO()>0){
                        pmu.setNO(pmu.getNO()-1);
                        try{
                            holder.noTextView.setText((CharSequence) Integer.toString(pmu.getNO()));
                            PmuNoAdapter.l
                        }
                        catch (Exception e){
                            Log.e("onClick minus: ",e.toString());
                        }
                    }
                    else{
                        Toast.makeText(context, "item cannot be reduced further", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pmu.setNO(pmu.getNO()+1);
                    holder.noTextView.setText((CharSequence) Integer.toString(pmu.getNO()));
                }
            });
            holder.insuf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.location=pmu.getPMU();
                    Intent i = new Intent(context, AddInsufficiency.class);
                    context.startActivity(i);
                }
            });
            holder.bind(pmu);
        }
        catch(Exception e){
            Log.e("onBindViewHolderloc: ",e.toString()+"\n"+ holder.insuf);
        }
    }

    @Override
    public int getItemCount() {
        return pmuList.size();
    }

    public static class PmuNoViewHolder extends RecyclerView.ViewHolder {
        private TextView pmuTextView;
        private TextView noTextView;
        private View minus;
        private ImageView plus;
        ImageView insuf;
        public PmuNoViewHolder(@NonNull View itemView) {
            super(itemView);
            try{
                insuf  = itemView.findViewById(R.id.insufficientIcon);
            }
            catch(Exception e){
                Log.e("PmuNoViewHolderloc: ", e.toString());
            }
            pmuTextView = itemView.findViewById(R.id.location);
            noTextView = itemView.findViewById(R.id.no);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
        }
        public void bind(PmuNo pmu) {
            pmuTextView.setText(pmu.getPMU());
            noTextView.setText((CharSequence) Integer.toString(pmu.getNO()));
        }
    }
}
