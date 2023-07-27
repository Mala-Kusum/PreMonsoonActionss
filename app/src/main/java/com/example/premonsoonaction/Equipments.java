package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class Equipments extends AppCompatActivity {
    FloatingActionButton add;
    private FirebaseFirestore db;
    CollectionReference Ref;
    Query querya,queryb;
    RecyclerView recycler;
    MaterialAdapter adapt;
    ArrayList<ModelEquipment> list,filtered;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);
        db = FirebaseFirestore.getInstance();
        add=findViewById(R.id.add);
        t=findViewById(R.id.No);
        list = new ArrayList<ModelEquipment>();
        adapt=new MaterialAdapter(this,list);
        recycler = findViewById(R.id.SearchByDesignation);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        querya=Ref.orderBy("")
        switch(Action.selectedAction){
            case "Equipment":
                t.setText("No.");
                Ref = db.collection("equipments");
                break;
            case "Material":
                t.setText("Quantity.");
                Ref = db.collection("materials");
                break;
            case "Rate running":
                t.setText("No.");
                Ref = db.collection("rate running contracts");
                break;
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Equipments.this,Add_Equipment.class);
                startActivity(i);
            }
        });
    }
}