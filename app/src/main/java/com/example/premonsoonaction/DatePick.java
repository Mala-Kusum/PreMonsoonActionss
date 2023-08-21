package com.example.premonsoonaction;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
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
        System.out.println("Date " + (Date) c.getTime());
    }
}
