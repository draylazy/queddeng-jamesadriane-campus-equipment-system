package edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity;

import jakarta.persistence.*;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String serialNumber;
    private boolean available = true;

    public Equipment() {}

    public Equipment(String name, String type, String serialNumber, boolean available) {
        this.name = name;
        this.type = type;
        this.serialNumber = serialNumber;
        this.available = available;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
