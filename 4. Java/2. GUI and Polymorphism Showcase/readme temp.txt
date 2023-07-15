# GUI & Polymorphism Project

The GUI & Polymorphism project aims to develop a Java program that includes a GUI interface and implements a converter class hierarchy. The program allows users to convert values between different modes, such as temperature and distance. The GUI interface enhances the program's user-friendliness but is separate from the core functionality of performing conversions.

## Software Requirements Specification (SRS)

The Software Requirements Specification (SRS) document outlines the project's requirements and design. It provides a comprehensive description of the converter class hierarchy, the GUI interface, and the main method. The SRS serves as a guide for understanding the project's scope and objectives.

To view the complete Software Requirements Specification (SRS) document, please refer to the [SRS.md](SRS.md) file in this repository.

## Converter Class Hierarchy

The converter class hierarchy consists of the following classes:

### 1. Converter Class

- The `Converter` class serves as the parent class for specific converter classes.
- It includes a private attribute for the input, which is a double value.
- The class provides a default constructor and an overloaded constructor with an input parameter.
- It also includes a `convert()` method that returns the input value.

### 2. TemperatureConverter Class

- The `TemperatureConverter` class is a child class of `Converter`.
- It includes constructors that call the parent constructors.
- The class overrides the `convert()` method to convert the input Fahrenheit temperature to Celsius using the formula: C = ((F-32) * 5)/9.
- If the instance has no input value or the input is not a number, it returns Double.NaN.

### 3. DistanceConverter Class

- The `DistanceConverter` class is a child class of `Converter`.
- It includes constructors that call the parent constructors.
- The class overrides the `convert()` method to convert the input distance in miles to kilometers using the formula: KM = M * 1.609.
- If the instance has no input value or the input is not a number, it returns Double.NaN.

To view the complete Java implementation of the converter class hierarchy, please refer to the [CMIS242ASG3BostwickC.java](CMIS242ASG3BostwickC.java) file in this repository.

## GUIConverter Class

The `GUIConverter` class provides the main graphical user interface (GUI) for the program. It is implemented using JFrame and JPanel to create a user-friendly interface. The GUI features include:

- The main window is centered on the computer screen.
- Three buttons: Distance, Temperature, and Exit.
- Clicking the Exit button terminates the program.
- Clicking the Distance button displays an input dialog where the user can enter a value.
- After clicking OK, a message dialog displays the converted result.
- Clicking OK on the message dialog returns the program to the original window.
- Clicking the Temperature button displays an input dialog to input a value.
- After clicking OK, a message dialog pops up with the converted result.
- The window is appropriately sized, with buttons of equal dimensions surrounded by a maximum of ¼ to ½ inch of space.

To view the complete Java implementation of the GUIConverter class, please refer to the [CMIS242ASG3BostwickC.java](CMIS242ASG3BostwickC.java) file in this repository.

## Usage

To run the GUI & Polymorphism program, follow these steps:

1. Make sure you have Java Development Kit (JDK) installed on your machine.
2. Compile the `CMIS242ASG3BostwickC.java` file using the following command: javac CMIS242ASG3BostwickC.java

3. Run the compiled file using the following command: java CMIS242ASG3BostwickC


4. The GUI interface will be displayed, and you can interact with the program by clicking the Distance, Temperature, or Exit buttons.

## Requirements

To run the GUI & Polymorphism program, you need to have the following software installed on your machine:

- Java Development Kit (JDK)

## Notes

- The provided implementation in the `CMIS242ASG3BostwickC.java` file is a partial implementation based on the specified requirements in the Software Requirements Specification (SRS). Additional code may be required to ensure the program works as intended and meets all requirements.
- The project adheres to recommended coding style practices, including meaningful variable names, proper spacing, and the use of comments for clarity and documentation.
- Javadoc comments are provided at the beginning of the program, including the author's name, date, and a brief program description.
- Comments are used to describe variables and code blocks, following the documentation guidelines.
- Class names are written in upper CamelCase, and constants are written in all capitals.

Thank you for considering my GUI & Polymorphism project. If you have any questions or would like to see additional code samples, please feel free to contact me.



