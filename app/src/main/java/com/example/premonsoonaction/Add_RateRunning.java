package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Add_RateRunning extends AppCompatActivity {

    EditText no,start,end,cname,cnumber,cmail;
    Spinner rate;
    ArrayAdapter ad;
    Button save;

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
        ad = ArrayAdapter.createFromResource(Add_RateRunning.this, R.array.Equipments, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_item);
        rate.setAdapter(ad);
        save=findViewById(R.id.save);
        save.setEnabled(false);
        no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    no.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true;);
                }
            }

            @Override
            public void onTextChanged(CharSequence t, int i, int i1, int i2) {
                if(t.toString().trim().isEmpty()){
                    no.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true;);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(no.getText().toString().trim().isEmpty()){
                    no.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if (start.getText().toString().trim().isEmpty()||end.getText().toString().trim().isEmpty()||cname.getText().toString().trim().isEmpty()||cnumber.getText().toString().trim().isEmpty()) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });
    }
}