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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.premonsoonaction.AdapterClasses.MaterialAdapter2;
import com.example.premonsoonaction.AdapterClasses.RateAdapter;
import com.example.premonsoonaction.Models.ModelEquipment;
import com.example.premonsoonaction.Models.PmuNo;
import com.example.premonsoonaction.Models.RateModel;
import com.example.premonsoonaction.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
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
    RecyclerView recycler;
    MaterialAdapter2 adapt,pmuwiseadapt;
    RateAdapter adr;
    public static ArrayList<ModelEquipment> list;
    public static ArrayList<RateModel> lr;
    TextView t,itemwise,pmuwise;
    EditText filter;
    public static String eqt;
    Map<String,Integer> eqwithcount,pmuwisecount;
    public static Map<Pair<String,String>, Integer> pmuwithcount;
    public static String eq;
    List<PmuNo> eqlist,pmuwiseList;
    SwitchMaterial sw;
    public static boolean switchValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);
        db = FirebaseFirestore.getInstance();
        add=findViewById(R.id.add);
        filter=findViewById(R.id.filter);
        t=findViewById(R.id.No);
        sw = findViewById(R.id.toggle);
        itemwise=findViewById(R.id.itemview);
        pmuwise=findViewById(R.id.pmuview);
        list = new ArrayList<>();
        lr = new ArrayList<>();
        eqlist = new ArrayList<>();
        pmuwiseList = new ArrayList<>();
        export=findViewById(R.id.export);
        switchValue=false;
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                s = s.toString().toLowerCase();
                if (eqt.equals("RateRunning")) {
                    ArrayList<RateModel> filtered = new ArrayList<>();
                    for (int j = 0; j < lr.size(); j++) {
                        try {
                            Log.d("Type + start +: ", s + " " + j + " " + lr.get(j).getType() + " " + lr.get(j).getType().contains(s));
                            if (lr.get(j).getType().toLowerCase().contains(s) || Integer.toString(lr.get(j).getPmis()).contains(s) || lr.get(j).getAddress().toLowerCase().contains(s) || Add_RateRunning.DATE_FORMAT.format(lr.get(j).getStart()).contains(s) || Add_RateRunning.DATE_FORMAT.format(lr.get(j).getEnd()).contains(s) || lr.get(j).getName().toLowerCase().contains(s) || lr.get(j).getMobile().toLowerCase().contains(s) || lr.get(j).getEmail().contains(s) || lr.get(j).getDetails().toLowerCase().contains(s)) {
                                filtered.add(lr.get(j));
                            }
                        } catch (Exception e) {
                            Log.e("filter error: ", e.toString());
                        }
                        adr.filterList(filtered);
                    }
                } else {
                    ArrayList<PmuNo> filtered = new ArrayList<>();
                    if (switchValue) {
                        for (int j = 0; j < pmuwiseList.size(); j++) {
                            try {
                                if (pmuwiseList.get(j).getPMU().toLowerCase().contains(s)) {
                                    filtered.add(pmuwiseList.get(j));
                                }
                            } catch (Exception e) {
                                Log.e("filter error: ", e.toString());
                            }
                            pmuwiseadapt.filterList(filtered);
                        }
                    }
                    else{
                        for (int j = 0; j < eqlist.size(); j++) {
                            try {
                                if (eqlist.get(j).getPMU().toLowerCase().contains(s)) {
                                    filtered.add(eqlist.get(j));
                                }
                            } catch (Exception e) {
                                Log.e("filter error: ", e.toString());
                            }
                            adapt.filterList(filtered);
                        }
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        adapt=new MaterialAdapter2(this,eqlist);
        pmuwiseadapt=new MaterialAdapter2(this,pmuwiseList);
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
                eqt="RateRunning";
                Ref = db.collection("rate running contracts");
                querya=Ref.whereEqualTo("ro",MainActivity.RO);
                break;
        }
        if(eqt.equals("RateRunning")){
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
                    cell4.setCellValue(getString(R.string.pmis_id));
                    HSSFCell cell5 = hssfRow0.createCell(j++);
                    cell5.setCellValue(getString(R.string.location));
                    HSSFCell cell6 = hssfRow0.createCell(j++);
                    cell6.setCellValue(getString(R.string.start_date));
                    HSSFCell cell7 = hssfRow0.createCell(j++);
                    cell7.setCellValue(getString(R.string.end_date));
                    HSSFCell cell8 = hssfRow0.createCell(j++);
                    cell8.setCellValue(getString(R.string.contractor_name));
                    HSSFCell cell9 = hssfRow0.createCell(j++);
                    cell9.setCellValue(getString(R.string.contractor_mail_id));
                    HSSFCell cell10 = hssfRow0.createCell(j++);
                    cell10.setCellValue(getString(R.string.contractor_number));
                    HSSFCell cell11 = hssfRow0.createCell(j++);
                    cell11.setCellValue(getString(R.string.essential_details));
                    for (int i = 1; i<=lr.size(); i++){
                        HSSFRow hssfRow = hssfSheet.createRow(i);
                        int k=0;
                        HSSFCell hssfCell = hssfRow.createCell(k++);
                        hssfCell.setCellValue(MainActivity.RO);
                        HSSFCell hssfCell2 = hssfRow.createCell(k++);
                        hssfCell2.setCellValue(lr.get(i-1).getType().toString());
                        HSSFCell hssfCell3 = hssfRow.createCell(k++);
                        hssfCell3.setCellValue(lr.get(i-1).getPmis());
                        HSSFCell hssfCell4 = hssfRow.createCell(k++);
                        hssfCell4.setCellValue(lr.get(i-1).getAddress());
                        HSSFCell hssfCell5 = hssfRow.createCell(k++);
                        if(lr.get(i-1).getName()==null){
                            hssfCell5.setCellValue("");
                        }
                        else{
                            hssfCell5.setCellValue(lr.get(i-1).getStart().toString());
                        }
                        HSSFCell hssfCell6 = hssfRow.createCell(k++);
                        if(lr.get(i-1).getName()==null){
                            hssfCell6.setCellValue("");
                        }
                        else{
                            hssfCell6.setCellValue(lr.get(i-1).getEnd().toString());
                        }
                        HSSFCell hssfCell7 = hssfRow.createCell(k++);
                        if(lr.get(i-1).getName()==null){
                            hssfCell7.setCellValue("");
                        }
                        else{
                            hssfCell7.setCellValue(lr.get(i-1).getName().toString());
                        }
                        HSSFCell hssfCell8 = hssfRow.createCell(k++);
                        if(lr.get(i-1).getEmail()==null){
                            hssfCell8.setCellValue("");
                        }
                        else{
                            hssfCell8.setCellValue(lr.get(i-1).getEmail());
                        }
                        HSSFCell hssfCell9 = hssfRow.createCell(k++);
                        hssfCell9.setCellValue(lr.get(i-1).getMobile());
                        HSSFCell hssfCell10 = hssfRow.createCell(k++);
                        hssfCell10.setCellValue(lr.get(i-1).getDetails());
                    }
                    saveWorkBook(hssfWorkbook);
                }
            });

        }
        else{
            sw.setVisibility(View.VISIBLE);
            itemwise.setVisibility(View.VISIBLE);
            sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        pmuwise.setVisibility(View.VISIBLE);
                        itemwise.setVisibility(View.GONE);
                        recycler.setAdapter(pmuwiseadapt);
                        eq="";
                    }
                    else{
                        itemwise.setVisibility(View.VISIBLE);
                        pmuwise.setVisibility(View.GONE);
                        recycler.setAdapter(adapt);
                    }
                    switchValue = b;
                }
            });
            eqwithcount = new HashMap<>();
            pmuwithcount = new HashMap<>();
            pmuwisecount = new HashMap<>();
            querya.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for (DocumentSnapshot dc: task.getResult()) {
                            ModelEquipment ob = dc.toObject(ModelEquipment.class);
                            //creating equipment:no map
                            assert ob != null;
                            if(eqwithcount.containsKey(ob.getName())){
                                eqwithcount.put(ob.getName(),eqwithcount.get(ob.getName())+ob.getNo());
                            }
                            else{
                                eqwithcount.put(ob.getName(),ob.getNo());
                            }
                            //creating pmu:no map
                            if(pmuwisecount.containsKey(ob.getPmu())){
                                pmuwisecount.put(ob.getPmu(),pmuwisecount.get(ob.getPmu())+ob.getNo());
                            }
                            else{
                                pmuwisecount.put(ob.getPmu(),ob.getNo());
                            }
                            Pair<String,String> p=new Pair<>(ob.getName(),ob.getPmu());
                            if(pmuwithcount.containsKey(p)){
                                pmuwithcount.put(p,eqwithcount.get(p)+ob.getNo());
                            }
                            else{
                                pmuwithcount.put(p,ob.getNo());
                            }
                            try{
                                list.add(ob);
                            }
                            catch(Exception e){
                                Log.e( "list.add error: ",e.toString());
                            }
                        }
                        for(Map.Entry<String, Integer> me : pmuwisecount.entrySet()){
                            PmuNo ob = new PmuNo(me.getKey(),me.getValue());
                            pmuwiseList.add(ob);
                            pmuwiseadapt.notifyDataSetChanged();
                        }
                        Log.d("pmuwithcount size: ",Integer.toString(pmuwithcount.size()));
                        for(Map.Entry<String, Integer> me : eqwithcount.entrySet()){
                            PmuNo ob = new PmuNo(me.getKey(),me.getValue());
                            eqlist.add(ob);
                            adapt.notifyDataSetChanged();
                        }
                        Log.d("pmuwithcount size: ",Integer.toString(pmuwithcount.size()));
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
                if(eqt.equals("RateRunning")){
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
            fileOutput = new File(Objects.requireNonNull(storageVolume.getDirectory()).getPath() +"/Download/",eqt+String.valueOf(System.currentTimeMillis())+".xls");
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

    }

}