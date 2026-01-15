package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.*;
import com.airtribe.learntrack.service.*;
import com.airtribe.learntrack.exception.*;
import com.airtribe.learntrack.exception.InvalidInputException;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        initializeServices();
        scanner = new Scanner(System.in);
        
        System.out.println("=========================================");
        System.out.println("    WELCOME TO LEARNTRACK SYSTEM");
        System.out.println("    Student & Course Management");
        System.out.println("=========================================");
        
        boolean running = true;
        
        while (running) {
            try {
                displayMainMenu();
                int choice = getIntInput("Enter your choice: ");
                
                switch (choice) {
                    case 1:
                        studentManagementMenu();
                        break;
                    case 2:
                        courseManagementMenu();
                        break;
                    case 3:
                        enrollmentManagementMenu();
                        break;
                    case 4:
                        displayReports();
                        break;
                    case 5:
                        demoInheritanceAndPolymorphism();
                        break;
                    case 0:
                        System.out.println("\nThank you for using LearnTrack!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void initializeServices() {
        studentService = new StudentService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService(studentService, courseService);
        
        // Add some sample data
        addSampleData();
    }
    
    private static void addSampleData() {
        try {
            // Add sample students
            studentService.addStudent("John", "Doe", "john@example.com", "2024-Batch-1");
            studentService.addStudent("Jane", "Smith", "jane@example.com", "2024-Batch-1");
            studentService.addStudent("Alice", "Johnson", null, "2024-Batch-2");
            
            // Add sample courses
            courseService.addCourse("Java Fundamentals", "Learn core Java concepts", 8);
            courseService.addCourse("Web Development", "HTML, CSS, JavaScript basics", 10);
            courseService.addCourse("Database Design", "SQL and database principles", 6);
            
            // Add sample enrollments
            enrollmentService.enrollStudent("STU1000", "CRS2000");
            enrollmentService.enrollStudent("STU1001", "CRS2000");
            enrollmentService.enrollStudent("STU1001", "CRS2001");
            
        } catch (Exception e) {
            System.out.println("Error adding sample data: " + e.getMessage());
        }
    }
    
    private static void displayMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. Reports");
        System.out.println("5. Demo Inheritance/Polymorphism");
        System.out.println("0. Exit");
        System.out.println("====================");
    }
    
    // STUDENT MANAGEMENT MENU
    private static void studentManagementMenu() {
        boolean backToMain = false;
        
        while (!backToMain) {
            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Student");
            System.out.println("6. Deactivate Student");
            System.out.println("7. View Active Students");
            System.out.println("0. Back to Main Menu");
            System.out.println("==============================");
            
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        searchStudentById();
                        break;
                    case 4:
                        searchStudentByName();
                        break;
                    case 5:
                        updateStudent();
                        break;
                    case 6:
                        deactivateStudent();
                        break;
                    case 7:
                        viewActiveStudents();
                        break;
                    case 0:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        
        String firstName = getStringInput("Enter first name: ");
        String lastName = getStringInput("Enter last name: ");
        String email = getStringInput("Enter email (press Enter to skip): ");
        String batch = getStringInput("Enter batch: ");
        
        try {
            Student student;
            if (email == null || email.trim().isEmpty()) {
                student = studentService.addStudent(firstName, lastName, batch);
            } else {
                student = studentService.addStudent(firstName, lastName, email, batch);
            }
            
            System.out.println("\nStudent added successfully!");
            System.out.println("Student ID: " + student.getId());
            System.out.println("Name: " + student.getDisplayName());
            
        } catch (InvalidInputException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }
    
    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        System.out.printf("%-12s %-20s %-30s %-15s %-10s\n", 
                "ID", "Name", "Email", "Batch", "Status");
        System.out.println("---------------------------------------------------------------------------");
        
        for (Student student : students) {
            System.out.printf("%-12s %-20s %-30s %-15s %-10s\n",
                    student.getId(),
                    student.getFirstName() + " " + student.getLastName(),
                    student.getEmail() != null ? student.getEmail() : "N/A",
                    student.getBatch(),
                    student.isActive() ? "Active" : "Inactive");
        }
    }
    
    private static void searchStudentById() {
        System.out.println("\n--- Search Student by ID ---");
        String id = getStringInput("Enter student ID: ");
        
        try {
            Student student = studentService.findStudentById(id);
            displayStudentDetails(student);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void searchStudentByName() {
        System.out.println("\n--- Search Student by Name ---");
        String name = getStringInput("Enter student name to search: ");
        
        List<Student> results = studentService.searchStudentsByName(name);
        
        if (results.isEmpty()) {
            System.out.println("No students found with name containing: " + name);
            return;
        }
        
        System.out.println("\nFound " + results.size() + " student(s):");
        for (Student student : results) {
            displayStudentDetails(student);
            System.out.println("---");
        }
    }
    
    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        String id = getStringInput("Enter student ID to update: ");
        
        try {
            Student current = studentService.findStudentById(id);
            System.out.println("Current details:");
            displayStudentDetails(current);
            
            System.out.println("\nEnter new details (press Enter to keep current value):");
            
            String firstName = getStringInput("First name [" + current.getFirstName() + "]: ");
            String lastName = getStringInput("Last name [" + current.getLastName() + "]: ");
            String email = getStringInput("Email [" + (current.getEmail() != null ? current.getEmail() : "N/A") + "]: ");
            String batch = getStringInput("Batch [" + current.getBatch() + "]: ");
            
            // Convert empty strings to null
            if (firstName.isEmpty()) firstName = null;
            if (lastName.isEmpty()) lastName = null;
            if (email.isEmpty() || email.equals("N/A")) email = null;
            if (batch.isEmpty()) batch = null;
            
            Student updated = studentService.updateStudent(id, firstName, lastName, email, batch);
            
            System.out.println("\nStudent updated successfully!");
            displayStudentDetails(updated);
            
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }
    
    private static void deactivateStudent() {
        System.out.println("\n--- Deactivate Student ---");
        String id = getStringInput("Enter student ID to deactivate: ");
        
        try {
            boolean success = studentService.deactivateStudent(id);
            if (success) {
                System.out.println("Student deactivated successfully.");
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void viewActiveStudents() {
        System.out.println("\n--- Active Students ---");
        List<Student> activeStudents = studentService.getActiveStudents();
        
        if (activeStudents.isEmpty()) {
            System.out.println("No active students found.");
            return;
        }
        
        for (Student student : activeStudents) {
            displayStudentDetails(student);
            System.out.println("---");
        }
    }
    
    private static void displayStudentDetails(Student student) {
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getDisplayName());
        System.out.println("Email: " + (student.getEmail() != null ? student.getEmail() : "N/A"));
        System.out.println("Batch: " + student.getBatch());
        System.out.println("Status: " + (student.isActive() ? "Active" : "Inactive"));
    }
    
    // COURSE MANAGEMENT MENU (similar pattern to student management)
    private static void courseManagementMenu() {
        boolean backToMain = false;
        
        while (!backToMain) {
            System.out.println("\n===== COURSE MANAGEMENT =====");
            System.out.println("1. Add New Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course by ID");
            System.out.println("4. Search Course by Name");
            System.out.println("5. Toggle Course Status");
            System.out.println("6. View Active Courses");
            System.out.println("0. Back to Main Menu");
            System.out.println("=============================");
            
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        viewAllCourses();
                        break;
                    case 3:
                        searchCourseById();
                        break;
                    case 4:
                        searchCourseByName();
                        break;
                    case 5:
                        toggleCourseStatus();
                        break;
                    case 6:
                        viewActiveCourses();
                        break;
                    case 0:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void addCourse() {
        System.out.println("\n--- Add New Course ---");
        
        String courseName = getStringInput("Enter course name: ");
        String description = getStringInput("Enter course description: ");
        int duration = getIntInput("Enter duration in weeks: ");
        
        try {
            Course course = courseService.addCourse(courseName, description, duration);
            System.out.println("\nCourse added successfully!");
            System.out.println("Course ID: " + course.getId());
            System.out.println("Course Name: " + course.getCourseName());
            
        } catch (InvalidInputException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
    }
    
    private static void viewAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = courseService.getAllCourses();
        
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        
        System.out.printf("%-12s %-25s %-30s %-15s %-10s\n", 
                "ID", "Name", "Description", "Duration(Weeks)", "Status");
        System.out.println("-------------------------------------------------------------------------------");
        
        for (Course course : courses) {
            String shortDesc = course.getDescription();
            if (shortDesc.length() > 28) {
                shortDesc = shortDesc.substring(0, 25) + "...";
            }
            
            System.out.printf("%-12s %-25s %-30s %-15d %-10s\n",
                    course.getId(),
                    course.getCourseName(),
                    shortDesc,
                    course.getDurationInWeeks(),
                    course.isActive() ? "Active" : "Inactive");
        }
    }
    
    private static void searchCourseById() {
        System.out.println("\n--- Search Course by ID ---");
        String id = getStringInput("Enter course ID: ");
        
        try {
            Course course = courseService.findCourseById(id);
            displayCourseDetails(course);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void searchCourseByName() {
        System.out.println("\n--- Search Course by Name ---");
        String name = getStringInput("Enter course name to search: ");
        
        List<Course> results = courseService.searchCoursesByName(name);
        
        if (results.isEmpty()) {
            System.out.println("No courses found with name containing: " + name);
            return;
        }
        
        System.out.println("\nFound " + results.size() + " course(s):");
        for (Course course : results) {
            displayCourseDetails(course);
            System.out.println("---");
        }
    }
    
    private static void toggleCourseStatus() {
        System.out.println("\n--- Toggle Course Status ---");
        String id = getStringInput("Enter course ID: ");
        
        try {
            Course course = courseService.findCourseById(id);
            System.out.println("Current status: " + (course.isActive() ? "Active" : "Inactive"));
            
            System.out.println("1. Activate");
            System.out.println("2. Deactivate");
            int choice = getIntInput("Choose action: ");
            
            boolean newStatus = (choice == 1);
            boolean success = courseService.toggleCourseStatus(id, newStatus);
            
            if (success) {
                System.out.println("Course status updated successfully.");
                System.out.println("New status: " + (newStatus ? "Active" : "Inactive"));
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void viewActiveCourses() {
        System.out.println("\n--- Active Courses ---");
        List<Course> activeCourses = courseService.getActiveCourses();
        
        if (activeCourses.isEmpty()) {
            System.out.println("No active courses found.");
            return;
        }
        
        for (Course course : activeCourses) {
            displayCourseDetails(course);
            System.out.println("---");
        }
    }
    
    private static void displayCourseDetails(Course course) {
        System.out.println("ID: " + course.getId());
        System.out.println("Name: " + course.getCourseName());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Duration: " + course.getDurationInWeeks() + " weeks");
        System.out.println("Status: " + (course.isActive() ? "Active" : "Inactive"));
    }
    
    // ENROLLMENT MANAGEMENT MENU
    private static void enrollmentManagementMenu() {
        boolean backToMain = false;
        
        while (!backToMain) {
            System.out.println("\n===== ENROLLMENT MANAGEMENT =====");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. View All Enrollments");
            System.out.println("3. View Student Enrollments");
            System.out.println("4. View Course Enrollments");
            System.out.println("5. Update Enrollment Status");
            System.out.println("6. View Enrollment Details");
            System.out.println("0. Back to Main Menu");
            System.out.println("==================================");
            
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        enrollStudentInCourse();
                        break;
                    case 2:
                        viewAllEnrollments();
                        break;
                    case 3:
                        viewStudentEnrollments();
                        break;
                    case 4:
                        viewCourseEnrollments();
                        break;
                    case 5:
                        updateEnrollmentStatus();
                        break;
                    case 6:
                        viewEnrollmentDetails();
                        break;
                    case 0:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void enrollStudentInCourse() {
        System.out.println("\n--- Enroll Student in Course ---");
        
        try {
            // Show available students
            System.out.println("\nAvailable Students:");
            List<Student> students = studentService.getActiveStudents();
            for (Student student : students) {
                System.out.println(student.getId() + ": " + student.getDisplayName());
            }
            
            // Show available courses
            System.out.println("\nAvailable Courses:");
            List<Course> courses = courseService.getActiveCourses();
            for (Course course : courses) {
                System.out.println(course.getId() + ": " + course.getCourseName());
            }
            
            String studentId = getStringInput("\nEnter student ID: ");
            String courseId = getStringInput("Enter course ID: ");
            
            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            
            System.out.println("\nEnrollment successful!");
            System.out.println("Enrollment ID: " + enrollment.getId());
            System.out.println("Status: " + enrollment.getStatus());
            System.out.println("Date: " + enrollment.getEnrollmentDate());
            
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void viewAllEnrollments() {
        System.out.println("\n--- All Enrollments ---");
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        }
        
        System.out.printf("%-12s %-12s %-12s %-15s %-12s\n", 
                "Enrollment ID", "Student ID", "Course ID", "Date", "Status");
        System.out.println("------------------------------------------------------------");
        
        for (Enrollment enrollment : enrollments) {
            System.out.printf("%-12s %-12s %-12s %-15s %-12s\n",
                    enrollment.getId(),
                    enrollment.getStudentId(),
                    enrollment.getCourseId(),
                    enrollment.getEnrollmentDate(),
                    enrollment.getStatus());
        }
    }
    
    private static void viewStudentEnrollments() {
        System.out.println("\n--- View Student Enrollments ---");
        String studentId = getStringInput("Enter student ID: ");
        
        try {
            // Verify student exists
            studentService.findStudentById(studentId);
            
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
            
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for this student.");
                return;
            }
            
            System.out.println("\nEnrollments for Student ID: " + studentId);
            System.out.printf("%-12s %-12s %-15s %-12s\n", 
                    "Enrollment ID", "Course ID", "Date", "Status");
            System.out.println("------------------------------------------------");
            
            for (Enrollment enrollment : enrollments) {
                System.out.printf("%-12s %-12s %-15s %-12s\n",
                        enrollment.getId(),
                        enrollment.getCourseId(),
                        enrollment.getEnrollmentDate(),
                        enrollment.getStatus());
            }
            
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void viewCourseEnrollments() {
        System.out.println("\n--- View Course Enrollments ---");
        String courseId = getStringInput("Enter course ID: ");
        
        try {
            // Verify course exists
            courseService.findCourseById(courseId);
            
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId);
            
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for this course.");
                return;
            }
            
            System.out.println("\nEnrollments for Course ID: " + courseId);
            System.out.printf("%-12s %-12s %-15s %-12s\n", 
                    "Enrollment ID", "Student ID", "Date", "Status");
            System.out.println("------------------------------------------------");
            
            for (Enrollment enrollment : enrollments) {
                System.out.printf("%-12s %-12s %-15s %-12s\n",
                        enrollment.getId(),
                        enrollment.getStudentId(),
                        enrollment.getEnrollmentDate(),
                        enrollment.getStatus());
            }
            
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void updateEnrollmentStatus() {
        System.out.println("\n--- Update Enrollment Status ---");
        String enrollmentId = getStringInput("Enter enrollment ID: ");
        
        try {
            Enrollment current = enrollmentService.findEnrollmentById(enrollmentId);
            System.out.println("Current status: " + current.getStatus());
            
            System.out.println("\nSelect new status:");
            System.out.println("1. ACTIVE");
            System.out.println("2. COMPLETED");
            System.out.println("3. CANCELLED");
            int choice = getIntInput("Enter choice (1-3): ");
            
            String newStatus;
            switch (choice) {
                case 1: newStatus = "ACTIVE"; break;
                case 2: newStatus = "COMPLETED"; break;
                case 3: newStatus = "CANCELLED"; break;
                default: throw new InvalidInputException("Invalid choice");
            }
            
            Enrollment updated = enrollmentService.updateEnrollmentStatus(enrollmentId, newStatus);
            
            System.out.println("\nEnrollment status updated successfully!");
            System.out.println("New status: " + updated.getStatus());
            
        } catch (EntityNotFoundException | InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void viewEnrollmentDetails() {
        System.out.println("\n--- View Enrollment Details ---");
        String enrollmentId = getStringInput("Enter enrollment ID: ");
        
        try {
            String details = enrollmentService.getEnrollmentDetails(enrollmentId);
            System.out.println("\n" + details);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // REPORTS MENU
    private static void displayReports() {
        System.out.println("\n===== REPORTS =====");
        System.out.println("1. System Summary");
        System.out.println("2. Active Students Count");
        System.out.println("3. Active Courses Count");
        System.out.println("4. Enrollment Statistics");
        System.out.println("0. Back");
        System.out.println("===================");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                displaySystemSummary();
                break;
            case 2:
                displayActiveStudentsCount();
                break;
            case 3:
                displayActiveCoursesCount();
                break;
            case 4:
                displayEnrollmentStatistics();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private static void displaySystemSummary() {
        System.out.println("\n--- System Summary ---");
        
        List<Student> allStudents = studentService.getAllStudents();
        List<Student> activeStudents = studentService.getActiveStudents();
        
        List<Course> allCourses = courseService.getAllCourses();
        List<Course> activeCourses = courseService.getActiveCourses();
        
        List<Enrollment> allEnrollments = enrollmentService.getAllEnrollments();
        
        System.out.println("Total Students: " + allStudents.size());
        System.out.println("Active Students: " + activeStudents.size());
        System.out.println("Total Courses: " + allCourses.size());
        System.out.println("Active Courses: " + activeCourses.size());
        System.out.println("Total Enrollments: " + allEnrollments.size());
        
        // Count enrollments by status
        int activeEnrollments = 0;
        int completedEnrollments = 0;
        int cancelledEnrollments = 0;
        
        for (Enrollment enrollment : allEnrollments) {
            switch (enrollment.getStatus()) {
                case "ACTIVE": activeEnrollments++; break;
                case "COMPLETED": completedEnrollments++; break;
                case "CANCELLED": cancelledEnrollments++; break;
            }
        }
        
        System.out.println("\nEnrollment Status:");
        System.out.println("  Active: " + activeEnrollments);
        System.out.println("  Completed: " + completedEnrollments);
        System.out.println("  Cancelled: " + cancelledEnrollments);
    }
    
    private static void displayActiveStudentsCount() {
        List<Student> activeStudents = studentService.getActiveStudents();
        System.out.println("\nActive Students: " + activeStudents.size());
    }
    
    private static void displayActiveCoursesCount() {
        List<Course> activeCourses = courseService.getActiveCourses();
        System.out.println("\nActive Courses: " + activeCourses.size());
    }
    
    private static void displayEnrollmentStatistics() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        
        if (enrollments.isEmpty()) {
            System.out.println("\nNo enrollments found.");
            return;
        }
        
        int active = 0, completed = 0, cancelled = 0;
        
        for (Enrollment enrollment : enrollments) {
            switch (enrollment.getStatus()) {
                case "ACTIVE": active++; break;
                case "COMPLETED": completed++; break;
                case "CANCELLED": cancelled++; break;
            }
        }
        
        System.out.println("\n--- Enrollment Statistics ---");
        System.out.println("Total Enrollments: " + enrollments.size());
        System.out.println("Active: " + active + " (" + (enrollments.size() > 0 ? (active * 100 / enrollments.size()) : 0) + "%)");
        System.out.println("Completed: " + completed + " (" + (enrollments.size() > 0 ? (completed * 100 / enrollments.size()) : 0) + "%)");
        System.out.println("Cancelled: " + cancelled + " (" + (enrollments.size() > 0 ? (cancelled * 100 / enrollments.size()) : 0) + "%)");
    }
    
    // DEMONSTRATION OF INHERITANCE AND POLYMORPHISM
    private static void demoInheritanceAndPolymorphism() {
        System.out.println("\n===== DEMONSTRATION: INHERITANCE & POLYMORPHISM =====");
        
        // Create instances using inheritance
        Person person = new Person("P001", "Generic", "Person", "person@example.com");
        Student student = new Student("S001", "John", "Student", "student@example.com", "2024-Batch");
        Trainer trainer = new Trainer("T001", "Jane", "Trainer", "trainer@example.com", "Java", 5);
        
        // Store in a Person array (polymorphism)
        Person[] people = {person, student, trainer};
        
        System.out.println("\nDemonstrating polymorphism:");
        System.out.println("All people calling getDisplayName() method:");
        System.out.println("--------------------------------------------");
        
        for (Person p : people) {
            // This demonstrates polymorphism - same method call, different behavior
            System.out.println(p.getDisplayName());
        }
        
        System.out.println("\nDemonstrating inheritance:");
        System.out.println("Student inherits from Person:");
        System.out.println("Student ID: " + student.getId());
        System.out.println("Student Email: " + student.getEmail());
        System.out.println("Student Batch: " + student.getBatch());
        
        System.out.println("\nTrainer inherits from Person:");
        System.out.println("Trainer Specialization: " + trainer.getSpecialization());
        System.out.println("Trainer Experience: " + trainer.getYearsOfExperience() + " years");
        
        // Demonstrate type checking and casting
        System.out.println("\nDemonstrating instanceof and casting:");
        for (Person p : people) {
            if (p instanceof Student) {
                Student s = (Student) p;
                System.out.println(p.getDisplayName() + " is a Student with batch: " + s.getBatch());
            } else if (p instanceof Trainer) {
                Trainer t = (Trainer) p;
                System.out.println(p.getDisplayName() + " is a Trainer with specialization: " + t.getSpecialization());
            } else {
                System.out.println(p.getDisplayName() + " is a generic Person");
            }
        }
        
        System.out.println("\n=====================================================");
    }
    
    // HELPER METHODS FOR INPUT HANDLING
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        
        // Handle empty input
        if (input.isEmpty()) {
            return "";
        }
        
        return input;
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}