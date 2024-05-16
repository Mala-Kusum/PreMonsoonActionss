package com.example.premonsoonaction.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.premonsoonaction.AdapterClasses.LocationCancellableAdapter;
import com.example.premonsoonaction.Models.Location;
import com.example.premonsoonaction.Models.ModelReportCheckList;
import com.example.premonsoonaction.R;
import com.example.premonsoonaction.Models.Vulnerable;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    List<Vulnerable> l3,l4;
    LocationCancellableAdapter a1,a2,a3;
    RecyclerView r1,r2,r3;
    /*public void deleteAtPath(String path) {
        Map<String, Object> data = new HashMap<>();
        data.put("path", path);

        HttpsCallableReference deleteFn =
                FirebaseFunctions.getInstance().getHttpsCallable("recursiveDelete");
        deleteFn.call(data)
                .addOnSuccessListener(new OnSuccessListener<HttpsCallableResult>() {
                    @Override
                    public void onSuccess(HttpsCallableResult httpsCallableResult) {
                        // Delete Success
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Delete failed
                        // ...
                    }
                });
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        //CollectionReference r2,r3;
        /*r2=r.collection("Vulnerable");
        r3=r.collection("critical");*/
        addvuner=findViewById(R.id.addvuner);
        addcritical=findViewById(R.id.addcritical);
        inspected=findViewById(R.id.addinsp);
        l1=new ArrayList<Vulnerable>();
        l2=new ArrayList<Vulnerable>();
        l3=new ArrayList<Vulnerable>();
        //l4=new ArrayList<Location>();
        r1 = findViewById(R.id.vulnerableconstructionloc);
        r2 = findViewById(R.id.vulnerabletonaturaldestructionloc);
        r3 = findViewById(R.id.locationinspectedbefore);
        a1 = new LocationCancellableAdapter(addReport.this, (ArrayList<Vulnerable>) l1);
        a2 = new LocationCancellableAdapter(addReport.this, (ArrayList<Vulnerable>) l2);
        a3 = new LocationCancellableAdapter(addReport.this, (ArrayList<Vulnerable>) l3);
        r1.setLayoutManager(new LinearLayoutManager(this));
        r2.setLayoutManager(new LinearLayoutManager(this));
        r3.setLayoutManager(new LinearLayoutManager(this));
        r1.setAdapter(a1);
        r2.setAdapter(a2);
        r3.setAdapter(a3);
        //warning=findViewById(R.id.addwarn);
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
        if(ShowReport.editrate){
            INST1.setChecked(ShowReport.INST1);
            INST2.setChecked(ShowReport.INST2);
            INST3.setChecked(ShowReport.INST3);
            INST4.setChecked(ShowReport.INST4);
            INST5.setChecked(ShowReport.INST5);
            INST6.setChecked(ShowReport.INST6);
            INST7.setChecked(ShowReport.INST7);
            INST8.setChecked(ShowReport.INST8);
            INST9.setChecked(ShowReport.INST9);
            INST10.setChecked(ShowReport.INST10);
            INST11.setChecked(ShowReport.INST11);
            l1.addAll(ShowReport.l1);
            a1.notifyDataSetChanged();
            l2.addAll(ShowReport.l2);
            a2.notifyDataSetChanged();
            l3.addAll(ShowReport.s3);
            a3.notifyDataSetChanged();
            //ShowReport.editrate=false;
        }
        addvuner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* DialogFragment newFragment = new adddialog();
                newFragment.setCancelable(true);
                newFragment.show(getSupportFragmentManager(), "game");*/
                customDialog=new Dialog(addReport.this);
                customDialog.setContentView(R.layout.dialog);
                Objects.requireNonNull(customDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                Button sb=customDialog.findViewById(R.id.save);
                sb.setEnabled(false);
                EditText e1,e2,e3;
                e1=customDialog.findViewById(R.id.typeinput);
                e2=customDialog.findViewById(R.id.Location);
                //e3=customDialog.findViewById(R.id.no);
                e1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(e1.getText().toString().trim().length()==0){
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
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
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        }
                        else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(e2.getText().toString().trim().length()==0){
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()) {
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
                         //v1.setNO(Integer.parseInt(e3.getText().toString()));
                         v1.setLOCATION(e2.getText().toString());
                         l1.add(v1);
                         a1.notifyDataSetChanged();
                         customDialog.cancel();
                    }
                });
            }
        });
        addcritical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new Dialog(addReport.this);
                customDialog.setContentView(R.layout.dialog);
                Objects.requireNonNull(customDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Button sb = customDialog.findViewById(R.id.save);
                sb.setEnabled(false);
                EditText e1, e2, e3;
                e1 = customDialog.findViewById(R.id.typeinput);
                e2 = customDialog.findViewById(R.id.Location);
                //e3=customDialog.findViewById(R.id.no);
                e1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (e1.getText().toString().trim().length() == 0) {
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }
                });

                e2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (e2.getText().toString().trim().length() == 0) {
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
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
                        //v1.setNO(Integer.parseInt(e3.getText().toString()));
                        v1.setLOCATION(e2.getText().toString());
                        l2.add(v1);
                        a1.notifyDataSetChanged();
                        customDialog.cancel();
                    }
                });
            }
        });

        inspected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new Dialog(addReport.this);
                customDialog.setContentView(R.layout.dialog);
                Objects.requireNonNull(customDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Button sb = customDialog.findViewById(R.id.save);
                sb.setEnabled(false);
                EditText e1, e2, e3;
                e1 = customDialog.findViewById(R.id.typeinput);
                e2 = customDialog.findViewById(R.id.Location);
                //e3=customDialog.findViewById(R.id.no);
                e1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }
                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (e1.getText().toString().trim().length() == 0) {
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }
                });

                e2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if (t.toString().trim().length() == 0) {
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e2.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if (e2.getText().toString().trim().length() == 0) {
                            e2.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        } else if (e1.getText().toString().isEmpty()) {
                            sb.setEnabled(false);
                        } else {
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
                        //v1.setNO(Integer.parseInt(e3.getText().toString()));
                        v1.setLOCATION(e2.getText().toString());
                        l3.add(v1);
                        a1.notifyDataSetChanged();
                        customDialog.cancel();
                    }
                });
            }
        });
        /*warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog=new Dialog(addReport.this);
                customDialog.setContentView(R.layout.dialog2);
                Objects.requireNonNull(customDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                Button sb=customDialog.findViewById(R.id.save);
                sb.setEnabled(false);
                EditText e1;
                e1=customDialog.findViewById(R.id.loc);
                e1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(e1.getText().toString().trim().length()==0){
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        }
                        else{
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                        if(t.toString().trim().length()==0){
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        }
                        else{
                            sb.setEnabled(true);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(e1.getText().toString().trim().length()==0){
                            e1.setError("the field cannot be empty");
                            sb.setEnabled(false);
                        }
                        else{
                            sb.setEnabled(true);
                        }
                    }
                });
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
        });*/
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ShowReport.editrate){
                    Ref.document(ShowReport.docid).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("onSuccess delete: ",ShowReport.docid+" is deleted");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("onFailure delete of "+ShowReport.docid+": ",e.toString());
                        }
                    });
                    ShowReport.editrate = false;
                }
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
                /*for(int i=0;i<l4.size();i++){
                    batch.set(Ref.document(MainActivity.RO+date).collection("Warning").document(), l4.get(i),SetOptions.merge());
                }*/
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