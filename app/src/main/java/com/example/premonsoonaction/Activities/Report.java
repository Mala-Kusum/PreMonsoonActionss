package com.example.premonsoonaction.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.premonsoonaction.AdapterClasses.ReportAdapter;
import com.example.premonsoonaction.DatePick;
import com.example.premonsoonaction.Models.reportGetModel;
import com.example.premonsoonaction.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Report extends AppCompatActivity {
    FloatingActionButton button;
    Button export;
    ImageButton from,to;
    public static Date f,t;
    RecyclerView r;
    public static List<reportGetModel> l,filtered;

    private FirebaseFirestore db;
    CollectionReference Ref;
    public static ReportAdapter ad;
    TextView reset;
    public static boolean b1,b2;

    Query q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        db=FirebaseFirestore.getInstance();
        b1=false;
        b2=false;
        Ref = db.collection("checklist");
        l=new ArrayList<>();
        filtered=new ArrayList<>();
        reset=findViewById(R.id.reset);
        ad=new ReportAdapter(Report.this, (ArrayList<reportGetModel>) l);
        r=findViewById(R.id.reportList);
        button=findViewById(R.id.addrepo);
        from=findViewById(R.id.From);
        to=findViewById(R.id.To);
        export=findViewById(R.id.export);
        r.setHasFixedSize(true);
        r.setLayoutManager(new LinearLayoutManager(this));
        r.setAdapter(ad);
        //q=Ref.whereEqualTo("ro",MainActivity.RO).orderBy("submitted", Query.Direction.DESCENDING);
        q=Ref.whereEqualTo("ro",MainActivity.RO).orderBy("submitted", Query.Direction.DESCENDING);
        if(MainActivity.HQ){
            button.setVisibility(View.INVISIBLE);
        }
        else{
            button.setVisibility(View.VISIBLE);
        }
        q.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore Error", error.getMessage());
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        reportGetModel ob = new reportGetModel();
                        try{
                            Timestamp t=(Timestamp) dc.getDocument().get("submitted");
                            ob.setDate(t.toDate());
                            System.out.println("date,"+t.toDate());
                            ob.setDocid(dc.getDocument().getId());
                            ob.setinst1((boolean) dc.getDocument().get("inst1"));
                            ob.setinst2((boolean) dc.getDocument().get("inst2"));
                            ob.setinst3((boolean) dc.getDocument().get("inst3"));
                            ob.setinst4((boolean) dc.getDocument().get("inst4"));
                            ob.setinst5((boolean) dc.getDocument().get("inst5"));
                            ob.setinst6((boolean) dc.getDocument().get("inst6"));
                            ob.setinst7((boolean) dc.getDocument().get("inst7"));
                            ob.setinst8((boolean) dc.getDocument().get("inst8"));
                            ob.setinst9((boolean) dc.getDocument().get("inst9"));
                            ob.setinst10((boolean) dc.getDocument().get("inst10"));
                            ob.setinst11((boolean) dc.getDocument().get("inst11"));
                            ob.setRO(dc.getDocument().get("ro").toString());
                            l.add(ob);
                        }
                        catch(Exception e){
                            Log.e("getTimeE", e.toString());
                        }
                        System.out.println("sssssssssssssssssssssssssssssssss        "+l);
                        ad.notifyDataSetChanged();
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Report.this, addReport.class);
                startActivity(i);
            }
        });
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePick(true);
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePick(false);
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtered.clear();
                ad.filterList((ArrayList<reportGetModel>) l);
            }
        });
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
                HSSFSheet hssfSheet = hssfWorkbook.createSheet("MySheet");
                HSSFRow hssfRow = hssfSheet.createRow(0);
                int j=0;
                HSSFCell hssfCell1 = hssfRow.createCell(j++);
                hssfCell1.setCellValue("RO");
                HSSFCell hssfCell2 = hssfRow.createCell(j++);
                hssfCell2.setCellValue("Timestamp");
                HSSFCell hssfCell3 = hssfRow.createCell(j++);
                hssfCell3.setCellValue(R.string.cleaning_of_drains_culverts);
                HSSFCell hssfCell4 = hssfRow.createCell(j++);
                hssfCell4.setCellValue(R.string.keeping_in_stock_on_offer_jcbs_saw_cutters_for_tree_cutting_sand_bags_traffic_cones_amp_signages_etc_in_required_quantities_at_required_locations_is_ensured_and_the_details_there_of_entered_in_the_google_worksheet);
                HSSFCell hssfCell5 = hssfRow.createCell(j++);
                hssfCell1.setCellValue(R.string.rate_running_contracts_in_place_for_emergency_works_like_breach_closing_tree_removal_landslide_clearance_etc_where_the_stretches_are_neither_under_construction_nor_in_dlp_is_ensured_and_the_details_there_of_entered_in_the_google_worksheet);
                HSSFCell hssfCell6 = hssfRow.createCell(j++);
                hssfCell1.setCellValue(R.string.bailey_bridges_including_their_quantities_and_locations_for_hilly_areas_are_kept_ready_and_the_details_there_of_entered_in_the_google_worksheet);
                HSSFCell hssfCell7 = hssfRow.createCell(j++);
                hssfCell1.setCellValue(R.string.whether_hume_pipes_np_3_1_m_diameter_in_required_quantities_are_kept_ready_and_the_details_there_of_entered_in_the_google_worksheet);
                HSSFCell hssfCell1 = hssfRow.createCell(j++);
                hssfCell1.setCellValue("RO");
                HSSFCell hssfCell1 = hssfRow.createCell(j++);
                hssfCell1.setCellValue("RO");
                HSSFCell hssfCell1 = hssfRow.createCell(j++);
                hssfCell1.setCellValue("RO");
                HSSFCell hssfCell1 = hssfRow.createCell(j++);
                hssfCell1.setCellValue("RO");
                HSSFCell hssfCell1 = hssfRow.createCell(j++);
                hssfCell1.setCellValue("RO");
                for (int i = 1; i<=l.size(); i++){
                    HSSFRow hssfRow = hssfSheet.createRow(i);
                    int j=0;
                    HSSFCell hssfCell = hssfRow.createCell(j++);
                    hssfCell.setCellValue(l.get(i-1).getRO().toString());
                    HSSFCell hssfCell2 = hssfRow.createCell(j++);
                    hssfCell2.setCellValue(l.get(i-1).getDate().toString());
                    HSSFCell hssfCell3 = hssfRow.createCell(j++);
                    hssfCell3.setCellValue(l.get(i-1).getinst1()?"Done":"Not Done");
                    HSSFCell hssfCell4 = hssfRow.createCell(j++);
                    hssfCell4.setCellValue(l.get(i-1).getinst2()?"Done":"Not Done");
                    HSSFCell hssfCell5 = hssfRow.createCell(j++);
                    hssfCell5.setCellValue(l.get(i-1).getinst3()?"Done":"Not Done");
                    HSSFCell hssfCell6 = hssfRow.createCell(j++);
                    hssfCell6.setCellValue(l.get(i-1).getinst4()?"Done":"Not Done");
                    HSSFCell hssfCell7 = hssfRow.createCell(j++);
                    hssfCell7.setCellValue(l.get(i-1).getinst5()?"Done":"Not Done");
                    HSSFCell hssfCell8 = hssfRow.createCell(j++);
                    hssfCell8.setCellValue(l.get(i-1).getinst6()?"Done":"Not Done");
                    HSSFCell hssfCell9 = hssfRow.createCell(j++);
                    hssfCell9.setCellValue(l.get(i-1).getinst7()?"Done":"Not Done");
                    HSSFCell hssfCell10 = hssfRow.createCell(j++);
                    hssfCell10.setCellValue(l.get(i-1).getinst8()?"Done":"Not Done");
                    HSSFCell hssfCell11 = hssfRow.createCell(j++);
                    hssfCell11.setCellValue(l.get(i-1).getinst9()?"Done":"Not Done");
                    HSSFCell hssfCell12 = hssfRow.createCell(j++);
                    hssfCell12.setCellValue(l.get(i-1).getinst10()?"Done":"Not Done");
                    HSSFCell hssfCell13 = hssfRow.createCell(j++);
                    hssfCell12.setCellValue(l.get(i-1).getinst11()?"Done":"Not Done");
                }
                saveWorkBook(hssfWorkbook);
            }
        });
    }
    private void saveWorkBook(HSSFWorkbook hssfWorkbook){
        StorageManager storageManager = (StorageManager) getSystemService(STORAGE_SERVICE);


        StorageVolume storageVolume = storageManager.getStorageVolumes().get(0); // internal storage

        File fileOutput = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            fileOutput = new File(storageVolume.getDirectory().getPath() +"/Download/ActionTakenReport.xls");
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