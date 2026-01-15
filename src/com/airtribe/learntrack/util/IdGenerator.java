package com.airtribe.learntrack.util;

public class IdGenerator {
    // Static variables - shared across all instances
    private static int studentIdCounter = 1000;
    private static int courseIdCounter = 2000;
    private static int enrollmentIdCounter = 3000;
    
    // Static methods - can be called without creating object
    public static String getNextStudentId() {
        return "STU" + studentIdCounter++;
    }
    
    public static String getNextCourseId() {
        return "CRS" + courseIdCounter++;
    }
    
    public static String getNextEnrollmentId() {
        return "ENR" + enrollmentIdCounter++;
    }
    
    // Reset counters (for testing)
    public static void resetCounters() {
        studentIdCounter = 1000;
        courseIdCounter = 2000;
        enrollmentIdCounter = 3000;
    }
}