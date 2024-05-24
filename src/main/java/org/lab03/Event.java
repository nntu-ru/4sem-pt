package org.lab03;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Event {
    private String name = "unknown";
    private String place = "unknown";
    private LocalDateTime dateTime = LocalDateTime.MIN;

    public Event() {

    }

    public Event(String name, String place, LocalDateTime dateTime) {
        this.name = name;
        this.place = place;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String toString() {
        return "Name: " + getName() + "; Place: " + getPlace() + "; DateTime: " + getDateTime();
    }
}
