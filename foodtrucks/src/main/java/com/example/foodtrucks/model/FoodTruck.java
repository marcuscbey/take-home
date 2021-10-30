package com.example.foodtrucks.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({     "locationId", "applicant", "facilityType", "cnn",
        "locationDescription", "Address", "blockLot", "block",
        "lot", "permit", "status", "foodItems", "x", "y",
        "Latitude", "Longitude", "schedule", "daysHours",
        "noiSent", "approved", "received", "priorPermit",
        "expirationDate", "location" })
public class FoodTruck {
    String locationId;
    String applicant;
    String facilityType;
    String cnn;
    String locationDescription;
    String Address;
    String blockLot;
    String block;
    String lot;
    String permit;
    String status;
    String foodItems;
    String x;
    String y;
    String Latitude;
    String Longitude;
    String schedule;
    String daysHours;
    String noiSent;
    String approved;
    String received;
    String priorPermit;
    String expirationDate;
    String location;
}


