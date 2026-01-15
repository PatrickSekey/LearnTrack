
**docs/JVM_Basics.md:**
```markdown
# JVM Basics Explained Simply

## What are JDK, JRE, and JVM?

### JVM (Java Virtual Machine)
- The **engine** that runs Java bytecode
- Provides "write once, run anywhere" capability
- Converts bytecode to machine-specific code

### JRE (Java Runtime Environment)
- Contains JVM + core libraries
- What you need to **run** Java programs
- Does NOT contain development tools

### JDK (Java Development Kit)
- Contains JRE + development tools (javac, javadoc, etc.)
- What you need to **develop** Java programs

## What is Bytecode?
- Intermediate code between Java source and machine code
- .class files contain bytecode
- Platform-independent
- JVM interprets/compiles bytecode to native code

## "Write Once, Run Anywhere"
- Java programs compile to bytecode (.class files)
- Bytecode runs on any platform with JVM
- JVM handles platform-specific differences
- No need to recompile for different operating systems