package com.example.premonsoonaction.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.premonsoonaction.AdapterClasses.MaterialAdapter;
import com.example.premonsoonaction.AdapterClasses.RateAdapter;
import com.example.premonsoonaction.Models.ModelEquipment;
import com.example.premonsoonaction.Models.RateModel;
import com.example.premonsoonaction.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Equipments extends AppCompatActivity {
    FloatingActionButton add;
    private FirebaseFirestore db;
    public static CollectionReference Ref;
    Query querya;
    RecyclerView recycler,recyclerPMUwise;
    MaterialAdapter adapt;
    RateAdapter adr;
    public static ArrayList<ModelEquipment> list,filtered;
    public static ArrayList<RateModel> lr;
    TextView t;
    EditText filter;
    public static String eqt;
    Date s,e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);
        db = FirebaseFirestore.getInstance();
        add=findViewById(R.id.add);
        filter=findViewById(R.id.filter);
        t=findViewById(R.id.No);
        list = new ArrayList<>();
        lr = new ArrayList<>();
        /*Calendar c1 = Calendar.getInstance();
        c1.set(1998,7,28);
        s = c1.getTime();
        Calendar c2 = Calendar.getInstance();
        c2.set(1998,7,28);
        e = c2.getTime();
        lr.add(new RateModel("Type1", 123456, "Address1",s,e, "email1@example.com", "123456789", "Name1", "Details1"));
        lr.add(new RateModel("Type2", 789101, "Address2",s,e, "email2@example.com", "223456789", "Name2", "Details2"));
        lr.add(new RateModel("Type3", 112131, "Address3",s,e, "email3@example.com", "323456789", "Name3", "Details3"));*/
        adapt=new MaterialAdapter(this,list);
        adr=new RateAdapter(this,lr);
        recycler = findViewById(R.id.SearchByDesignation);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        /*for(int i=0;i<list.size();i++){
            Log.e( "onCreathuyge: ",list.get(i).getName() );
        }*/
        if(Action.selectedAction.equals("Rate running")){
            try{
                recycler.setAdapter(adr);
            }
            catch(Exception e){
                Log.e("set adr: ",e.toString());
            }
        }
        else{
            recycler.setAdapter(adapt);
        }
        //recyler for PMUwise split currently only displays list as in equipment list, it has to be changed while working on backend.
        if(MainActivity.HQ){
            add.setVisibility(View.INVISIBLE);
        }
        else{
            add.setVisibility(View.VISIBLE);
        }
        switch(Action.selectedAction){
            case "Equipment":
                t.setText("No.");
                this.setTitle("Equipments");
                eqt="Equipment";
                Ref = db.collection("equipments");
                querya=Ref.orderBy("name").orderBy("pmu");
                break;
            case "Material":
                t.setText("Quantity.");
                this.setTitle("Materials");
                eqt="Material";
                Ref = db.collection("materials");
                querya=Ref.orderBy("name").orderBy("pmu");
                break;
            case "Rate running":
                //t.setText("No.");
                this.setTitle("Rate Running Contracts");
                eqt="Rate Running Contract";
                Ref = db.collection("rate running contracts");
                querya=Ref.whereEqualTo("ro",MainActivity.RO);
                break;
        }
        //r2=db.collection("pmuno");
        //queryb=r2.orderBy("pmu");
        if(eqt.equals("Rate Running Contract")){
            querya.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot dc: task.getResult()) {
                            try {
                                RateModel ob = dc.toObject(RateModel.class);
                                lr.add(ob);
                                Log.d("RateModel obj: ",ob.getPmis()+" "+ob.getAddress()+" "+ob.getStart()+" "+ob.getEnd()+" "+ob.getType()+" "+ob.getName()+" "+ob.getMobile()+" "+ob.getEmail()+" "+ob.getDetails()+" "+ob.getRo()+"\n");
                            }
                            catch(Exception e){
                                Log.e("onComplete rate running: ",e.toString());
                            }
                        }
                    }
                    else{
                        Log.d("Error rate: ",task.getException().toString());
                    }
                    try {
                        adr.notifyDataSetChanged();
                    }
                    catch(Exception e){
                        Log.e("onComplete rate running: ",e.toString());
                    }
                }
            });
        }
        else{
            querya.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot dc: task.getResult()) {
                            list.add(dc.toObject(ModelEquipment.class));
                        }
                    }
                    else{
                        Log.d("Error eq: ",task.getException().toString());
                    }
                    adapt.notifyDataSetChanged();
                }
            });
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                if(eqt.equals("Rate Running Contract")){
                    i=new Intent(Equipments.this, Add_RateRunning.class);
                }
                else{
                    i=new Intent(Equipments.this, Add_Equipment.class);
                }
                startActivity(i);
            }
        });
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                s = s.toString().toLowerCase();

                filtered = new ArrayList<>();

                for (int j = 0; j < list.size(); j++) {
                    final String text1 = list.get(j).getPmu().toLowerCase();
                    final String text2 = list.get(j).getName().toLowerCase();
                    if (text1.contains(s) || text2.contains(s)) {
                        filtered.add(list.get(j));
                    }
                    adapt.filterList(filtered);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
}