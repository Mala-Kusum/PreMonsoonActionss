package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

public class PMUs extends AppCompatActivity {

    RecyclerView grid;
    String[] s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmus);
        grid=findViewById(R.id.grid);
        switch(MainActivity.RO){
            case"Ro-Leh/Srinagar":
                s = getResources().getStringArray(R.array.LehSrinagar);
                break;
            case"RO-Shillong":
                s = getResources().getStringArray(R.array.Shillong);
                break;
            case"RO-LADAKH":
                s = getResources().getStringArray(R.array.LADAKH);
                break;
            case"RO-Kohima":
                s = getResources().getStringArray(R.array.Kohima);
                break;
            case"RO-Jammu":
                s = getResources().getStringArray(R.array.Jammu);
                break;
            case"RO-Itanagar":
                s = getResources().getStringArray(R.array.Itanagar);
                break;
            case"RO-Imphal":
                s = getResources().getStringArray(R.array.Imphal);
                break;
            case"RO-Guwahati":
                s = getResources().getStringArray(R.array.Guwahati);
                break;
            case"RO-Gangtok":
                s = getResources().getStringArray(R.array.Gangtok);
                break;
            case"RO-Dehradun":
                s = getResources().getStringArray(R.array.Dehradun);
                break;
            case"RO-Aizwal":
                s = getResources().getStringArray(R.array.Aizwal);
                break;
            case"RO-Agartala":
                s = getResources().getStringArray(R.array.Agartala);
                break;
            case"RO-Port Blair":
                s = getResources().getStringArray(R.array.PortBlair);
                break;
            case"RO-Srinagar":
                s = getResources().getStringArray(R.array.SRINAGAR);
                break;
            case"New Delhi":
                s = getResources().getStringArray(R.array.NewDelhi);
                break;
        }
        placard_adapter ad=new placard_adapter(PMUs.this,s);
        grid.setAdapter(ad);
    }
}