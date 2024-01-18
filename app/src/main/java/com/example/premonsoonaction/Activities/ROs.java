package com.example.premonsoonaction.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.premonsoonaction.AdapterClasses.placard_adapter;
import com.example.premonsoonaction.R;

public class ROs extends AppCompatActivity {
    RecyclerView grid;
    public static String DName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ros);
        grid=findViewById(R.id.grid);
        String[] s={"RO-Port Blair","RO-Itanagar","RO-Guwahati","RO-Jammu","RO-Srinagar","RO-Ladakh","RO-Imphal","RO-Shillong","RO-Aizwal","RO-Kohima","RO-Gangtok","RO-Agartala","RO-Dehradun"};
        placard_adapter ad=new placard_adapter(ROs.this,s);
        grid.setAdapter(ad);
    }
}