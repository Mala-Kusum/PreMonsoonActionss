package com.example.premonsoonaction;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Report extends AppCompatActivity {
    FloatingActionButton button;
    ImageButton from,to;
    RecyclerView r;
    List<reportGetModel> l;
    private FirebaseFirestore db;
    CollectionReference Ref;

    Query q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        db=FirebaseFirestore.getInstance();
        Ref = db.collection("checklist");
        l=new ArrayList<>();
        ReportAdapter ad;
        ad=new ReportAdapter(Report.this, (ArrayList<reportGetModel>) l);
        r=findViewById(R.id.reportList);
        button=findViewById(R.id.addrepo);
        from=findViewById(R.id.From);
        to=findViewById(R.id.To);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(ad);
        q=Ref.whereEqualTo("ro",MainActivity.RO).orderBy("submitted", Query.Direction.DESCENDING);
        q.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore Error", error.getMessage());
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        reportGetModel ob = new reportGetModel();
                        try{
                            Timestamp t=(Timestamp) dc.getDocument().get("submitted");
                            ob.setDate(t.toDate());
                            System.out.println("date,"+t.toDate());
                            ob.setinst1((boolean) dc.getDocument().get("inst1"));
                            ob.setinst2((boolean) dc.getDocument().get("inst2"));
                            ob.setinst3((boolean) dc.getDocument().get("inst3"));
                            ob.setinst4((boolean) dc.getDocument().get("inst4"));
                            ob.setinst5((boolean) dc.getDocument().get("inst5"));
                            ob.setinst6((boolean) dc.getDocument().get("inst6"));
                            ob.setinst7((boolean) dc.getDocument().get("inst7"));
                            ob.setinst8((boolean) dc.getDocument().get("inst8"));
                            ob.setinst9((boolean) dc.getDocument().get("inst9"));
                            ob.setinst10((boolean) dc.getDocument().get("inst10"));
                            ob.setinst11((boolean) dc.getDocument().get("inst11"));
                            ob.setRO(dc.getDocument().get("ro").toString());
                            l.add(ob);
                        }
                        catch(Exception e){
                            Log.e("getTimeE", e.toString());
                        }
                        System.out.println("sssssssssssssssssssssssssssssssss        "+l);
                        ad.notifyDataSetChanged();
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Report.this,addReport.class);
                startActivity(i);
            }
        });
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePick();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePick();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }
}