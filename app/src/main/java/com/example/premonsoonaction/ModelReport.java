package com.example.premonsoonaction;

import com.google.firebase.Timestamp;

import java.util.Date;

public class ModelReport {
    Timestamp submitted;

    public Timestamp getDate() {
        return submitted;
    }

    public void setDate(Timestamp date) {
        this.submitted = date;
    }
}
