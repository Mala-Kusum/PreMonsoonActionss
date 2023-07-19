package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Action extends AppCompatActivity {
    Button equipment,actiontaken,material,raterunning;
    TextView t;
    public static String selectedAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        equipment=findViewById(R.id.equipments);
        actiontaken=findViewById(R.id.actiontaken);
        material=findViewById(R.id.materials);
        raterunning=findViewById(R.id.raterunning);
        t=findViewById(R.id.no);
        actiontaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAction="Report";
                Intent i=new Intent(Action.this,Report.class);
                startActivity(i);
            }
        });
        equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAction="Equipment";
                t.setText("No.");
                Intent i=new Intent(Action.this,Equipments.class);
                startActivity(i);
            }
        });
        material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAction="Material";
                t.setText("Quantity.");
                Intent i=new Intent(Action.this,Equipments.class);
                startActivity(i);
            }
        });
        raterunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAction="Rate running";
                t.setText("No.");
                Intent i=new Intent(Action.this,Equipments.class);
                startActivity(i);
            }
        });
    }
}