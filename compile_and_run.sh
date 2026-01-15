#!/bin/bash
echo "Compiling LearnTrack..."
javac -d out src/com/airtribe/learntrack/ui/Main.java
if [ $? -eq 0 ]; then
    echo "Running LearnTrack..."
    java -cp out com.airtribe.learntrack.ui.Main
else
    echo "Compilation failed!"
    read -p "Press any key to continue..."
fi