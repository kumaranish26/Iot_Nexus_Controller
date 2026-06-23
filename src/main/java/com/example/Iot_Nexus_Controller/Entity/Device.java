package com.example.Iot_Nexus_Controller.Entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

// TODO: Mark this class as a JPA Entity so it maps to a database table
@Entity
@Getter
@Setter
public class Device {

    // TODO: Mark this field as the Primary Key
    @Id
    // TODO: Configure the ID to Auto-Increment using the IDENTITY strategy
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // Standard fields for the device
    private String name;
    private String status;

    // --- RELATIONSHIP MAPPING SECTION ---

    // TODO: Define the relationship type: Many Devices belong to One Room
    //@__________
    // TODO: Configure the Foreign Key column name (name it "room_id")
    // @__________
    // TODO: Add the annotation to ignore this field during JSON conversion
    // (This prevents the 'Infinite Recursion' loop: Room -> Device -> Room...)


    @ManyToOne @JoinColumn(name = "room_id")

    private Room room;

    // TODO: Write the Getters and Setters for all fields

}


