package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowReport extends AppCompatActivity {

    private FirebaseFirestore db;
    CollectionReference Ref;
    List<Vulnerable> l1,l2;
    List<Location> l3,l4;
    Date c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);
        db = FirebaseFirestore.getInstance();
        Ref = db.collection("checklist");
        l1=new ArrayList<Vulnerable>();
        l2=new ArrayList<Vulnerable>();
        l3=new ArrayList<Location>();
        l4=new ArrayList<Location>();
    }
}