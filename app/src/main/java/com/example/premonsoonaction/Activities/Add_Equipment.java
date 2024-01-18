package com.example.premonsoonaction.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.premonsoonaction.Models.ModelEquipment;
import com.example.premonsoonaction.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class Add_Equipment extends AppCompatActivity {
    //Spinner pmu;
    EditText no,site;
    AutoCompleteTextView eq;
    Spinner pmu;
    ArrayAdapter<CharSequence> ad1,ad;
    private CollectionReference Ref;
    String name, n, loc;
    Button save;
    Query querya;
    String[] pmus;
    ModelEquipment me;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);
        //Declarations
        eq = (AutoCompleteTextView)findViewById(R.id.Type);
        no = findViewById(R.id.no);
        site = findViewById(R.id.site);
        pmu = findViewById(R.id.pmu);
        save = findViewById(R.id.save);

        //set eq type
        ad1 = ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Equipments, android.R.layout.select_dialog_singlechoice);
        eq.setThreshold(1);
        eq.setAdapter(ad1);
        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eq.showDropDown();
            }
        });

        //set PMU
        ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Guwahati, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_item);
        pmu.setAdapter(ad);
        pmu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loc=(String) adapterView.getItemAtPosition(i);
                save.setEnabled(true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                View selectedView = pmu.getSelectedView();
                if (selectedView instanceof TextView) {
                    pmu.requestFocus();
                    TextView selectedTextView = (TextView) selectedView;
                    selectedTextView.setError("error"); // any name of the error will do
                    selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
                    selectedTextView.setText("Please select an option"); // actual error message
                    pmu.performClick(); // to open the spinner list if error is found.
                    save.setEnabled(false);
                }
            }
        });

        //set save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Add_Equipment.this, Equipments.class);
                startActivity(i);
                finish();
            }
        });
    }
}