package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class Report extends AppCompatActivity {
    FloatingActionButton button;
    ImageButton from,to;
    RecyclerView r;
    List<ModelReport> l;
    private FirebaseFirestore db;
    CollectionReference Ref;
    ReportAdapter ad;
    Query q;
    RecyclerView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        db=FirebaseFirestore.getInstance();
        Ref = db.collection("checklist");
        l=new ArrayList<>();
        ad=new ReportAdapter(this, (ArrayList<ModelReport>) l);
        r=findViewById(R.id.reportList);
        button=findViewById(R.id.addrepo);
        from=findViewById(R.id.From);
        r=findViewById(R.id.reportList);
        to=findViewById(R.id.To);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(this));
        //q=Ref.orderBy("")
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