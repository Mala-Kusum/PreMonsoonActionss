package com.example.premonsoonaction.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.premonsoonaction.R;

public class AddInsufficiency extends AppCompatActivity {

 AutoCompleteTextView ro,pmu,eq;
 EditText loc;
 ArrayAdapter ad,ad2,eqa;
 TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_insufficiency);
        ro =(AutoCompleteTextView) findViewById(R.id.ro);
        pmu = (AutoCompleteTextView) findViewById(R.id.pmu);
        loc = findViewById(R.id.loc);
        eq = (AutoCompleteTextView) findViewById(R.id.Type);
        t = findViewById(R.id.eq);
        ro.setText(MainActivity.RO);
        ad = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.ROs, android.R.layout.select_dialog_singlechoice);
        ad2 = ArrayAdapter.createFromResource(AddInsufficiency.this, R.array.ROs, android.R.layout.select_dialog_singlechoice);
        eq.setText(Equipments.eq);
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
        ro.setAdapter(ad);
        ro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ro.setText("");
                ro.showDropDown();
            }
        });

        pmu.setText(MainActivity.pmu);
        pmu.setThreshold(1);
        pmu.setAdapter(ad2);
        try{
            pmu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pmu.setText("");
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
