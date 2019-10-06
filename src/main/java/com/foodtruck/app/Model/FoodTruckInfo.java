package com.foodtruck.app.Model;

public class FoodTruckInfo {
    private String applicant;
    private String location;

    public FoodTruckInfo() {
        super();
    }

    public FoodTruckInfo(String applicant, String location) {
        this.applicant = applicant;
        this.location = location;
    }

    public String getApplicant() {
        return applicant;
    }

    public String getLocation() {
        return location;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
