package com.example.premonsoonaction.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
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
import com.example.premonsoonaction.Models.Unit;
import com.example.premonsoonaction.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Add_Equipment extends AppCompatActivity {
    //Spinner pmu;
    EditText no,site;
    AutoCompleteTextView eq,unit;
    private FirebaseFirestore db;
    AutoCompleteTextView pmu;
    ArrayAdapter ad,unia;
    Map<String,Boolean> m;
    ArrayAdapter<String> ad1;
    private CollectionReference Ref;
    Button save;
    Query querya;
    List<String> eqtypes;
    String typeCollection;
    Unit du;
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);
        db = FirebaseFirestore.getInstance();
        //Declarations
        switch(Action.selectedAction){
            case "Equipment":
                this.setTitle("Add Equipments");
                try{
                    Ref = db.collection("equipments");
                }
                catch(Exception e){
                    Log.e( "Setting eq collection reference: ",e.toString());
                }
                typeCollection="equipmentTypes";
                du=Unit.units;
                break;
            case "Material":
                this.setTitle("Add Materials");
                Ref = db.collection("materials");
                typeCollection="materialTypes";
                du=Unit.Kg;
                break;
        }
        eq = (AutoCompleteTextView)findViewById(R.id.Type);
        no = findViewById(R.id.no);
        site = findViewById(R.id.site);
        pmu = (AutoCompleteTextView) findViewById(R.id.pmu);
        save = findViewById(R.id.save);
        unit = (AutoCompleteTextView)findViewById(R.id.unit);

        //set eq type
        try{
            m = new HashMap<>();
            eqtypes=new ArrayList<>();
            db.collection(typeCollection).orderBy("type").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        m.put(Objects.requireNonNull(doc.getData().get("type")).toString().trim(),true);
                        eqtypes.add(Objects.requireNonNull(doc.getData().get("type")).toString().trim());
                        ad1.notifyDataSetChanged();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("onFailure eqtypes: ", e.toString());
                }
            });
        }
        catch(Exception e){
            Log.e("get eqtypes: ",e.toString());
        }

        try{
            ad1=new ArrayAdapter<String>(Add_Equipment.this,android.R.layout.select_dialog_singlechoice,eqtypes);
        }
        catch(Exception e){
            Log.e("ad1: ",e.toString());
        }
        eq.setThreshold(1);
        eq.setAdapter(ad1);
        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    eq.showDropDown();
                }
                catch(Exception e){
                    Log.e("show dropdown: ",e.toString() );
                }
            }
        });
        //set unit
        unia = ArrayAdapter.createFromResource(Add_Equipment.this, R.array.Units, android.R.layout.select_dialog_singlechoice);
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
        //set PMU
        switch(MainActivity.RO){
            case"Ro-Leh/Srinagar":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.LehSrinagar, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Shillong":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Shillong, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-LADAKH":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.LADAKH, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Kohima":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Kohima, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Jammu":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Jammu, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Itanagar":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Itanagar, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Imphal":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Imphal, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Guwahati":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Guwahati, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Gangtok":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Gangtok, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Dehradun":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Dehradun, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Aizwal":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Aizwal, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Agartala":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.Agartala, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-Port Blair":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.PortBlair, android.R.layout.select_dialog_singlechoice);
                break;
            case"RO-SRINAGAR":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.SRINAGAR, android.R.layout.select_dialog_singlechoice);
                break;
            case"New Delhi":
                ad=ArrayAdapter.createFromResource(Add_Equipment.this,R.array.NewDelhi, android.R.layout.select_dialog_singlechoice);
                break;
        }
        eq.setThreshold(1);
        pmu.setAdapter(ad);
        pmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pmu.showDropDown();
            }
        });

        //set save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelEquipment ob = new ModelEquipment();
                ob.setName(eq.getText().toString().trim());
                ob.setPmu(pmu.getText().toString().trim());
                ob.setRo(MainActivity.RO);
                ob.setLocation(site.getText().toString().trim());
                ob.setInsuf(0);
                ob.setNo(Integer.parseInt(no.getText().toString().trim()));
                ob.setUnit(Unit.valueOf(unit.getText().toString().trim()));
                ob.setInsufUnit(Unit.valueOf(unit.getText().toString().trim()));
                ob.setIsInsuf(false);
                Log.d( "isInsuf: ",ob.getIsInsuf().toString());
                if(!m.containsKey(eq.getText().toString().trim())){
                    Map<String,String> m1 = new HashMap<>();
                    m1.put("type",eq.getText().toString().trim());
                    db.collection(typeCollection).document(eq.getText().toString().trim()).set(m1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.e( "onSuccess rate type: ", "added successfully");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e( "onFailure rate type: ", e.toString());
                        }
                    });
                }
                querya = Ref.whereEqualTo("location", ob.getLocation()).whereEqualTo("name", ob.getName());
                querya.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            Ref.document(ob.getLocation()+" "+ob.getName()).set(ob).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG", "DocumentSnapshot successfully updated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("Error updating details", e.toString());
                                }
                            });
                        }
                        else {
                            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                                int x,y;
                                Unit z;
                                ModelEquipment ob2;
                                try{
                                    ob2 = doc.toObject(ModelEquipment.class);
                                    x=ob2.getNo();
                                    y=(Integer) ob2.getInsuf();
                                    z=(Unit) ob2.getInsufUnit();
                                    ob.setNo(ob.getNo()+x);
                                    ob.setInsuf(y);
                                    ob.setIsInsuf(ob.getNo()<ob.getInsuf()?true:false);
                                    ob.setInsufUnit(z);
                                }
                                catch(Exception e){
                                    Log.e("get no,insuf,unit: ", e.toString());
                                }
                            }
                            Ref.document(ob.getLocation()+" "+ob.getName()).set(ob).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG", "DocumentSnapshot successfully updated!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("Error updating details", e.toString());
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener()                                                               {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("mainActivity ", "inquery");
                        //Log.e("tage", e.toString());
                    }
                });
                Intent i = new Intent(Add_Equipment.this, Equipments.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
    }
}