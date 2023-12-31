package com.example.premonsoonaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;

public class Add_RateRunning extends AppCompatActivity {

    EditText no,start,end,cname,cnumber,cmail,location;
    Spinner rate;
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
        no=findViewById(R.id.no);
        start=findViewById(R.id.startDate);
        end=findViewById(R.id.endDate);
        cname=findViewById(R.id.contractorName);
        cnumber=findViewById(R.id.contractorNumber);
        cmail=findViewById(R.id.contractorMail);
        rate=findViewById(R.id.Type);
        location = findViewById(R.id.addloc);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Ref = db.collection("rate running contracts");
        loc=MainActivity.pmu;

        rat = new ModelRate();

        ad = ArrayAdapter.createFromResource(Add_RateRunning.this, R.array.Equipments, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_item);
        rate.setAdapter(ad);

        rate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                name = (String) adapterView.getItemAtPosition(i);
                rat.setName(name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                View selectedView = rate.getSelectedView();
                if (selectedView instanceof TextView) {
                    rate.requestFocus();
                    TextView selectedTextView = (TextView) selectedView;
                    selectedTextView.setError("error"); // any name of the error will do
                    selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
                    selectedTextView.setText("Please select an option"); // actual error message
                    rate.performClick(); // to open the spinner list if error is found.
                    save.setEnabled(false);
                }
            }
        });

        save=findViewById(R.id.save);
        save.setEnabled(false);
        no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    no.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
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
                } else if (location.toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
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
                } else if (location.toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });
        start.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    start.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    start.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(start.getText().toString().trim().isEmpty()){
                    start.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });

        end.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    end.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    end.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(end.getText().toString().trim().isEmpty()){
                    end.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });

        cname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    cname.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
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
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
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
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
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
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
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
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
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
                } else if (location.toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
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
                } else if (cnumber.getText().toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
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
                } else if (cnumber.getText().toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
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
                } else if (cnumber.getText().toString().trim().isEmpty()||no.getText().toString().trim().isEmpty()||start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()) {
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
                rat.setName(name.trim());
                rat.setNo(Integer.parseInt(no.getText().toString().trim()));
                rat.setStart(start.getText().toString().trim());
                rat.setEnd(end.getText().toString().trim());
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
        });
    }
}