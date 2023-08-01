package com.example.premonsoonaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    Dialog customDialog;
    List<Vulnerable> l1;
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
               /* DialogFragment newFragment = new adddialog();
                newFragment.setCancelable(true);
                newFragment.show(getSupportFragmentManager(), "game");*/
                customDialog=new Dialog(addReport.this);
                customDialog.setContentView(R.layout.dialog);
                customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                Button sb=customDialog.findViewById(R.id.save);
                l1=new ArrayList<Vulnerable>();
                EditText e1,e2,e3;
                e1=findViewById(R.id.typeinput);
                e2=findViewById(R.id.Location);
                e3=findViewById(R.id.no);
                sb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Vulnerable v1=new Vulnerable();
                         v1.setTYPE(e1.getText().toString());
                         v1.setNO(Integer.parseInt(e3.getText().toString()));
                         v1.setLOCATION(e2.getText().toString());
                         l1.add(v1);
                    }
                });
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
            CollectionReference rc1=r.collection("Vulnerable");
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
                for(int i=0;i<l1.size();i++){
                    batch.set(rc1.document(), l1.get(i),SetOptions.merge());
                }
                batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d( "onSuccess: ","batch uploaded");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("onFailure: ",e.toString() );
                    }
                });
                customDialog.cancel();
            }
        });
    }
}