package com.example.premonsoonaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.anthonyfdev.dropdownview.DropDownView;

public class PMUWise extends AppCompatActivity {
    DropDownView report;
    TextView action_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmuwise);
        action_type = findViewById(R.id.action_type);
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