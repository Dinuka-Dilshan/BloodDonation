package com.example.blooddonation;

public class BloodRequest {

    private String description, name, phone, town;

    public BloodRequest(String description, String name, String phone, String town) {
        this.description = description;
        this.name = name;
        this.phone = phone;
        this.town = town;
    }

    public BloodRequest() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
