package com.example.premonsoonaction.Models;

public class Insuf {
    int required;
    Unit uni;
    InsufType type;
    String item;
    String ro;
    String pmu;
    String loc;
    public Insuf() {
        super();
    }

    public Insuf(int required, Unit uni, String item, String ro, String pmu, String loc) {
        this.required = required;
        this.uni = uni;
        this.item = item;
        this.ro = ro;
        this.pmu = pmu;
        this.loc = loc;
    }
    public int getRequired() {
        return required;
    }
    public void setRequired(int nequired) {
        this.required = nequired;
    }
    public Unit getUni() {
        return uni;
    }
    public void setUni(Unit uni) {
        this.uni = uni;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getRo() {
        return ro;
    }

    public void setRo(String ro) {
        this.ro = ro;
    }

    public String getPmu() {
        return pmu;
    }

    public void setPmu(String pmu) {
        this.pmu = pmu;
    }
    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
    public InsufType getType() {
        return type;
    }
    public void setType(InsufType type) {
        this.type = type;
    }
}
