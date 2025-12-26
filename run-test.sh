#!/bin/bash
echo "Compiling source code..."
javac -d bin src/logic/*.java src/api/*.java

echo "Compiling tests..."
javac -cp "lib/junit-platform-console-standalone-1.11.0-M2.jar:bin" -d bin test/logic/**/*.java

echo "Running tests..."
java -jar lib/junit-platform-console-standalone-1.11.0-M2.jar --classpath bin --select-package logic