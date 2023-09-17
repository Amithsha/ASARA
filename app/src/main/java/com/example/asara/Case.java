package com.example.asara;

import java.io.Serializable;

public class Case implements Serializable {

    private int id;
    private String shortDescription;
    private String status;
    private String fullDescription;

    public Case(int id, String shortDescription, String status, String fullDescription) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.status = status;
        this.fullDescription = fullDescription;
    }

    public String getId() {
        String ticketNo="Ticket ID : "+id;
        return ticketNo;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getStatus() {
        return status;
    }
}
