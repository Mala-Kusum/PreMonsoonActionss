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
import android.widget.ImageButton;

import java.util.HashMap;


public class addReport extends AppCompatActivity {

    ImageButton addvuner,addcritical,inspected,warning;
    Button submit;
    CheckBox INST1,INST2,INST3,INST4,INST5,INST6,INST7,INST8,INST9,INST10,INST11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
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
        addvuner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new adddialog();
                newFragment.setCancelable(true);
                newFragment.show(getSupportFragmentManager(), "game");
                Button save=findViewById(R.id.save);
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
                HashMap<String,Boolean> m1=new HashMap<>();
                m1.put("INST1",INST1.isChecked());
                m1.put("INST2",INST2.isChecked());
                m1.put("INST3",INST3.isChecked());
                m1.put("INST4",INST4.isChecked());
                m1.put("INST5",INST5.isChecked());
                m1.put("INST6",INST6.isChecked());
                m1.put("INST7",INST7.isChecked());
                m1.put("INST8",INST8.isChecked());
                m1.put("INST9",INST9.isChecked());
                m1.put("INST10",INST10.isChecked());
                m1.put("INST11",INST11.isChecked());
            }
        });
    }

}