package com.airtribe.learntrack.entity;

import java.time.LocalDate;

public class Enrollment {
    private String id;
    private String studentId;
    private String courseId;
    private LocalDate enrollmentDate;
    private String status; // ACTIVE, COMPLETED, CANCELLED
    
    public Enrollment() {
        this.enrollmentDate = LocalDate.now();
        this.status = "ACTIVE";
    }
    
    public Enrollment(String id, String studentId, String courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDate.now();
        this.status = "ACTIVE";
    }
    
    public Enrollment(String id, String studentId, String courseId, String status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDate.now();
        this.status = status;
    }
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}