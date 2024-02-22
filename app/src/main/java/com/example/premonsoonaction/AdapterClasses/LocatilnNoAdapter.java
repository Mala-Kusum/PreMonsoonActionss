package com.example.premonsoonaction.AdapterClasses;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    public LocatilnNoAdapter(Context context, List<PmuNo> pmuList) {
        this.context = context;
        this.pmuList = pmuList;
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
        }
        public void bind(PmuNo pmu) {
            pmuTextView.setText(pmu.getPMU());
            noTextView.setText((CharSequence) Integer.toString(pmu.getNO()));
        }
    }
}
