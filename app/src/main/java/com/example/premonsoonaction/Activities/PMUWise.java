package com.example.premonsoonaction.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.anthonyfdev.dropdownview.DropDownView;
import com.example.premonsoonaction.R;

public class PMUWise extends AppCompatActivity {
    DropDownView report;
    TextView action_type;
    ArrayAdapter<CharSequence> ad;
    Spinner s;
    TextView rate,equip,mater,actionreport;
    LinearLayout ratel,equipl,materl,reportl;
    CardView ratec,equipc,materc,reportc;
    Boolean expanded,expandedeq,expandedmat,expandedrep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmuwise);
        /*equipc=findViewById(R.id.equipmentcard);*/
        s=equipc.findViewById(R.id.inventory);
        ad=ArrayAdapter.createFromResource(PMUWise.this,R.array.inventory,android.R.layout.simple_spinner_item);
        s.setAdapter(ad);

        /*ratec=findViewById(R.id.raterunningcard);
        rate= findViewById(R.id.raterunning);
        ratel = findViewById(R.id.raterunninglayout);
        ratel.setVisibility(View.GONE);
        equipc=findViewById(R.id.equipmentcard);
        equip=findViewById(R.id.equipment);
        equipl=findViewById(R.id.equipmentlayout);
        equipl.setVisibility(View.GONE);
        materc=findViewById(R.id.materialcard);
        mater=findViewById(R.id.material);
        materl=findViewById(R.id.materiallayout);
        materl.setVisibility(View.GONE);
        reportc=findViewById(R.id.reportcard);
        actionreport=findViewById(R.id.report);
        reportl=findViewById(R.id.reportlayout);
        reportl.setVisibility(View.GONE);*/

        expandedeq=false;
        expandedmat=false;
        expanded=false;
        expandedrep=false;

        equipc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expandedeq){
                    equip.setVisibility(View.VISIBLE);
                    equipl.setVisibility(View.GONE);
                    expandedeq=false;
                }
                else{
                    equip.setVisibility(View.GONE);
                    equipl.setVisibility(View.VISIBLE);
                    expandedeq=true;
                }
            }
        });

        materc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expandedmat){
                    mater.setVisibility(View.VISIBLE);
                    materl.setVisibility(View.GONE);
                    expandedmat=false;
                }
                else{
                    mater.setVisibility(View.GONE);
                    materl.setVisibility(View.VISIBLE);
                    expandedmat=true;
                }
            }
        });
        ratec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expanded){
                    rate.setVisibility(View.VISIBLE);
                    ratel.setVisibility(View.GONE);
                    expanded=false;
                }
                else{
                    rate.setVisibility(View.GONE);
                    ratel.setVisibility(View.VISIBLE);
                    expanded= true;
                }
            }
        });

        reportc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expandedrep){
                    actionreport.setVisibility(View.VISIBLE);
                    reportl.setVisibility(View.GONE);
                    expandedrep=false;
                }
                else{
                    actionreport.setVisibility(View.GONE);
                    reportl.setVisibility(View.VISIBLE);
                    expandedrep=true;
                }
            }
        });
        //action_type = findViewById(R.id.action_type);
        /*report = (DropDownView) findViewById(R.id.report);
        View shrink = LayoutInflater.from(this).inflate(R.layout.report_header,null,false);
        View  expanded = LayoutInflater.from(this).inflate(R.layout.report_footer,null,false);
        report.setHeaderView(shrink);
        report.setExpandedView(expanded);
        shrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(report.isExpanded()){
                    report.collapseDropDown();
                }
                else{
                    report.expandDropDown();
                }
            }
        });*/
    }
}