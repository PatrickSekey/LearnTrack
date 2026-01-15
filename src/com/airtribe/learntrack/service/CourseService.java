package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private List<Course> courses;
    
    public CourseService() {
        this.courses = new ArrayList<>();
    }
    
    // Add new course
    public Course addCourse(String courseName, String description, int durationInWeeks) 
            throws InvalidInputException {
        
        if (!InputValidator.isValidName(courseName)) {
            throw new InvalidInputException("Course name must be at least 2 characters");
        }
        
        if (!InputValidator.isPositiveNumber(durationInWeeks)) {
            throw new InvalidInputException("Duration must be positive number");
        }
        
        String id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        courses.add(course);
        
        return course;
    }
    
    // Get all courses
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }
    
    // Get active courses
    public List<Course> getActiveCourses() {
        List<Course> activeCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.isActive()) {
                activeCourses.add(course);
            }
        }
        return activeCourses;
    }
    
    // Find course by ID
    public Course findCourseById(String id) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course", id);
    }
    
    // Toggle course active status
    public boolean toggleCourseStatus(String id, boolean active) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(active);
        return true;
    }
    
    // Search courses by name
    public List<Course> searchCoursesByName(String name) {
        List<Course> results = new ArrayList<>();
        String searchTerm = name.toLowerCase();
        
        for (Course course : courses) {
            if (course.getCourseName().toLowerCase().contains(searchTerm) ||
                course.getDescription().toLowerCase().contains(searchTerm)) {
                results.add(course);
            }
        }
        return results;
    }
}