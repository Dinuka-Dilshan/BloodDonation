package com.example.blooddonation;

public class Organizer {
    private String name;
    private String venue;
    private String time;

    public Organizer(String name, String venue, String time) {
        this.name = name;
        this.venue = venue;
        this.time = time;
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
