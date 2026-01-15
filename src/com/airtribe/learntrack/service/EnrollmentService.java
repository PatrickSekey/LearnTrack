package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private List<Enrollment> enrollments;
    private StudentService studentService;
    private CourseService courseService;
    
    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.enrollments = new ArrayList<>();
        this.studentService = studentService;
        this.courseService = courseService;
    }
    
    // Enroll student in course
    public Enrollment enrollStudent(String studentId, String courseId) 
            throws EntityNotFoundException, InvalidInputException {
        
        // Check if student exists and is active
        Student student = studentService.findStudentById(studentId);
        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll inactive student");
        }
        
        // Check if course exists and is active
        Course course = courseService.findCourseById(courseId);
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in inactive course");
        }
        
        // Check if already enrolled
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId) && 
                enrollment.getCourseId().equals(courseId) &&
                enrollment.getStatus().equals("ACTIVE")) {
                throw new InvalidInputException("Student already enrolled in this course");
            }
        }
        
        String id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollments.add(enrollment);
        
        return enrollment;
    }
    
    // Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }
    
    // Get enrollments for a specific student
    public List<Enrollment> getEnrollmentsByStudentId(String studentId) {
        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId().equals(studentId)) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }
    
    // Get enrollments for a specific course
    public List<Enrollment> getEnrollmentsByCourseId(String courseId) {
        List<Enrollment> courseEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseId().equals(courseId)) {
                courseEnrollments.add(enrollment);
            }
        }
        return courseEnrollments;
    }
    
    // Update enrollment status
    public Enrollment updateEnrollmentStatus(String enrollmentId, String newStatus) 
            throws EntityNotFoundException, InvalidInputException {
        
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        
        // Validate status
        if (!isValidStatus(newStatus)) {
            throw new InvalidInputException("Invalid status. Must be ACTIVE, COMPLETED, or CANCELLED");
        }
        
        enrollment.setStatus(newStatus);
        return enrollment;
    }
    
    // Find enrollment by ID
    public Enrollment findEnrollmentById(String id) throws EntityNotFoundException {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId().equals(id)) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment", id);
    }
    
    // Helper method to validate status
    private boolean isValidStatus(String status) {
        return status.equals("ACTIVE") || 
               status.equals("COMPLETED") || 
               status.equals("CANCELLED");
    }
    
    // Get enrollment details with student and course info
    public String getEnrollmentDetails(String enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        
        try {
            Student student = studentService.findStudentById(enrollment.getStudentId());
            Course course = courseService.findCourseById(enrollment.getCourseId());
            
            return String.format("Enrollment ID: %s\nStudent: %s %s\nCourse: %s\nStatus: %s\nDate: %s",
                    enrollmentId,
                    student.getFirstName(), student.getLastName(),
                    course.getCourseName(),
                    enrollment.getStatus(),
                    enrollment.getEnrollmentDate());
        } catch (EntityNotFoundException e) {
            return "Enrollment found but associated student/course not found";
        }
    }
}