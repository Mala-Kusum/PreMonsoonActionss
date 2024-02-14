package com.example.premonsoonaction.Models;

public class PmuNo {
    String PMU;
    int NO;

    public PmuNo(String PMU, int NO) {
        this.PMU = PMU;
        this.NO = NO;
    }

    public String getPMU() {
        return PMU;
    }

    public void setPMU(String PMU) {
        this.PMU = PMU;
    }

    public int getNO() {
        return NO;
    }

    public void setNO(int NO) {
        this.NO = NO;
    }
}
