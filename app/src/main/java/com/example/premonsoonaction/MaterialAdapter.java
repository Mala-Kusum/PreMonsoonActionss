package com.example.premonsoonaction;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyViewHolder>{
    Context context;
    ArrayList<ModelEquipment> l;

    public MaterialAdapter(Context context, ArrayList<ModelEquipment> list) {
        this.context = context;
        this.l=list;
    }
    public void filterList(ArrayList<ModelEquipment> filterlist) {
        l = filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MaterialAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.materialcard,parent,false);
        MaterialAdapter.MyViewHolder viewHolder = new MaterialAdapter.MyViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MaterialAdapter.MyViewHolder holder, int position) {
        ModelEquipment m=l.get(position);
        holder.name.setText(m.getName());
        holder.no.setText(m.getNo());
        holder.pmu.setText(m.getPmu());
        Query q;
        if(MainActivity.HQ){
            holder.materialcard.setOnClickListener(null);
        }
        else{
            holder.materialcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog customDialog = new Dialog(context);
                    customDialog.setContentView(R.layout.dialog3);
                    customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    Button edit=customDialog.findViewById(R.id.edit);
                    Button delete=customDialog.findViewById(R.id.delete);
                    // Button cancel=customDialog.findViewById(R.id.cancel);
                    EditText e;
                    e=customDialog.findViewById(R.id.amount);
                    String docid=m.getPmu()+m.getName();
                    customDialog.show();
                    ModelEquipment eq=new ModelEquipment();

                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(e.getText().equals(null)){
                                Toast.makeText(context, "PLease enter a value", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                eq.setName(m.getName());
                                eq.setPmu(m.getPmu());
                                eq.setNo(e.getText().toString());
                                Equipments.Ref.document(docid).set(eq).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void v) {
                                        Log.d("TAG", "DocumentSnapshot successfully updated!");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("Error updating details",e.toString());
                                    }
                                });
                            }
                            customDialog.cancel();
                        }
                    });
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Equipments.Ref.document(docid).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("TAG", "DocumentSnapshot successfully deleted!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("TAG", "Error deleting document", e);
                                        }
                                    });
                            customDialog.cancel();
                        }
                    });
                /*cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customDialog.cancel();
                    }
                });*/
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return l.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout materialcard;
        TextView name,no,pmu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            materialcard=itemView.findViewById(R.id.materialcard);
            name=itemView.findViewById(R.id.Name);
            no=itemView.findViewById(R.id.No);
            pmu=itemView.findViewById(R.id.PMU);
        }
    }
}
