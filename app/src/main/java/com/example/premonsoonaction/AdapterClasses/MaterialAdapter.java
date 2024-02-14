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

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyViewHolder>{
    Context context;
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
        //holder.pmu.setText(m.getPmu());
        Query q ;
        if(Action.selectedAction.equals("Rate running")){
            holder.detail.setText(" Detail :  ");
        }
        if(MainActivity.HQ){
            holder.materialcard.setOnClickListener(null);
        }
        else{
            try {
                holder.imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(holder.r.getVisibility()==View.GONE){
                            holder.r.setVisibility(View.VISIBLE);
                        }
                        else{
                            holder.r.setVisibility(View.GONE);
                        }
                        /*Dialog customDialog = new Dialog(context);
                        customDialog.setContentView(R.layout.dialog5);
                        customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);*/
                        ArrayList<PmuNo> l = new ArrayList<>();
                        PmuNo m1 = new PmuNo("PMU-Bongaigaon",1);
                        l.add(m1);
                        PmuNo m2 = new PmuNo("PMU-Dhubri",2);
                        l.add(m2);
                        PmuNo m3 = new PmuNo("PMU-Diphu",1);
                        l.add(m3);
                        ad=new EqPMUWiseAdapter(context,l);
                        //recyclerPMUwise = customDialog.findViewById(R.id.eqList);
                        //recyclerPMUwise.setHasFixedSize(true);
                       // recyclerPMUwise.setLayoutManager(new LinearLayoutManager(customDialog.getContext()));
                        holder.r.setLayoutManager(new LinearLayoutManager(context));
                    /*db = FirebaseFirestore.getInstance();
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
                    });*/
                        //recyclerPMUwise.setAdapter(ad);
                        holder.r.setAdapter(ad);
                    /*Button edit=customDialog.findViewById(R.id.edit);
                    Button delete=customDialog.findViewById(R.id.delete);
                    // Button cancel=customDialog.findViewById(R.id.cancel);
                    EditText e;
                    e=customDialog.findViewById(R.id.amount);
                    String docid = m.getPmu()+m.getName();*/

                        //customDialog.show();
                    }
                });
            }
            catch (Exception e){
                Log.e("onBindViewHolder: ", e.toString());
            }
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
        RecyclerView r;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            materialcard=itemView.findViewById(R.id.materialcard);
            name=itemView.findViewById(R.id.Name);
            no=itemView.findViewById(R.id.No);
            //pmu=itemView.findViewById(R.id.PMU);
            imageButton=itemView.findViewById(R.id.drawer);
            r=itemView.findViewById(R.id.pmuwise);
        }
    }
}
