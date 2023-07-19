package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Action extends AppCompatActivity {
    Button equipment,actiontaken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        equipment=findViewById(R.id.equipments);
        actiontaken=findViewById(R.id.actiontaken);
        actiontaken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Action.this,Report.class);
                startActivity(i);
            }
        });
        equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Action.this,Equipments.class);
                startActivity(i);
            }
        });
    }
}