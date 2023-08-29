package com.example.premonsoonaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Add_Equipment extends AppCompatActivity {
    Spinner pmu;
    TextView t;
    ArrayAdapter<CharSequence> ad;
    private CollectionReference Ref;
    EditText t1,t2;
    String name,n,loc;
    Button save;
    Query querya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);
       // t=findViewById(R.id.notext);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        t1=findViewById(R.id.Type);
        t2=findViewById(R.id.no);
        save=findViewById(R.id.save);
        save.setEnabled(false);
        this.setTitle("Add "+Equipments.eqt);
        switch(Action.selectedAction){
            case "Equipment":
                t2.setHint("No.");
                Ref = db.collection("equipments");
                break;
            case "Material":
                t2.setHint("Quantity.");
               // t.setText();
                Ref = db.collection("materials");
                break;
            case "Rate running":
                t2.setHint("No.");
                Ref = db.collection("rate running contracts");
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
        pmu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loc=(String) adapterView.getItemAtPosition(i);
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
        t1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int i, int i1, int i2) {
                if(text.toString().trim().length()==0){
                    t1.setError("This field cannot be empty");
                    save.setEnabled(false);
                }
                else if (t2.getText().toString().trim().length()==0) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                if(text.toString().trim().length()==0){
                    t1.setError("This field cannot be empty");
                    save.setEnabled(false);
                }
                else if (t2.getText().toString().trim().length()==0) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(t1.getText().toString().trim().length()==0){
                    t1.setError("This field cannot be empty");
                    save.setEnabled(false);
                }
                else if (t2.getText().toString().trim().length()==0) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });
        t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int i, int i1, int i2) {
                if(text.toString().trim().length()==0){
                    t2.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if ((!text.toString().contains("0"))&&(!text.toString().contains("1"))&&(!text.toString().contains("2"))&&(!text.toString().contains("3"))&&(!text.toString().contains("4"))&&(!text.toString().contains("5"))&&(!text.toString().contains("6"))&&(!text.toString().contains("7"))&&(!text.toString().contains("8"))&&(!text.toString().contains("9"))) {
                    t2.setError("This field must contain at least 1 digit");
                    save.setEnabled(false);
                } else if (t1.getText().toString().trim().length()==0) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                if(text.toString().trim().length()==0){
                    t2.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if ((!text.toString().contains("0"))&&(!text.toString().contains("1"))&&(!text.toString().contains("2"))&&(!text.toString().contains("3"))&&(!text.toString().contains("4"))&&(!text.toString().contains("5"))&&(!text.toString().contains("6"))&&(!text.toString().contains("7"))&&(!text.toString().contains("8"))&&(!text.toString().contains("9"))) {
                    t2.setError("This field must contain at least 1 digit");
                    save.setEnabled(false);
                } else if (t1.getText().toString().trim().length()==0) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(t2.getText().toString().trim().length()==0){
                    t2.setError("This field cannot be empty");
                    save.setEnabled(false);
                } else if ((!t2.getText().toString().contains("0"))&&(!t2.getText().toString().contains("1"))&&(!t2.getText().toString().contains("2"))&&(!t2.getText().toString().contains("3"))&&(!t2.getText().toString().contains("4"))&&(!t2.getText().toString().contains("5"))&&(!t2.getText().toString().contains("6"))&&(!t2.getText().toString().contains("7"))&&(!t2.getText().toString().contains("8"))&&(!t2.getText().toString().contains("9"))) {
                    t2.setError("This field must contain at least 1 digit");
                    save.setEnabled(false);
                } else if (t1.getText().toString().trim().length()==0) {
                    save.setEnabled(false);
                }
                else{
                    save.setEnabled(true);
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=t1.getText().toString();
                n=t2.getText().toString();
                Map<String,String> m=new HashMap<String,String>();
                m.put("name",name);
                m.put("no",n);
                m.put("pmu",loc);
                querya = Ref.whereEqualTo("NAME",name).whereEqualTo("PMU",loc);
                querya.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>(){
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            Ref.document(loc+name).set(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void v) {
                                    Log.d("TAG", "DocumentSnapshot successfully updated!");
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("Error updating details", e.toString());
                                    finish();
                                }
                            });
                        }
                        else{
                            Toast.makeText(Add_Equipment.this, "This "+Action.selectedAction+" for "+loc+"already exists. You may edit that data.", Toast.LENGTH_SHORT).show();
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {            @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("mainActivity ", "inquery");
                    Log.e("tage",e.toString());
                }
                });
            }
        });
    }
}