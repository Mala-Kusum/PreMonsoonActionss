package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class addReport2 extends AppCompatActivity {
    ImageButton addvuner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report2);
        addvuner=findViewById(R.id.addvuner);
        addvuner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new adddialog();
                newFragment.show(getSupportFragmentManager(), "game");
            }
        });
    }
}