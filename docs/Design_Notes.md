# Design Notes

## Why ArrayList Instead of Array?

### Advantages of ArrayList:
1. **Dynamic Size**: Arrays have fixed size, while ArrayList grows automatically
2. **Built-in Methods**: ArrayList provides useful methods like add(), remove(), contains()
3. **Type Safety**: ArrayList uses generics for compile-time type checking
4. **Memory Efficiency**: Better memory management for dynamic data

### Example from project:
```java
// With array (fixed size)
Student[] students = new Student[10]; // Max 10 students

// With ArrayList (dynamic)
List<Student> students = new ArrayList<>(); // Can grow as needed