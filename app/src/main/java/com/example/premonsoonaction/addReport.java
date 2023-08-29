package com.example.premonsoonaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
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
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;
import com.google.firestore.v1.DocumentTransform;

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
    List<Vulnerable> l1,l2;
    List<Location> l3,l4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        //CollectionReference r2,r3;
        /*
        r2=r.collection("Vulnerable");
        r3=r.collection("critical");*/
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
        l1=new ArrayList<Vulnerable>();
        l2=new ArrayList<Vulnerable>();
        l3=new ArrayList<Location>();
        l4=new ArrayList<Location>();
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
                sb.setEnabled(false);
                EditText e1,e2,e3;
                e1=customDialog.findViewById(R.id.typeinput);
                e2=customDialog.findViewById(R.id.Location);
                e3=customDialog.findViewById(R.id.no);
                e1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e1.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e1.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(e1.getText().toString().trim().length()==0){
                            e1.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                });

                e2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e2.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e2.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(e2.getText().toString().trim().length()==0){
                            e2.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                });

                e3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e3.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()||e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e3.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()||e1.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(e3.getText().toString().trim().length()==0){
                            e3.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()||e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                });
                customDialog.show();
                sb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Vulnerable v1=new Vulnerable();
                         v1.setTYPE(e1.getText().toString());
                         v1.setNO(Integer.parseInt(e3.getText().toString()));
                         v1.setLOCATION(e2.getText().toString());
                         l1.add(v1);
                         customDialog.cancel();
                    }
                });
            }
        });
        addcritical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {customDialog=new Dialog(addReport.this);
                customDialog.setContentView(R.layout.dialog);
                customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                Button sb=customDialog.findViewById(R.id.save);
                sb.setEnabled(false);
                EditText e1,e2,e3;
                e1=customDialog.findViewById(R.id.typeinput);
                e2=customDialog.findViewById(R.id.Location);
                e3=customDialog.findViewById(R.id.no);
                e1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e1.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e1.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(e1.getText().toString().trim().length()==0){
                            e1.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                });

                e2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e2.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e2.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(e2.getText().toString().trim().length()==0){
                            e2.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()||e3.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                });

                e3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e3.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()||e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e3.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()||e1.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(e3.getText().toString().trim().length()==0){
                            e3.setError("the feild cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()||e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                });
                customDialog.show();
                sb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Vulnerable v1=new Vulnerable();
                        v1.setTYPE(e1.getText().toString());
                        v1.setNO(Integer.parseInt(e3.getText().toString()));
                        v1.setLOCATION(e2.getText().toString());
                        l2.add(v1);
                        customDialog.cancel();
                    }
                });
            }
        });
        inspected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*DialogFragment newFragment = new adddialog2();
                newFragment.setCancelable(true);
                newFragment.show(getSupportFragmentManager(), "game");*/
                customDialog.setContentView(R.layout.dialog2);
                customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                Button sb=customDialog.findViewById(R.id.save);
                EditText e1;
                e1=customDialog.findViewById(R.id.loc);
                customDialog.show();
                sb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Location lo=new Location();
                        lo.setLOCATION(e1.getText().toString());
                        l3.add(lo);
                        customDialog.cancel();
                    }
                });
            }
        });
        warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.setContentView(R.layout.dialog2);
                customDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                Button sb=customDialog.findViewById(R.id.save);
                EditText e1;
                e1=customDialog.findViewById(R.id.loc);
                customDialog.show();
                sb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Location lo=new Location();
                        lo.setLOCATION(e1.getText().toString());
                        l4.add(lo);
                        customDialog.cancel();
                    }
                });
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Timestamp> p=new HashMap<>();
                p.put("submitted", Timestamp.now());
                batch= db.batch();
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
                batch.set(Ref.document(MainActivity.RO+date),m1,SetOptions.merge());
                batch.set(Ref.document(MainActivity.RO+date),p,SetOptions.merge());
                for(int i=0;i<l1.size();i++){
                    batch.set(Ref.document(MainActivity.RO+date).collection("Vulnerable").document(), l1.get(i),SetOptions.merge());
                }
                for(int i=0;i<l2.size();i++){
                    batch.set(Ref.document(MainActivity.RO+date).collection("Critical").document(), l2.get(i),SetOptions.merge());
                }
                for(int i=0;i<l3.size();i++){
                    batch.set(Ref.document(MainActivity.RO+date).collection("Inspected").document(), l3.get(i),SetOptions.merge());
                }
                for(int i=0;i<l4.size();i++){
                    batch.set(Ref.document(MainActivity.RO+date).collection("Warning").document(), l4.get(i),SetOptions.merge());
                }
                batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d( "onSuccess: ","batch uploaded");
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("onFailure: ",e.toString() );
                        finish();
                    }
                });
            }
        });
    }
}