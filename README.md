LearnTrack – Student & Course Management System
Project Description

LearnTrack is a console-based Student & Course Management System built using Core Java.
It allows an admin to manage students, courses, and enrollments through a menu-driven interface.

The project is designed to strengthen Java fundamentals and Object-Oriented Programming (OOP) concepts.
All data is stored in-memory using Java collections. No database or advanced frameworks are used.
Learning Focus

This project helps practice:

Core Java syntax and control flow

Classes, objects, and constructors

Encapsulation using getters and setters

Basic inheritance and polymorphism

Static vs instance members

Collections (ArrayList)

Basic exception handling

Clean, modular code structure.

Project Structure

com.airtribe.learntrack
├── entity     (Student, Course, Enrollment, Person)
├── service    (Business logic)
├── ui         (Main – console menu)
├── exception  (Custom exceptions)
├── util       (Utility classes)
└── docs       (Documentation)
How to Compile and Run
Prerequisites

JDK 8 or higher

IDE (IntelliJ IDEA recommended) or terminal

Run Using an IDE

Clone the repository

Open the project in your IDE

Run Main.java from com.airtribe.learntrack.ui

Run Using Terminal

javac com/airtribe/learntrack/**/*.java
java com.airtribe.learntrack.ui.Main

javac com/airtribe/learntrack/**/*.java
java com.airtribe.learntrack.ui.Main
Application Features

Add, view, search, and deactivate students

Add, view, activate, and deactivate courses

Enroll students into courses

View and update enrollment status

Graceful handling of invalid inputs.

Class Diagram
```mermaid
classDiagram
class Person {
    - int id
    - String firstName
    - String lastName
    - String email
    + String getDisplayName()
    + int getId()
    + void setId(int)
    + String getFirstName()
    + void setFirstName(String)
    + String getLastName()
    + void setLastName(String)
    + String getEmail()
    + void setEmail(String)
}

class Student {
    - String batch
    - boolean active
    + String getDisplayName()
}

class Trainer {
    - String specialization
    + String getDisplayName()
}

class Course {
    - int id
    - String courseName
    - String description
    - int durationInWeeks
    - boolean active
}

class Enrollment {
    - int id
    - int studentId
    - int courseId
    - Date enrollmentDate
    - String status
}

Person <|-- Student
Person <|-- Trainer

Student "1" --> "*" Enrollment
Course "1" --> "*" Enrollment

class StudentService {
    + addStudent(Student)
    + updateStudent(int)
    + removeStudent(int)
    + listStudents()
    + findById(int) Student
}

class CourseService {
    + addCourse(Course)
    + updateCourse(int)
    + activateCourse(int)
    + deactivateCourse(int)
    + listCourses()
    + findById(int) Course
}

class EnrollmentService {
    + enroll(int studentId, int courseId)
    + listEnrollmentsByStudent(int studentId)
    + updateEnrollmentStatus(int enrollmentId, String status)
}

class IdGenerator {
    <<utility>>
    + static int getNextStudentId()
    + static int getNextCourseId()
    + static int getNextEnrollmentId()
}

class InvalidInputException
class EntityNotFoundException

StudentService --> Student
CourseService --> Course
EnrollmentService --> Enrollment

IdGenerator ..> Student
IdGenerator ..> Course
IdGenerator ..> Enrollment

InvalidInputException <|-- Exception
EntityNotFoundException <|-- Exception
```

