package com.example.premonsoonaction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowReport extends AppCompatActivity {

    public static String docid;
    CollectionReference c1,c2,c3,c4;
    ArrayList<Vulnerable> l1,l2;
    ArrayList<String> s3,s4;
    public static reportGetModel ob;
    // batch2;
    Vulnerable ob1,ob2;
    public static Boolean INST1,INST2,INST3,INST4,INST5,INST6,INST7,INST8,INST9,INST10,INST11;
    TextView inst1,inst2,inst3,inst4,inst5,inst6,inst7,inst8,inst9,inst10,inst11;
    vulnerableAdapter ad1,ad2;
    LocationAdapter ad3,ad4;
    RecyclerView r1,r2,r3,r4;
    Query q1,q2,q3,q4;
    int i,j;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        i=0;
        j=0;
        ob1=new Vulnerable();
        ob2=new Vulnerable();
        c1 = db.collection("checklist").document(docid).collection("Vulnerable");
        c2 = db.collection("checklist").document(docid).collection("Critical");
        c3 = db.collection("checklist").document(docid).collection("Inspected");
        c4 = db.collection("checklist").document(docid).collection("Warning");
        INST1=ob.getinst1();
        INST2=ob.getinst2();
        INST3=ob.getinst3();
        INST4=ob.getinst4();
        INST5=ob.getinst5();
        INST6=ob.getinst6();
        INST7=ob.getinst7();
        INST8=ob.getinst8();
        INST9=ob.getinst9();
        INST10=ob.getinst10();
        INST11=ob.getinst11();

        inst1=findViewById(R.id.inst1);
        inst2=findViewById(R.id.inst2);
        inst3=findViewById(R.id.inst3);
        inst4=findViewById(R.id.inst4);
        inst5=findViewById(R.id.inst5);
        inst6=findViewById(R.id.inst6);
        inst7=findViewById(R.id.inst7);
        inst8=findViewById(R.id.inst8);
        inst9=findViewById(R.id.inst9);
        inst10=findViewById(R.id.inst10);
        inst11=findViewById(R.id.inst11);

        l1= new ArrayList<>();
        l2=new ArrayList<>();
        s3=new ArrayList<>();
        s4=new ArrayList<>();

        r1=findViewById(R.id.Vulnerable);
        r2=findViewById(R.id.Critical);
        r3=findViewById(R.id.inspected);
        r4=findViewById(R.id.warning);

        if(INST1==true){
            inst1.setText("Done");
            inst1.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst1.setText("Not Done");
            inst1.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        if(INST2==true){
            inst2.setText("Done");
            inst2.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst2.setText("Not Done");
            inst2.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        if(INST3==true){
            inst3.setText("Done");
            inst3.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst3.setText("Not Done");
            inst3.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        if(INST4==true){
            inst4.setText("Done");
            inst4.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst4.setText("Not Done");
            inst4.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        if(INST5==true){
            inst5.setText("Done");
            inst5.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst5.setText("Not Done");
            inst5.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        if(INST6==true){
            inst6.setText("Done");
            inst6.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst6.setText("Not Done");
            inst6.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        if(INST7==true){
            inst7.setText("Done");
            inst7.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst7.setText("Not Done");
            inst7.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        if(INST8==true){
            inst8.setText("Done");
            inst8.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst8.setText("Not Done");
            inst8.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        if(INST9==true){
            inst9.setText("Done");
            inst9.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst9.setText("Not Done");
            inst9.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        if(INST10==true){
            inst10.setText("Done");
            inst10.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst10.setText("Not Done");
            inst10.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        if(INST11==true){
            inst11.setText("Done");
            inst11.setTextColor(getResources().getColor(R.color.green,getTheme()));
        }
        else{
            inst11.setText("Not Done");
            inst11.setTextColor(getResources().getColor(R.color.red,getTheme()));
        }
        ad1=new vulnerableAdapter(ShowReport.this,l1);
        ad2=new vulnerableAdapter(ShowReport.this,l2);
        try{
            ad3=new LocationAdapter(ShowReport.this,s3);
            ad4=new LocationAdapter(ShowReport.this,s4);
        }
        catch(Exception e){
            Log.e("adapter ",e.toString());
        }
        //r1.setHasFixedSize(true);
        System.out.println("xxxxxxxxxxrecyclexxxxxxxx");
        r1.setLayoutManager(new LinearLayoutManager(this));
        r1.setAdapter(ad1);
        r2.setLayoutManager(new LinearLayoutManager(this));
        r2.setAdapter(ad2);
        r3.setLayoutManager(new LinearLayoutManager(this));
        r3.setAdapter(ad3);
        try{
            r4.setLayoutManager(new LinearLayoutManager(this));
            r4.setAdapter(ad4);
        }
        catch(Exception e){
            Log.e("r4 recycler ",e.toString());
        }
        q1=c1.orderBy("location").orderBy("type");
        q1.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore Error", error.getMessage());
                    return;
                }
                for (DocumentChange dc1 : value.getDocumentChanges()) {
                    if (dc1.getType() == DocumentChange.Type.ADDED) {
                        System.out.println("vulnerable doc "+ dc1.getDocument().toObject(Vulnerable.class).getLOCATION()+dc1.getDocument().toObject(Vulnerable.class).getNO()+dc1.getDocument().toObject(Vulnerable.class).getTYPE());

                        ob1.setTYPE(dc1.getDocument().getString("type"));
                        try {
                            ob1.setNO((long) dc1.getDocument().get("no"));
                            ob1.setLOCATION(dc1.getDocument().getString("location"));
                        }
                        catch(Exception e){
                            System.out.println("error no "+e.toString());
                        }
                        System.out.println("ba " +ob1.getLOCATION()+" "+ob1.getTYPE()+" "+ob1.getNO());
                       // l1.add(dc.getDocument().toObject(Vulnerable.class));
                        l1.add(ob1);
                        ad1.notifyDataSetChanged();
                    }
                    try {
                        ad1.notifyDataSetChanged();
                    }
                    catch(Exception e){
                        Log.e("error notify", e.toString());
                    }

                }
            }
        });
        q2=c2.orderBy("location").orderBy("type");
        q2.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Log.e("Firestore Error", error.getMessage());
                    return;
                }
                else{
                    for (DocumentChange dc2:value.getDocumentChanges()) {
                        ob2.setTYPE(dc2.getDocument().getString("type"));
                        ob2.setNO((long) dc2.getDocument().get("no"));
                        ob2.setLOCATION(dc2.getDocument().getString("location"));
                        l2.add(ob2);
                    }
                    ad2.notifyDataSetChanged();
                }
            }
        });

        q3=c3.orderBy("location");
        q3.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Log.e("Firestore Error", error.getMessage());
                    return;
                }
                else{
                    for (DocumentChange dc3:value.getDocumentChanges()) {
                        s3.add(dc3.getDocument().getString("location"));
                    }
                    try{
                        ad3.notifyDataSetChanged();
                    }
                    catch(Exception e){
                        Log.e("error notify", e.toString());
                    }

                }
            }
        });

        q4=c4.orderBy("location");
        q4.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Log.e("Firestore Error", error.getMessage());
                    return;
                }
                else{
                    for (DocumentChange dc4:value.getDocumentChanges()) {
                        s4.add(dc4.getDocument().getString("location"));
                    }
                    ad4.notifyDataSetChanged();
                }
            }
        });
    }
}