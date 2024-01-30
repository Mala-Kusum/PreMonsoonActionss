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

import java.util.ArrayList;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyViewHolder>{
    Context context;
    TextView detail;
    RecyclerView recyclerPMUwise;
    private FirebaseFirestore db;
    public static CollectionReference Ref;
    Query querya;
    ArrayList<ModelEquipment> list;
    EqPMUWiseAdapter ad;

    public MaterialAdapter(Context context, ArrayList<ModelEquipment> list) {
        this.context = context;
        this.list=list;
    }
    public void filterList(ArrayList<ModelEquipment> filterlist) {
        list = filterlist;
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
        ModelEquipment m=list.get(position);
        holder.name.setText(m.getName());
        holder.no.setText(m.getNo());
        holder.pmu.setText(m.getPmu());
        Query q ;
        if(Action.selectedAction.equals("Rate running")){
            holder.detail.setText(" Detail :  ");
        }
        if(MainActivity.HQ){
            holder.materialcard.setOnClickListener(null);
        }
        else{
            holder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog customDialog = new Dialog(context);
                    //customDialog.setContentView(R.layout.dialog3);
                    customDialog.setContentView(R.layout.dialog5);
                    customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    ad=new EqPMUWiseAdapter(customDialog.getContext(),list);
                    recyclerPMUwise = customDialog.findViewById(R.id.eqList);
                    recyclerPMUwise.setHasFixedSize(true);
                    recyclerPMUwise.setLayoutManager(new LinearLayoutManager(customDialog.getContext()));
                    db = FirebaseFirestore.getInstance();
                    Ref = db.collection("rate running contracts");
                    querya=Ref.orderBy("name").orderBy("pmu");
                    querya.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot dc: task.getResult()) {
                                    list.add(dc.toObject(ModelEquipment.class));
                                }
                            }
                            else{
                                Log.d("Error: ",task.getException().toString());
                            }
                            ad.notifyDataSetChanged();
                        }
                    });
                    recyclerPMUwise.setAdapter(ad);
                    /*Button edit=customDialog.findViewById(R.id.edit);
                    Button delete=customDialog.findViewById(R.id.delete);
                    // Button cancel=customDialog.findViewById(R.id.cancel);
                    EditText e;
                    e=customDialog.findViewById(R.id.amount);
                    String docid = m.getPmu()+m.getName();*/
                    customDialog.show();
                   /* ModelEquipment eq=new ModelEquipment();
                    edit.setEnabled(false);
                    e.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                            if(t.toString().trim().length()==0){
                                e.setError("the field cannot be empty");
                                edit.setEnabled(false);
                            }else if ((!t.toString().contains("0"))&&(!t.toString().contains("1"))&&(!t.toString().contains("2"))&&(!t.toString().contains("3"))&&(!t.toString().contains("4"))&&(!t.toString().contains("5"))&&(!t.toString().contains("6"))&&(!t.toString().contains("7"))&&(!t.toString().contains("8"))&&(!t.toString().contains("9"))) {
                                e.setError("This field must contain at least 1 digit");
                                edit.setEnabled(false);
                            }
                            else{
                                edit.setEnabled(true);
                            }
                        }
                        @Override
                        public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                            if(t.toString().trim().length()==0){
                                e.setError("the field cannot be empty");
                                edit.setEnabled(false);
                            }else if ((!t.toString().contains("0"))&&(!t.toString().contains("1"))&&(!t.toString().contains("2"))&&(!t.toString().contains("3"))&&(!t.toString().contains("4"))&&(!t.toString().contains("5"))&&(!t.toString().contains("6"))&&(!t.toString().contains("7"))&&(!t.toString().contains("8"))&&(!t.toString().contains("9"))) {
                                e.setError("This field must contain at least 1 digit");
                                edit.setEnabled(false);
                            }
                            else{
                                edit.setEnabled(true);
                            }
                        }
                        @Override
                        public void afterTextChanged(Editable editable) {
                            if(e.getText().toString().trim().length()==0){
                                e.setError("the field cannot be empty");
                                edit.setEnabled(false);
                            }else if ((!e.getText().toString().contains("0"))&&(!e.getText().toString().contains("1"))&&(!e.getText().toString().contains("2"))&&(!e.getText().toString().contains("3"))&&(!e.getText().toString().contains("4"))&&(!e.getText().toString().contains("5"))&&(!e.getText().toString().contains("6"))&&(!e.getText().toString().contains("7"))&&(!e.getText().toString().contains("8"))&&(!e.getText().toString().contains("9"))) {
                                e.setError("This field must contain at least 1 digit");
                                edit.setEnabled(false);
                            }
                            else{
                                edit.setEnabled(true);
                            }
                        }
                    });

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
                cancel.setOnClickListener(new View.OnClickListener() {
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
        return list.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout materialcard;
        TextView name,no,pmu,detail;
        ImageView imageButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            materialcard=itemView.findViewById(R.id.materialcard);
            name=itemView.findViewById(R.id.Name);
            no=itemView.findViewById(R.id.No);
            pmu=itemView.findViewById(R.id.PMU);
            imageButton=itemView.findViewById(R.id.drawer);
        }
    }
}
