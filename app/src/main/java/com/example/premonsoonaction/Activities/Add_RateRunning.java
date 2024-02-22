package com.example.premonsoonaction.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.premonsoonaction.DatePick2;
import com.example.premonsoonaction.Models.ModelRate;
import com.example.premonsoonaction.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;

public class Add_RateRunning extends AppCompatActivity {

    EditText cname,cnumber,cmail,location,pmis,started,ended,detail;
    AutoCompleteTextView rate;
    ArrayAdapter ad;
    Button save;
    private String name,loc;
    ModelRate rat;
    CollectionReference Ref;

    Query q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rate_running);
        this.getSupportActionBar().hide();
        cname=findViewById(R.id.contractorName);
        cnumber=findViewById(R.id.contractorNumber);
        cmail=findViewById(R.id.contractorMail);
        rate=(AutoCompleteTextView)findViewById(R.id.Type);
        location = findViewById(R.id.addloc);
        pmis = findViewById(R.id.pmis);
        started =findViewById(R.id.startDate);
        ended = findViewById(R.id.endDate);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Ref = db.collection("rate running contracts");
        loc= MainActivity.pmu;
        rat = new ModelRate();
        ad = ArrayAdapter.createFromResource(Add_RateRunning.this, R.array.rate_running, android.R.layout.select_dialog_singlechoice);
        rate.setThreshold(1);
        rate.setAdapter(ad);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate.showDropDown();
            }
        });
        save=findViewById(R.id.save);
        save.setEnabled(false);
        /*no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    no.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    no.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(no.getText().toString().trim().isEmpty()){
                    no.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });*/
        started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePick2(started);
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        ended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePick2(ended);
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        cname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    cname.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    cname.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(cname.getText().toString().trim().isEmpty()){
                    cname.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });

        cnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    cnumber.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    cnumber.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(cnumber.getText().toString().trim().isEmpty()){
                    cnumber.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });
        location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    location.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (cnumber.getText().toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    location.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (cnumber.getText().toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(location.toString().trim().isEmpty()){
                    location.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (cnumber.getText().toString().trim().isEmpty()||started.getText().toString().trim().isEmpty()||ended.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                /*rat.setName(name.trim());
                rat.setNo(Integer.parseInt(no.getText().toString().trim()));
                rat.setstarted(started.getText().toString().trim());
                rat.setended(ended.getText().toString().trim());
                rat.setCname(cname.getText().toString().trim());
                rat.setNumber(Integer.parseInt(cnumber.getText().toString().trim()));
                rat.setLocation(location.getText().toString().trim());
                rat.setPmu(MainActivity.pmu);
                if(cmail.getText().toString().trim().isEmpty()){
                    rat.setCemail(" ");
                }
                else{
                    rat.setCemail(cmail.getText().toString().trim());
                }
                Ref.document(loc + name).set(rat).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "DocumentSnapshot successfully updated!");
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Error updating details", e.toString());
                        finish();
                    }
                });
            }
        });*/try{
                Intent i = new Intent(Add_RateRunning.this, Equipments.class);
                startActivity(i);
                finish();
             }
             catch(Exception e){
                 Log.e("intentError",e.toString());
             }
            }
        });
    }
}