package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class addReport extends AppCompatActivity {
    private FirebaseFirestore db;
    CollectionReference Ref;
    ImageButton addvuner,addcritical,inspected,warning;
    Button submit;
    CheckBox INST1,INST2,INST3,INST4,INST5,INST6,INST7,INST8,INST9,INST10,INST11;
    String date;
    Date c;
    ModelReportCheckList m1;
    WriteBatch batch;
    Vulnerable v1,v2;
    Map<String,String> inspec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        CollectionReference r2,r3;
        DocumentReference r=Ref.document(MainActivity.RO+date);
        r2=r.collection("Vulnerable");
        r3=r.collection("critical");
        addvuner=findViewById(R.id.addvuner);
        addcritical=findViewById(R.id.addcritical);
        inspected=findViewById(R.id.addinsp);
        warning=findViewById(R.id.addwarn);
        submit=findViewById(R.id.submit);
        INST1=findViewById(R.id.inst1);
        INST2=findViewById(R.id.inst2);
        INST3=findViewById(R.id.inst3);
        INST4=findViewById(R.id.inst4);
        INST5=findViewById(R.id.inst5);
        INST6=findViewById(R.id.inst6);
        INST7=findViewById(R.id.inst7);
        INST8=findViewById(R.id.inst8);
        INST9=findViewById(R.id.inst9);
        INST10=findViewById(R.id.inst10);
        INST11=findViewById(R.id.inst11);
        db = FirebaseFirestore.getInstance();
        Ref = db.collection("checklist");
        c=new Date();
        date=c.toString();
        addvuner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new adddialog();
                newFragment.setCancelable(true);
                Button save=(Button) newFragment.findViewById(R.id.save);
                EditText e=(EditText) newFragment.
                newFragment.show(getSupportFragmentManager(), "game");
            }
        });
        addcritical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new adddialog();
                newFragment.setCancelable(true);
                newFragment.show(getSupportFragmentManager(), "game");
            }
        });
        inspected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new adddialog2();
                newFragment.setCancelable(true);
                newFragment.show(getSupportFragmentManager(), "game");
            }
        });
        warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new adddialog2();
                newFragment.setCancelable(true);
                newFragment.show(getSupportFragmentManager(), "game");
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m1=new ModelReportCheckList();
                m1.setRO(MainActivity.RO);
                m1.setINST1(INST1.isChecked());
                m1.setINST2(INST2.isChecked());
                m1.setINST3(INST3.isChecked());
                m1.setINST4(INST4.isChecked());
                m1.setINST5(INST5.isChecked());
                m1.setINST6(INST6.isChecked());
                m1.setINST7(INST7.isChecked());
                m1.setINST8(INST8.isChecked());
                m1.setINST9(INST9.isChecked());
                m1.setINST10(INST10.isChecked());
                m1.setINST11(INST11.isChecked());
                batch.set(r,m1,SetOptions.merge());
            }
        });
    }
}