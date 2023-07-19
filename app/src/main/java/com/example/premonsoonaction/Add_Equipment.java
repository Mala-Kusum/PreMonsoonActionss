package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Add_Equipment extends AppCompatActivity {
    Spinner pmu;
    private FirebaseFirestore db;
    private CollectionReference noteRef;
    TextView t;
    ArrayAdapter<CharSequence> ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);
        t=findViewById(R.id.no);
        switch(Action.selectedAction){
            case "Equipment":
                t.setText("No.");
                break;
            case "Material":
                t.setText("Quantity.");
                break;
            case "Rate running":
                t.setText("No.");
                break;
        }
        pmu=findViewById(R.id.pmu);
        switch(MainActivity.RO){
            case"Ro-Leh/Srinagar":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.LehSrinagar, android.R.layout.simple_spinner_item);
                break;
            case"RO-Shillong":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Shillong, android.R.layout.simple_spinner_item);
                break;
            case"RO-LADAKH":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.LADAKH, android.R.layout.simple_spinner_item);
                break;
            case"RO-Kohima":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Kohima, android.R.layout.simple_spinner_item);
                break;
            case"RO-Jammu":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Jammu, android.R.layout.simple_spinner_item);
                break;
            case"RO-Itanagar":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Itanagar, android.R.layout.simple_spinner_item);
                break;
            case"RO-Imphal":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Imphal, android.R.layout.simple_spinner_item);
                break;
            case"RO-Guwahati":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Guwahati, android.R.layout.simple_spinner_item);
                break;
            case"RO-Gangtok":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Gangtok, android.R.layout.simple_spinner_item);
                break;
            case"RO-Dehradun":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Dehradun, android.R.layout.simple_spinner_item);
                break;
            case"RO-Aizwal":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Aizwal, android.R.layout.simple_spinner_item);
                break;
            case"RO-Agartala":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Agartala, android.R.layout.simple_spinner_item);
                break;
            case"RO-Port Blair":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.PortBlair, android.R.layout.simple_spinner_item);
                break;
            case"RO-Srinagar":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.SRINAGAR, android.R.layout.simple_spinner_item);
                break;
            case"New Delhi":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.NewDelhi, android.R.layout.simple_spinner_item);
                break;
            default:
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Select, android.R.layout.simple_spinner_item);
        }
        ad.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pmu.setAdapter(ad);
    }
}