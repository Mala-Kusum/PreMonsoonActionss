package com.example.premonsoonaction.Models;

public class RateModel {
    private String type;
    private String pmis;
    private String address;
    private String start;
    private String end;
    private String email;
    private String mobile;
    private String name;
    private String details;

    public RateModel(String type, String pmis, String address, String start, String end, String email, String mobile, String name, String details) {
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
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