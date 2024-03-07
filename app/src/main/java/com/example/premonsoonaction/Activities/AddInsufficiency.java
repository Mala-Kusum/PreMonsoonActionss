package com.example.premonsoonaction.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.premonsoonaction.Models.Insuf;
import com.example.premonsoonaction.Models.Unit;
import com.example.premonsoonaction.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddInsufficiency extends AppCompatActivity {

 AutoCompleteTextView ro,pmu,eq,unit;
 EditText loc,quant;
 private FirebaseFirestore db;
 private CollectionReference Ref;
 ArrayAdapter ad,ad2,eqa,unia;
 Button save;
 TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_insufficiency);
        db = FirebaseFirestore.getInstance();
        Ref = db.collection("insufficiency");
        ro =(AutoCompleteTextView) findViewById(R.id.ro);
        pmu = (AutoCompleteTextView) findViewById(R.id.pmu);
        loc = findViewById(R.id.loc);
        eq = (AutoCompleteTextView) findViewById(R.id.Type);
        t = findViewById(R.id.eq);
        unit = findViewById(R.id.unit);
        quant = findViewById(R.id.quantity);
        save=findViewById(R.id.save);
        ro.setText(MainActivity.RO);
        ad = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.ROs, android.R.layout.select_dialog_singlechoice);
        //ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.ROs, android.R.layout.select_dialog_singlechoice);
        unia = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Units, android.R.layout.select_dialog_singlechoice);
        eq.setText(Equipments.eq);

        ro.setThreshold(1);
        ro.setAdapter(ad);
        try{
            ro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ro.showDropDown();
                }
            });
        }
        catch(Exception e){
            Log.e("ro onclick: ", e.toString());
        }
        ro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("TAG",adapterView.getSelectedItem().toString());
                switch(adapterView.getSelectedItem().toString()){
                    case "Ro-Leh/Srinagar":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.LehSrinagar, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Shillong":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Shillong, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-LADAKH":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.LADAKH, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Kohima":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Kohima, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Jammu":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Jammu, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Itanagar":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Itanagar, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Imphal":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Imphal, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Guwahati":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Guwahati, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Gangtok":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Gangtok, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Dehradun":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Dehradun, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Aizwal":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Aizwal, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Agartala":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.Agartala, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-Port Blair":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.PortBlair, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "RO-SRINAGAR":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.SRINAGAR, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                    case "New Delhi":
                        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.NewDelhi, android.R.layout.simple_spinner_item);
                        pmu.setAdapter(ad2);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        pmu.setText(MainActivity.pmu);
        pmu.setThreshold(1);
        pmu.setAdapter(ad2);
        try{
            pmu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pmu.showDropDown();
                }
            });
        }
        catch (Exception e){
            Log.e("PMUList: ", e.toString() );
        }
        loc.setText(MainActivity.location);
        switch(Action.selectedAction){
            case "Equipment":
                eqa = ArrayAdapter.createFromResource(AddInsufficiency.this,R.array.Equipments, android.R.layout.select_dialog_singlechoice);
                break;
            case "Material":
                t.setText("Material");
                eqa = ArrayAdapter.createFromResource(AddInsufficiency.this,R.array.Materials, android.R.layout.select_dialog_singlechoice);
                break;
        }
        eq.setThreshold(1);
        eq.setAdapter(eqa);
        unit.setThreshold(1);
        unit.setAdapter(unia);
        try{
            unit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    unit.showDropDown();
                }
            });
        }
        catch (Exception e){
            Log.e("PMUList: ", e.toString() );
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Insuf ob = new Insuf();
                ob.setItem(eq.getText().toString().trim());
                ob.setRo(ro.getText().toString().trim());
                if(pmu.getText().toString().isEmpty()){
                    ob.setPmu(" ");
                }
                else{
                    ob.setPmu(pmu.getText().toString().trim());
                }
                if(loc.getText().toString().isEmpty()){
                    ob.setLoc(" ");
                }
                else{
                    ob.setLoc(loc.getText().toString().trim());
                }
                ob.setUni(Unit.valueOf(unit.getText().toString().trim()));
                ob.setRequired(Integer.parseInt(quant.getText().toString().trim()));
                Ref.document(String.valueOf(System.currentTimeMillis())).set(ob).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "DocumentSnapshot successfully updated!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Error updating insufficiency", e.toString());
                    }
                });
                MainActivity.pmu="";
                pmu.setText("");
                MainActivity.location="";
                loc.setText("");
                Intent i = new Intent(AddInsufficiency.this, Equipments.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.pmu="";
        pmu.setText("");
        MainActivity.location="";
        loc.setText("");
    }
}
