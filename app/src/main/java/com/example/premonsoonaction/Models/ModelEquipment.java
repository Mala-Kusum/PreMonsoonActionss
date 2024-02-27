package com.example.premonsoonaction.Models;

public class ModelEquipment {
    String name;
    String pmu;
    String ro;
    int no;
    int insuf;
    String location;
    Unit insufUnit;
    Unit unit;
    Boolean isInsuf;
    public String getLocation() {
        return location;
    }

    public void setLocation(String locations) {
        this.location = locations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getPmu() {
        return pmu;
    }

    public void setPmu(String pmu) {
        this.pmu = pmu;
    }
    public int getInsuf() {
        return insuf;
    }
    public void setInsuf(int insuf) {
        this.insuf = insuf;
    }
    public String getRo() {
        return ro;
    }
    public void setRo(String ro) {
        this.ro = ro;
    }
    public Unit getUnit() {
        return unit;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    public Unit getInsufUnit() {
        return insufUnit;
    }
    public void setInsufUnit(Unit insufUnit) {
        this.insufUnit = insufUnit;
    }
    public void setIsInsuf(Boolean insuf) {
        isInsuf = insuf;
    }
    public Boolean getIsInsuf(Boolean insuf) {
        return isInsuf;
    }
}
