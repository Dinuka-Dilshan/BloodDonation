package com.example.blooddonation;

public class Organizer {
    private String name;
    private String latitude;
    private String longitude;
    private String time;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Organizer(String name, String time, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.time = time;
        this.longitude = longitude;
    }

    public Organizer(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTime() {
        return time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
