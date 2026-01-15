package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;
    
    // Constructor overloading examples
    public Student() {
        super();
        this.active = true;
    }
    
    // Constructor without email
    public Student(String id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, null);
        this.batch = batch;
        this.active = true;
    }
    
    // Constructor with all fields
    public Student(String id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }
    
    // Method overriding example
    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " (Batch: " + batch + ")";
    }
    
    // Getters and setters
    public String getBatch() {
        return batch;
    }
    
    public void setBatch(String batch) {
        this.batch = batch;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
}