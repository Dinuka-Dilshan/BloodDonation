package com.example.blooddonation;

public class Organizer {
    private String name;
    private String venue;
    private String time;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Organizer(String name, String time, String venue) {
        this.name = name;
        this.venue = venue;
        this.time = time;
    }

    public Organizer(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
