package com.example.premonsoonaction.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.premonsoonaction.DatePick2;
import com.example.premonsoonaction.Models.RateModel;
import com.example.premonsoonaction.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Add_RateRunning extends AppCompatActivity {
    public EditText cname,cnumber,cmail,location,pmis,started,ended,detail;
    public AutoCompleteTextView rate;
    ArrayAdapter ad;
    Button save;
    private String name,loc;
    RateModel rat;
    CollectionReference Ref;
    List<String> ratetypes;
    Map<String,Boolean> m;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    public Date getDateFromString(String datetoSaved){
        try {
            Date date = DATE_FORMAT.parse(datetoSaved);
            return date ;
        } catch (ParseException e){
            return null ;
        }
    }
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
        save=findViewById(R.id.save);
        detail=findViewById(R.id.essentialdetails);
        rat = new RateModel();
        save.setEnabled(false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Ref = db.collection("rate running contracts");
        loc= MainActivity.pmu;
        try{
            m = new HashMap<>();
            ratetypes=new ArrayList<>();
            ad=new ArrayAdapter<String>(Add_RateRunning.this,android.R.layout.select_dialog_singlechoice,ratetypes);
        }
        catch(Exception e){
            Log.e("ad: ",e.toString());
        }
        rate.setThreshold(1);
        rate.setAdapter(ad);
        try{
            db.collection("rateTypes").orderBy("type").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        m.put(Objects.requireNonNull(doc.getData().get("type")).toString().trim(),true);
                        ratetypes.add(Objects.requireNonNull(doc.getData().get("type")).toString().trim());
                        ad.notifyDataSetChanged();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("onFailure ratetypes: ", e.toString());
                }
            });
        }
        catch(Exception e){
            Log.e("get ratetypes: ",e.toString());
        }
        //set values to be editted
        if(this.getParent().equals(Equipments.class)){

            /*save.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    nam=name.getText().toString();
                    desi=des.getText().toString();
                    depar=dep.getText().toString();
                    addr=add.getText().toString();
                    emai=email.getText().toString();
                    tele=tel.getText().toString();
                    mobi=mob.getText().toString();
                    sta=st.getText().toString();

                    m.put(Name,nam);
                    m.put(Email,MainActivity.EMAIL);
                    m.put(Designation,desi);
                    m.put(Department,depar);
                    m.put(Address,addr);
                    m.put(Mobile,mobi);
                    m.put(Landline,tele);
                    m.put(State,sta);
                    m.put(Type,ty);
                    Log.e("tag",DocID);

                    noteRef.document(DocID).set(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void v) {
                            Log.d("TAG", "DocumentSnapshot successfully updated!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Error updating profile details",e.toString());
                        }
                    });
                    Intent intent=new Intent(admin_profile_edit.this,MainActivity.parent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });*/
        }

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate.showDropDown();
            }
        });
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
                rat.setName(cname.getText().toString().trim());
                rat.setStart(getDateFromString(started.getText().toString()));
                rat.setEnd(getDateFromString(ended.getText().toString().trim()));
                Log.d( "start & end : ",getDateFromString(started.getText().toString())+" "+getDateFromString(ended.getText().toString().trim()));
                rat.setMobile(cnumber.getText().toString().trim());
                rat.setAddress(location.getText().toString().trim());
                rat.setPmis(Integer.parseInt(pmis.getText().toString().trim()));
                rat.setType(rate.getText().toString().trim());
                rat.setRo(MainActivity.RO);
                if(!m.containsKey(rate.getText().toString().trim())){
                    Map<String,String> m1 = new HashMap<>();
                    m1.put("type",rate.getText().toString().trim());
                    db.collection("rateTypes").document(rate.getText().toString().trim()).set(m1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.e( "onSuccess rate type: ", "added successfully");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e( "onFailure rate type: ", e.toString());
                        }
                    });
                }
                if(cmail.getText().toString().trim().isEmpty()){
                    try{
                        rat.setEmail("");
                    }
                    catch(Exception e){
                        Log.e("adding email: ",e.toString());
                    }
                }
                else{
                    try{
                        rat.setEmail(cmail.getText().toString().trim());
                    }
                    catch(Exception e){
                        Log.e("adding email: ",e.toString());
                    }
                }
               if(detail.getText().toString().trim().isEmpty()){
                   rat.setDetails(" ");
               }
               else{
                   rat.setDetails(detail.getText().toString().trim());
               }
               try{
                Ref.document(pmis.getText().toString().trim()).set(rat).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "DocumentSnapshot successfully updated!");
                        //finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Error updating details", e.toString());
                        //finish();
                    }
                });
                   Intent i = new Intent(Add_RateRunning.this, Equipments.class);
                   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
