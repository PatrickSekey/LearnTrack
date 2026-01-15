package com.airtribe.learntrack.entity;

public class Trainer extends Person {
    private String specialization;
    private int yearsOfExperience;
    
    public Trainer(String id, String firstName, String lastName, String email, 
                   String specialization, int yearsOfExperience) {
        super(id, firstName, lastName, email);
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }
    
    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName() + " - " + specialization + " Expert";
    }
    
    // Getters and setters
    public String getSpecialization() {
        return specialization;
    }
    
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}