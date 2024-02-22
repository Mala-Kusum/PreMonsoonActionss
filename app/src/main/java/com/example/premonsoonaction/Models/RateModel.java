package com.example.premonsoonaction.Models;

import java.util.Date;

public class RateModel {
    private String ro;
    private String type;
    private String pmis;
    private String address;
    private Date start;
    private Date end;
    private String email;
    private String mobile;
    private String name;
    private String details;

    public RateModel(String type, String pmis, String address, Date start, Date end, String email, String mobile, String name, String details) {
        this.type = type;
        this.pmis = pmis;
        this.address = address;
        this.start = start;
        this.end = end;
        this.email = email;
        this.mobile = mobile;
        this.name = name;
        this.details = details;
    }

    // Getters and setters for each field
    public String getRo() { return ro; }

    public void setRo(String ro) { this.ro = ro; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPmis() {
        return pmis;
    }

    public void setPmis(String pmis) {
        this.pmis = pmis;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

   public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}