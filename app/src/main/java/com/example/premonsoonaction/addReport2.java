package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class addReport2 extends AppCompatActivity {
    ImageButton addvuner,addcritical,inspected,warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report2);
        addvuner=findViewById(R.id.addvuner);
        addcritical=findViewById(R.id.addcritical);
        inspected=findViewById(R.id.addinsp);
        warning=findViewById(R.id.addwarn);
        addvuner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new adddialog();
                newFragment.show(getSupportFragmentManager(), "game");
            }
        });
        addcritical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new adddialog();
                newFragment.show(getSupportFragmentManager(), "game");
            }
        });
        inspected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new adddialog2();
                newFragment.show(getSupportFragmentManager(), "game");
            }
        });
        warning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new adddialog2();
                newFragment.show(getSupportFragmentManager(), "game");
            }
        });
    }
}