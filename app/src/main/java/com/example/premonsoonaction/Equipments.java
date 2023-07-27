package com.example.premonsoonaction;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Equipments extends AppCompatActivity {
    FloatingActionButton add;
    private FirebaseFirestore db;
    CollectionReference Ref;
    Query querya;
    RecyclerView recycler;
    MaterialAdapter adapt;
    ArrayList<ModelEquipment> list,filtered;
    TextView t;
    EditText filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);
        db = FirebaseFirestore.getInstance();
        add=findViewById(R.id.add);
        filter=findViewById(R.id.filter);
        t=findViewById(R.id.No);
        list = new ArrayList<>();
        adapt=new MaterialAdapter(this,list);
        recycler = findViewById(R.id.SearchByDesignation);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
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
        querya=Ref.orderBy("NAME").orderBy("PMU");
        querya.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore Error", error.getMessage());
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        list.add(dc.getDocument().toObject(ModelEquipment.class));
                        System.out.println("sssssssssssssssssssssssssssssssss        " );
                        System.out.println(list);
                    }
                    adapt.notifyDataSetChanged();
                }
            }
        });

        for(int i=0;i<list.size();i++){
            Log.e( "onCreathuyge: ",list.get(i).getName() );
        }



        recycler.setAdapter(adapt);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Equipments.this,Add_Equipment.class);
                startActivity(i);
            }
        });
    }
}