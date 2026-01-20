# LearnTrack
Student &amp; Course Management System

# LearnTrack - Student & Course Management System

## Project Description
LearnTrack is a console-based Student & Course Management System built with Core Java. It allows administrators to manage students, courses, and enrollments efficiently.

Example:

<details>
<summary>Click to see the wrapped version</summary>

````markdown
```mermaid
classDiagram
class Person {
    - int id
    - String firstName
    - String lastName
    - String email
    + String getDisplayName()
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


## Features
- Student Management (Add, View, Search, Deactivate)
- Course Management (Add, View, Activate/Deactivate)
- Enrollment Management (Enroll, View, Update Status)
- Clean console-based UI
- Data persistence in memory

## How to Compile and Run

### Prerequisites
- JDK 17 or higher
- Command line terminal

### Compilation
```bash
javac -d out src/com/airtribe/learntrack/**/*.java
