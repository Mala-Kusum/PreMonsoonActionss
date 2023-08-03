package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
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
    Query q;
    RecyclerView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        db=FirebaseFirestore.getInstance();
        Ref = db.collection("checklist");
        l=new ArrayList<>();
        button=findViewById(R.id.addrepo);
        from=findViewById(R.id.From);
        r=findViewById(R.id.reportList);
        to=findViewById(R.id.To);
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