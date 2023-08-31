package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class PMUs extends AppCompatActivity {

    RecyclerView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmus);
        grid=findViewById(R.id.grid);
        String[] s = getResources().getStringArray(R.array.Guwahati);
        placard_adapter ad=new placard_adapter(PMUs.this,s);
        grid.setAdapter(ad);
    }
}