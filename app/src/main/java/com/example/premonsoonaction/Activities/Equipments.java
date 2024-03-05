package com.example.premonsoonaction.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.premonsoonaction.AdapterClasses.MaterialAdapter;
import com.example.premonsoonaction.AdapterClasses.MaterialAdapter2;
import com.example.premonsoonaction.AdapterClasses.RateAdapter;
import com.example.premonsoonaction.Models.ModelEquipment;
import com.example.premonsoonaction.Models.PmuNo;
import com.example.premonsoonaction.Models.RateModel;
import com.example.premonsoonaction.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Equipments extends AppCompatActivity {
    FloatingActionButton add;
    private FirebaseFirestore db;
    Button export;
    public static CollectionReference Ref;
    Query querya;
    RecyclerView recycler,recyclerPMUwise;
    MaterialAdapter2 adapt;
    RateAdapter adr;
    public static ArrayList<ModelEquipment> list;
    public static ArrayList<RateModel> lr;
    TextView t;
    EditText filter;
    public static String eqt;
    Date s,e;
    Map<String,Integer> eqwithcount;
    public static Map<Pair<String,String>, Integer> pmuwithcount;
    List<PmuNo> eqlist,pmuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);
        db = FirebaseFirestore.getInstance();
        add=findViewById(R.id.add);
        filter=findViewById(R.id.filter);
        t=findViewById(R.id.No);
        list = new ArrayList<>();
        lr = new ArrayList<>();
        eqlist = new ArrayList<>();
        pmuList = new ArrayList<>();
        export=findViewById(R.id.export);
        /*Calendar c1 = Calendar.getInstance();
        c1.set(1998,7,28);
        s = c1.getTime();
        Calendar c2 = Calendar.getInstance();
        c2.set(1998,7,28);
        e = c2.getTime();
        lr.add(new RateModel("Type1", 123456, "Address1",s,e, "email1@example.com", "123456789", "Name1", "Details1"));
        lr.add(new RateModel("Type2", 789101, "Address2",s,e, "email2@example.com", "223456789", "Name2", "Details2"));
        lr.add(new RateModel("Type3", 112131, "Address3",s,e, "email3@example.com", "323456789", "Name3", "Details3"));*/
        adapt=new MaterialAdapter2(this,eqlist);
        adr=new RateAdapter(this,lr);
        recycler = findViewById(R.id.SearchByDesignation);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        /*for(int i=0;i<list.size();i++){
            Log.e( "onCreathuyge: ",list.get(i).getName() );
        }*/
        if(Action.selectedAction.equals("Rate running")){
            try{
                recycler.setAdapter(adr);
            }
            catch(Exception e){
                Log.e("set adr: ",e.toString());
            }
        }
        else{
            recycler.setAdapter(adapt);
        }
        //recyler for PMUwise split currently only displays list as in equipment list, it has to be changed while working on backend.
        if(MainActivity.HQ){
            add.setVisibility(View.INVISIBLE);
        }
        else{
            add.setVisibility(View.VISIBLE);
        }
        switch(Action.selectedAction){
            case "Equipment":
                t.setText("No.");
                this.setTitle("Equipments");
                eqt="Equipment";
                Ref = db.collection("equipments");
                try{
                    querya=Ref.whereEqualTo("ro",MainActivity.RO).whereNotEqualTo("location","malahehehe").orderBy("location").orderBy("name").orderBy("pmu");
                }
                catch(Exception e){
                    Log.e("eqQuery: ",e.toString());
                }
                break;
            case "Material":
                t.setText("Quantity.");
                this.setTitle("Materials");
                eqt="Material";
                Ref = db.collection("materials");
                try{
                    querya=Ref.whereEqualTo("ro",MainActivity.RO).whereNotEqualTo("location","malahehehe").orderBy("location").orderBy("name").orderBy("pmu");
                }
                catch(Exception e){
                    Log.e("eqQuery: ",e.toString());
                }
                break;
            case "Rate running":
                //t.setText("No.");
                this.setTitle("Rate Running Contracts");
                eqt="Rate Running Contract";
                Ref = db.collection("rate running contracts");
                querya=Ref.whereEqualTo("ro",MainActivity.RO);
                break;
        }
        //r2=db.collection("pmuno");
        //queryb=r2.orderBy("pmu");
        if(eqt.equals("Rate Running Contract")){
            querya.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot dc: task.getResult()) {
                            try {
                                RateModel ob = dc.toObject(RateModel.class);
                                lr.add(ob);
                                Log.d("RateModel obj: ",ob.getPmis()+" "+ob.getAddress()+" "+ob.getStart()+" "+ob.getEnd()+" "+ob.getType()+" "+ob.getName()+" "+ob.getMobile()+" "+ob.getEmail()+" "+ob.getDetails()+" "+ob.getRo()+"\n");
                            }
                            catch(Exception e){
                                Log.e("onComplete rate running: ",e.toString());
                            }
                        }
                    }
                    else{
                        Log.d("Error rate: ",task.getException().toString());
                    }
                    try {
                        adr.notifyDataSetChanged();
                    }
                    catch(Exception e){
                        Log.e("onComplete rate running: ",e.toString());
                    }
                }
            });
        }
        else{
            eqwithcount = new HashMap<>();
            pmuwithcount = new HashMap<>();
            querya.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot dc: task.getResult()) {
                            ModelEquipment ob = dc.toObject(ModelEquipment.class);
                            if(eqwithcount.containsKey(ob.getName())){
                                eqwithcount.put(ob.getName(),eqwithcount.get(ob.getName())+ob.getNo());
                            }
                            else{
                                eqwithcount.put(ob.getName(),ob.getNo());
                            }
                            Pair<String,String> p=new Pair<>(ob.getName(),ob.getPmu());
                            if(pmuwithcount.containsKey(p)){
                                pmuwithcount.put(p,eqwithcount.get(p)+ob.getNo());
                                adapt.notifyDataSetChanged();
                            }
                            else{
                                pmuwithcount.put(p,ob.getNo());
                                adapt.notifyDataSetChanged();
                            }
                            try{
                                list.add(ob);
                            }
                            catch(Exception e){
                                Log.e( "list.add error: ",e.toString());
                            }
                        }
                        for(Map.Entry<String, Integer> me : eqwithcount.entrySet()){
                            PmuNo ob = new PmuNo(me.getKey(),me.getValue());
                            eqlist.add(ob);
                            adapt.notifyDataSetChanged();
                        }
                        Log.d("pmuwithcount size: ",Integer.toString(pmuwithcount.size()));
                        /*for(Map.Entry<Pair<String,String>, Integer> me : pmuwithcount.entrySet()){
                            PmuNo ob = new PmuNo(me.getKey().second,me.getValue());
                            Log.d("pmuwithcount size: ",Integer.toString(pmuwithcount.getValue()));
                            adapt.notifyDataSetChanged();
                        }*/
                    }
                    else{
                        Log.d("Error eq: ",task.getException().toString());
                    }
                }
            });
            export.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
                    HSSFSheet hssfSheet = hssfWorkbook.createSheet("MySheet");
                    HSSFRow hssfRow0 = hssfSheet.createRow(0);
                    int j=0;
                    HSSFCell cell1 = hssfRow0.createCell(j++);
                    cell1.setCellValue("RO");
                    HSSFCell cell3 = hssfRow0.createCell(j++);
                    cell3.setCellValue(getString(R.string.type));
                    HSSFCell cell4 = hssfRow0.createCell(j++);
                    cell4.setCellValue(getString(R.string.no));
                    HSSFCell cell5 = hssfRow0.createCell(j++);
                    cell5.setCellValue(getString(R.string.ro));
                    HSSFCell cell6 = hssfRow0.createCell(j++);
                    cell6.setCellValue(getString(R.string.pmu));
                    HSSFCell cell7 = hssfRow0.createCell(j++);
                    cell7.setCellValue(getString(R.string.location));
                    HSSFCell cell8 = hssfRow0.createCell(j++);
                    cell8.setCellValue("Unit");
                    HSSFCell cell9 = hssfRow0.createCell(j++);
                    cell9.setCellValue("Is Item insufficient");
                    HSSFCell cell10 = hssfRow0.createCell(j++);
                    cell10.setCellValue("Required amount");
                    HSSFCell cell11 = hssfRow0.createCell(j++);
                    cell11.setCellValue("Required amount unit");
                    for (int i = 1; i<=list.size(); i++){
                        HSSFRow hssfRow = hssfSheet.createRow(i);
                        int k=0;
                        HSSFCell hssfCell = hssfRow.createCell(k++);
                        hssfCell.setCellValue(MainActivity.RO);
                        HSSFCell hssfCell2 = hssfRow.createCell(k++);
                        hssfCell2.setCellValue(list.get(i-1).getName().toString());
                        HSSFCell hssfCell3 = hssfRow.createCell(k++);
                        hssfCell3.setCellValue(list.get(i-1).getNo());
                        HSSFCell hssfCell4 = hssfRow.createCell(k++);
                        hssfCell4.setCellValue(list.get(i-1).getRo());
                        HSSFCell hssfCell5 = hssfRow.createCell(k++);
                        hssfCell5.setCellValue(list.get(i-1).getPmu());
                        HSSFCell hssfCell6 = hssfRow.createCell(k++);
                        hssfCell6.setCellValue(list.get(i-1).getLocation());
                        HSSFCell hssfCell7 = hssfRow.createCell(k++);
                        if(list.get(i-1).getUnit()==null){
                            hssfCell7.setCellValue("");
                        }
                        else{
                            hssfCell7.setCellValue(list.get(i-1).getUnit().toString());
                        }
                        HSSFCell hssfCell8 = hssfRow.createCell(k++);
                        if(list.get(i-1).getIsInsuf()==null){
                            hssfCell8.setCellValue("");
                        }
                        else{
                            hssfCell8.setCellValue(list.get(i-1).getIsInsuf());
                        }
                        HSSFCell hssfCell9 = hssfRow.createCell(k++);
                        hssfCell9.setCellValue(list.get(i-1).getInsuf());
                        HSSFCell hssfCell10 = hssfRow.createCell(k++);
                        hssfCell10.setCellValue(list.get(i-1).getInsufUnit().toString());
                    }
                    saveWorkBook(hssfWorkbook);
                }
            });

        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                if(eqt.equals("Rate Running Contract")){
                    i=new Intent(Equipments.this, Add_RateRunning.class);
                }
                else{
                    i=new Intent(Equipments.this, Add_Equipment.class);
                }
                startActivity(i);
            }
        });

    }
    private void saveWorkBook(HSSFWorkbook hssfWorkbook){
        StorageManager storageManager = (StorageManager) getSystemService(STORAGE_SERVICE);


        StorageVolume storageVolume = storageManager.getStorageVolumes().get(0); // internal storage

        File fileOutput = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            //Objects.requireNonNull(storageVolume.getDirectory()).ge
            fileOutput = new File(Objects.requireNonNull(storageVolume.getDirectory()).getPath() +"/Download","Equipments.xls");
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileOutput);
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
            hssfWorkbook.close();
            Toast.makeText(this, "File Created Successfully", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, "File Creation Failed", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
        /*filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                s = s.toString().toLowerCase();

                filtered = new ArrayList<>();

                for (int j = 0; j < list.size(); j++) {
                    final String text1 = list.get(j).getPmu().toLowerCase();
                    final String text2 = list.get(j).getName().toLowerCase();
                    if (text1.contains(s) || text2.contains(s)) {
                        filtered.add(list.get(j));
                    }
                    adapt.filterList(filtered);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });*/
    }
   /* @Override
    protected void onResume() {
        super.onResume();
        if(eqt.equals("Rate Running Contract")){
            querya.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot dc: task.getResult()) {
                            try {
                                RateModel ob = dc.toObject(RateModel.class);
                                lr.add(ob);
                                Log.d("RateModel obj: ",ob.getPmis()+" "+ob.getAddress()+" "+ob.getStart()+" "+ob.getEnd()+" "+ob.getType()+" "+ob.getName()+" "+ob.getMobile()+" "+ob.getEmail()+" "+ob.getDetails()+" "+ob.getRo()+"\n");
                            }
                            catch(Exception e){
                                Log.e("onComplete rate running: ",e.toString());
                            }
                        }
                    }
                    else{
                        Log.d("Error rate: ",task.getException().toString());
                    }
                    try {
                        adr.notifyDataSetChanged();
                    }
                    catch(Exception e){
                        Log.e("onComplete rate running: ",e.toString());
                    }
                }
            });
        }
        else{
            eqwithcount = new HashMap<>();
            pmuwithcount = new HashMap<>();
            querya.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot dc: task.getResult()) {
                            ModelEquipment ob = dc.toObject(ModelEquipment.class);
                            if(eqwithcount.containsKey(ob.getName())){
                                eqwithcount.put(ob.getName(),eqwithcount.get(ob.getName())+ob.getNo());
                            }
                            else{
                                eqwithcount.put(ob.getName(),ob.getNo());
                            }
                            Pair<String,String> p=new Pair<>(ob.getName(),ob.getPmu());
                            if(pmuwithcount.containsKey(p)){
                                pmuwithcount.put(p,eqwithcount.get(p)+ob.getNo());
                                adapt.notifyDataSetChanged();
                            }
                            else{
                                pmuwithcount.put(p,ob.getNo());
                                adapt.notifyDataSetChanged();
                            }
                            try{
                                list.add(ob);
                            }
                            catch(Exception e){
                                Log.e( "list.add error: ",e.toString());
                            }
                        }
                        for(Map.Entry<String, Integer> me : eqwithcount.entrySet()){
                            PmuNo ob = new PmuNo(me.getKey(),me.getValue());
                            eqlist.add(ob);
                            adapt.notifyDataSetChanged();
                        }
                        Log.d("pmuwithcount size: ",Integer.toString(pmuwithcount.size()));
                        *//*for(Map.Entry<Pair<String,String>, Integer> me : pmuwithcount.entrySet()){
                            PmuNo ob = new PmuNo(me.getKey().second,me.getValue());
                            Log.d("pmuwithcount size: ",Integer.toString(pmuwithcount.getValue()));
                            adapt.notifyDataSetChanged();
                        }*//*
                    }
                    else{
                        Log.d("Error eq: ",task.getException().toString());
                    }
                }
            });

        }
    }*/
}