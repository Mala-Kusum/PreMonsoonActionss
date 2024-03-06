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
import com.example.premonsoonaction.Models.Vulnerable;
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
import java.util.Objects;

public class Report extends AppCompatActivity {
    FloatingActionButton button;
    Button export;
    ImageButton from,to;
    public static Date f,t;
    RecyclerView r;
    public static List<reportGetModel> l,filtered;

    private FirebaseFirestore db;
    CollectionReference c1,c2,c3,c4;
    Query q1,q2,q3;
    ArrayList<ArrayList<Vulnerable>> l1,l2,l3;
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
        l1=new ArrayList<>();
        l2=new ArrayList<>();
        l3=new ArrayList<>();
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
                            /*c1 = db.collection("checklist").document(ob.getDocid()).collection("Vulnerable");
                            c2 = db.collection("checklist").document(ob.getDocid()).collection("Critical");
                            c3 = db.collection("checklist").document(ob.getDocid()).collection("Inspected");
                            ArrayList<Vulnerable> s1,s2,s3;
                            s1 = new ArrayList<>();
                            s2 = new ArrayList<>();
                            s3 = new ArrayList<>();
                            q1=c1.orderBy("location").orderBy("type");
                            q2=c2.orderBy("location").orderBy("type");
                            q3=c3.orderBy("location");
                            q1.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    if (error != null) {
                                        Log.e("Firestore Error", error.getMessage());
                                        l1.add(s1);
                                        return;
                                    }
                                    for (DocumentChange dc1 : value.getDocumentChanges()) {
                                        if (dc1.getType() == DocumentChange.Type.ADDED) {
                                            //System.out.println("vulnerable doc "+ dc1.getDocument().toObject(Vulnerable.class).getLOCATION()+dc1.getDocument().toObject(Vulnerable.class).getNO()+dc1.getDocument().toObject(Vulnerable.class).getTYPE());
                                            Vulnerable ob1 = new Vulnerable();
                                            ob1.setTYPE(dc1.getDocument().getString("type"));
                                            try {
                                                ob1.setLOCATION(dc1.getDocument().getString("location"));
                                            }
                                            catch(Exception e){
                                                System.out.println("error no "+e.toString());
                                            }
                                            //System.out.println("ba " +ob1.getLOCATION()+" "+ob1.getTYPE()+" "+ob1.getNO());
                                            // l1.add(dc.getDocument().toObject(Vulnerable.class));
                                            s1.add(ob1);
                                        }
                                    }
                                    l1.add(s1);
                                }
                            });
                            q2.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    if(error!=null){
                                        Log.e("Firestore Error", error.getMessage());
                                    }
                                    else{
                                        for (DocumentChange dc2:value.getDocumentChanges()) {
                                            Vulnerable ob2 = new Vulnerable();
                                            ob2.setTYPE(dc2.getDocument().getString("type"));
                                            ob2.setLOCATION(dc2.getDocument().getString("location"));
                                            s2.add(ob2);
                                        }
                                    }
                                    l2.add(s2);
                                }
                            });
                            q3.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    if(error!=null){
                                        Log.e("Firestore Error", error.getMessage());
                                        l3.add(s3);
                                        return;
                                    }
                                    else{
                                        for (DocumentChange dc3:value.getDocumentChanges()) {
                                            Vulnerable v = new Vulnerable();
                                            v = dc3.getDocument().toObject(Vulnerable.class);
                                            s3.add(v);
                                        }
                                        l3.add(s3);
                                    }
                                }
                            });*/
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
        /*for(int i=0;i<l1.size();i++){
            for(int j=0;j<l1.get(i).size();j++){
                Log.d( "All Vulnerable l1 "+i+": ","type: "+l1.get(i).get(j).getTYPE()+" location: "+l1.get(i).get(j).getLOCATION()+", ");
            }
        }*/

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
                HSSFRow hssfRow0 = hssfSheet.createRow(0);
                int j=0;
                HSSFCell cell1 = hssfRow0.createCell(j++);
                cell1.setCellValue("RO");
                HSSFCell cell2 = hssfRow0.createCell(j++);
                cell2.setCellValue("Timestamp");
                HSSFCell cell3 = hssfRow0.createCell(j++);
                cell3.setCellValue(getString(R.string.cleaning_of_drains_culverts));
                HSSFCell cell4 = hssfRow0.createCell(j++);
                cell4.setCellValue(getString(R.string.keeping_in_stock_on_offer_jcbs_saw_cutters_for_tree_cutting_sand_bags_traffic_cones_amp_signages_etc_in_required_quantities_at_required_locations_is_ensured_and_the_details_there_of_entered_in_the_google_worksheet));
                HSSFCell cell5 = hssfRow0.createCell(j++);
                cell5.setCellValue(getString(R.string.rate_running_contracts_in_place_for_emergency_works_like_breach_closing_tree_removal_landslide_clearance_etc_where_the_stretches_are_neither_under_construction_nor_in_dlp_is_ensured_and_the_details_there_of_entered_in_the_google_worksheet));
                HSSFCell cell6 = hssfRow0.createCell(j++);
                cell6.setCellValue(getString(R.string.bailey_bridges_including_their_quantities_and_locations_for_hilly_areas_are_kept_ready_and_the_details_there_of_entered_in_the_google_worksheet));
                HSSFCell cell7 = hssfRow0.createCell(j++);
                cell7.setCellValue(getString(R.string.whether_hume_pipes_np_3_1_m_diameter_in_required_quantities_are_kept_ready_and_the_details_there_of_entered_in_the_google_worksheet));
                HSSFCell cell8 = hssfRow0.createCell(j++);
                cell8.setCellValue(getString(R.string.the_contractors_for_the_above_actions_in_respect_of_ongoing_work_stretches_and_those_under_dlp_maintenance_period_in_respect_of_above_actions_have_been_instructed_and_alerted));
                HSSFCell cell9 = hssfRow0.createCell(j++);
                cell9.setCellValue(getString(R.string.mehgdoot_app_has_been_downloaded_by_ros_all_field_officers_under_the_concerned_ros));
                HSSFCell cell10 = hssfRow0.createCell(j++);
                cell10.setCellValue(getString(R.string.local_whatsapp_groups_are_formed_by_regional_officers_covering_executing_agencies_of_mort_amp_h_roads_wings_nhai_nhidcl_bro_local_administration_cwc_etc));
                HSSFCell cell11 = hssfRow0.createCell(j++);
                cell11.setCellValue(getString(R.string.the_contact_details_of_emergency_service_providers_are_kept_ready_for_example_ambulance_hospitals_police_authorities_trauma_centres_etc));
                HSSFCell cell12 = hssfRow0.createCell(j++);
                cell12.setCellValue(getString(R.string.list_of_all_available_resources_and_arrangements_with_the_concerned_ros_and_nearby_ros_in_the_region_is_compiled_and_kept_ready));
                HSSFCell cell13 = hssfRow0.createCell(j++);
                cell13.setCellValue(getString(R.string._24_7_central_control_room_is_created_and_maintained_at_selected_location_by_concerned_ros_of_all_the_agencies_put_together));
                for (int i = 1; i<=l.size(); i++){
                    HSSFRow hssfRow = hssfSheet.createRow(i);
                    int k=0;
                    HSSFCell hssfCell = hssfRow.createCell(k++);
                    hssfCell.setCellValue(l.get(i-1).getRO().toString());
                    HSSFCell hssfCell2 = hssfRow.createCell(k++);
                    hssfCell2.setCellValue(l.get(i-1).getDate().toString());
                    HSSFCell hssfCell3 = hssfRow.createCell(k++);
                    hssfCell3.setCellValue(l.get(i-1).getinst1()?"Done":"Not Done");
                    HSSFCell hssfCell4 = hssfRow.createCell(k++);
                    hssfCell4.setCellValue(l.get(i-1).getinst2()?"Done":"Not Done");
                    HSSFCell hssfCell5 = hssfRow.createCell(k++);
                    hssfCell5.setCellValue(l.get(i-1).getinst3()?"Done":"Not Done");
                    HSSFCell hssfCell6 = hssfRow.createCell(k++);
                    hssfCell6.setCellValue(l.get(i-1).getinst4()?"Done":"Not Done");
                    HSSFCell hssfCell7 = hssfRow.createCell(k++);
                    hssfCell7.setCellValue(l.get(i-1).getinst5()?"Done":"Not Done");
                    HSSFCell hssfCell8 = hssfRow.createCell(k++);
                    hssfCell8.setCellValue(l.get(i-1).getinst6()?"Done":"Not Done");
                    HSSFCell hssfCell9 = hssfRow.createCell(k++);
                    hssfCell9.setCellValue(l.get(i-1).getinst7()?"Done":"Not Done");
                    HSSFCell hssfCell10 = hssfRow.createCell(k++);
                    hssfCell10.setCellValue(l.get(i-1).getinst8()?"Done":"Not Done");
                    HSSFCell hssfCell11 = hssfRow.createCell(k++);
                    hssfCell11.setCellValue(l.get(i-1).getinst9()?"Done":"Not Done");
                    HSSFCell hssfCell12 = hssfRow.createCell(k++);
                    hssfCell12.setCellValue(l.get(i-1).getinst10()?"Done":"Not Done");
                    HSSFCell hssfCell13 = hssfRow.createCell(k++);
                    hssfCell13.setCellValue(l.get(i-1).getinst11()?"Done":"Not Done");
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
            //Objects.requireNonNull(storageVolume.getDirectory()).ge
            fileOutput = new File(Objects.requireNonNull(storageVolume.getDirectory()).getPath() +"/Download","ActionTakenReports"+String.valueOf(System.currentTimeMillis())+".xls");
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