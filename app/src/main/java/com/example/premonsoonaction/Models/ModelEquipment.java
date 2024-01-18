package com.example.premonsoonaction.Models;

public class ModelEquipment {
    String name,no,pmu;
    String [] locations;

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPmu() {
        return pmu;
    }

    public void setPmu(String pmu) {
        this.pmu = pmu;
    }
}
