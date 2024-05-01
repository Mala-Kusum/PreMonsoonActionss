package com.example.premonsoonaction.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.premonsoonaction.AdapterClasses.AddLocAdapter;
import com.example.premonsoonaction.AdapterClasses.LocationAdapter;
import com.example.premonsoonaction.AdapterClasses.vulnerableAdapter;
import com.example.premonsoonaction.Models.Vulnerable;
import com.example.premonsoonaction.Models.reportGetModel;
import com.example.premonsoonaction.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class  ShowReport extends AppCompatActivity {

    public static String docid;
    CollectionReference c1,c2,c3,c4;
    public static ArrayList<Vulnerable> l1,l2;
    public static ArrayList<Vulnerable> s3;
    ArrayList<String> s4;
    public static reportGetModel ob;
    // batch2;
    Vulnerable ob1,ob2;
    public static Boolean INST1,INST2,INST3,INST4,INST5,INST6,INST7,INST8,INST9,INST10,INST11;
    TextView inst1,inst2,inst3,inst4,inst5,inst6,inst7,inst8,inst9,inst10,inst11;
    AddLocAdapter ad1,ad2,ad3;
    LocationAdapter ad4;
    RecyclerView r1,r2,r3,r4;
    Query q1,q2,q3,q4;
    int i,j;
    public static Boolean editrate = false;
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
            inst1.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst1.setText("Not Done");
            inst1.setBackground(getDrawable(R.drawable.not_done));
        }
        if(INST2==true){
            inst2.setText("Done");
            inst2.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst2.setText("Not Done");
            inst2.setBackground(getDrawable(R.drawable.not_done));
        }
        if(INST3==true){
            inst3.setText("Done");
            inst3.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst3.setText("Not Done");
            inst3.setBackground(getDrawable(R.drawable.not_done));
        }
        if(INST4==true){
            inst4.setText("Done");
            inst4.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst4.setText("Not Done");
            inst4.setBackground(getDrawable(R.drawable.not_done));
        }
        if(INST5==true){
            inst5.setText("Done");
            inst5.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst5.setText("Not Done");
            inst5.setBackground(getDrawable(R.drawable.not_done));
        }
        if(INST6==true){
            inst6.setText("Done");
            inst6.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst6.setText("Not Done");
            inst6.setBackground(getDrawable(R.drawable.not_done));
        }
        if(INST7==true){
            inst7.setText("Done");
            inst7.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst7.setText("Not Done");
            inst7.setBackground(getDrawable(R.drawable.not_done));
        }
        if(INST8==true){
            inst8.setText("Done");
            inst8.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst8.setText("Not Done");
            inst8.setBackground(getDrawable(R.drawable.not_done));
        }
        if(INST9==true){
            inst9.setText("Done");
            inst9.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst9.setText("Not Done");
            inst9.setBackground(getDrawable(R.drawable.not_done));
        }
        if(INST10==true){
            inst10.setText("Done");
            inst10.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst10.setText("Not Done");
            inst10.setBackground(getDrawable(R.drawable.not_done));
        }
        if(INST11==true){
            inst11.setText("Done");
            inst11.setBackground(getDrawable(R.drawable.done));
        }
        else{
            inst11.setText("Not Done");
            inst11.setBackground(getDrawable(R.drawable.not_done));
        }
        ad1=new AddLocAdapter(ShowReport.this,l1);
        ad2=new AddLocAdapter(ShowReport.this,l2);
        try{
            ad3=new AddLocAdapter(ShowReport.this,s3);
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
                        //System.out.println("vulnerable doc "+ dc1.getDocument().toObject(Vulnerable.class).getLOCATION()+dc1.getDocument().toObject(Vulnerable.class).getNO()+dc1.getDocument().toObject(Vulnerable.class).getTYPE());

                        ob1.setTYPE(dc1.getDocument().getString("type"));
                        try {
                            ob1.setLOCATION(dc1.getDocument().getString("location"));
                        }
                        catch(Exception e){
                            System.out.println("error no "+e.toString());
                        }
                        //System.out.println("ba " +ob1.getLOCATION()+" "+ob1.getTYPE()+" "+ob1.getNO());
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
                }
                else{
                    for (DocumentChange dc2:value.getDocumentChanges()) {
                        ob2.setTYPE(dc2.getDocument().getString("type"));
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
                        Vulnerable v = new Vulnerable();
                        v = dc3.getDocument().toObject(Vulnerable.class);
                        s3.add(v);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // methods to control the operations that will
    // happen when user clicks on the action buttons
    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        if(item.getItemId()==R.id.edit){
            editrate=true;
            Intent i=new Intent(ShowReport.this, addReport.class);
            startActivity(i);
        }
        /*switch (item.getItemId()){
            case R.id.edit:
                Intent i=new Intent(ShowReport.this, addReport.class);
                startActivity(i);
                break;
        }*/
        return super.onOptionsItemSelected(item);
    }
}