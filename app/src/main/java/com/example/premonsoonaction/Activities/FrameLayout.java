package com.example.premonsoonaction.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.premonsoonaction.R;
import com.google.android.material.tabs.TabLayout;

public class FrameLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        /*TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs); // get the reference of TabLayout
        TabLayout.Tab eqTab = tabLayout.newTab(); // Create a new Tab names "First Tab"
        eqTab.setText(" "); // set the Text for the first Tab
        eqTab.setIcon(R.drawable.baseline_construction_24); // set an icon for the first tab'
        try{
            tabLayout.addTab(eqTab,1,true);
        }
        catch(Exception e){
            Log.e("on add tab: ", e.toString());
        }
        TabLayout.Tab matTab = tabLayout.newTab(); // Create a new Tab names "First Tab"
        matTab.setText(" "); // set the Text for the first Tab
        matTab.setIcon(R.drawable.baseline_oil_barrel_24); // set an icon for the first tab
        tabLayout.addTab(matTab,2,true);
        TabLayout.Tab contractTab = tabLayout.newTab(); // Create a new Tab names "First Tab"
        contractTab.setText(" "); // set the Text for the first Tab
        contractTab.setIcon(R.drawable.baseline_document_scanner_24); // set an icon for the first tab
        tabLayout.addTab(contractTab,3,true);
        TabLayout.Tab reportTab = tabLayout.newTab(); // Create a new Tab names "First Tab"
        reportTab.setText(" "); // set the Text for the first Tab
        reportTab.setIcon(R.drawable.baseline_assessment_24); // set an icon for the first tab
        tabLayout.addTab(contractTab,4,true);*/
    }
}