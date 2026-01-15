package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    // Using ArrayList for dynamic storage
    private List<Student> students;
    
    public StudentService() {
        this.students = new ArrayList<>();
    }
    
    // Method to add student
    public Student addStudent(String firstName, String lastName, String email, String batch) 
            throws InvalidInputException {
        
        // Input validation
        if (!InputValidator.isValidName(firstName)) {
            throw new InvalidInputException("First name must be at least 2 characters");
        }
        
        if (!InputValidator.isValidName(lastName)) {
            throw new InvalidInputException("Last name must be at least 2 characters");
        }
        
        if (email != null && !email.isEmpty() && !InputValidator.isValidEmail(email)) {
            throw new InvalidInputException("Invalid email format");
        }
        
        String id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        students.add(student);
        
        return student;
    }
    
    // Method overloading: add student without email
    public Student addStudent(String firstName, String lastName, String batch) 
            throws InvalidInputException {
        return addStudent(firstName, lastName, null, batch);
    }
    
    // Get all students
    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Return copy for encapsulation
    }
    
    // Get active students only
    public List<Student> getActiveStudents() {
        List<Student> activeStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.isActive()) {
                activeStudents.add(student);
            }
        }
        return activeStudents;
    }
    
    // Find student by ID
    public Student findStudentById(String id) throws EntityNotFoundException {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student", id);
    }
    
    // Deactivate student (soft delete)
    public boolean deactivateStudent(String id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
        return true;
    }
    
    // Update student information
    public Student updateStudent(String id, String firstName, String lastName, String email, String batch) 
            throws EntityNotFoundException, InvalidInputException {
        
        Student student = findStudentById(id);
        
        if (firstName != null && !firstName.isEmpty()) {
            if (!InputValidator.isValidName(firstName)) {
                throw new InvalidInputException("First name must be at least 2 characters");
            }
            student.setFirstName(firstName);
        }
        
        if (lastName != null && !lastName.isEmpty()) {
            if (!InputValidator.isValidName(lastName)) {
                throw new InvalidInputException("Last name must be at least 2 characters");
            }
            student.setLastName(lastName);
        }
        
        if (email != null && !email.isEmpty()) {
            if (!InputValidator.isValidEmail(email)) {
                throw new InvalidInputException("Invalid email format");
            }
            student.setEmail(email);
        }
        
        if (batch != null && !batch.isEmpty()) {
            student.setBatch(batch);
        }
        
        return student;
    }
    
    // Search students by name
    public List<Student> searchStudentsByName(String name) {
        List<Student> results = new ArrayList<>();
        String searchTerm = name.toLowerCase();
        
        for (Student student : students) {
            if (student.getFirstName().toLowerCase().contains(searchTerm) ||
                student.getLastName().toLowerCase().contains(searchTerm) ||
                student.getDisplayName().toLowerCase().contains(searchTerm)) {
                results.add(student);
            }
        }
        return results;
    }
}