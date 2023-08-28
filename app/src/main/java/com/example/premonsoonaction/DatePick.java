package com.example.premonsoonaction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePick extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    Boolean fr;

    public DatePick(Boolean fr) {
        this.fr = fr;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Calendar c = Calendar.getInstance();
        c.set(year,month,day);
        if(fr){
            Report.f=c.getTime();
            Report.b1=true;
        }
        else{
            Report.t=c.getTime();
            Report.b2=true;
        }
        if(Report.b1&&Report.b2){
            Report.filtered.clear();
            for (int i = 0; i < Report.l.size(); i++) {

                Date date = Report.l.get(i).getDate();
                if (date.after(Report.f)&&date.before(Report.t)) {
                    Report.filtered.add(Report.l.get(i));
                }
            }
            if (Report.filtered.isEmpty()) {
                // if no item is added in filtered list we are
                //Toast.makeText(Report., "No Data Found..", Toast.LENGTH_SHORT).show();
            } else {
                // at last we are passing that filtered
                // list to our adapter class.
                try{
                    Report.ad.filterList((ArrayList<reportGetModel>) Report.filtered);

                }
                catch(Exception e){
                    Log.e("filtering  ",e.toString());                }

            } // data set changed
            Report.b1=false;
            Report.b2=false;
        }
        System.out.println("Date " + (Date) c.getTime());
    }
}
