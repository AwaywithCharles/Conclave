# Software Requirements Specification (SRS) for Media Rental System

## 1. Introduction

### 1.1 Purpose
The purpose of this document is to provide a comprehensive Software Requirements Specification (SRS) for developing a Java program that models a media rental system. The program allows users to manage and rent different types of media, including EBooks, Music CDs, and Movie DVDs. The document outlines the functional and non-functional requirements, design constraints, and system behavior of the media rental system.

### 1.2 Scope
The media rental system will be implemented as a Java program. It will provide functionality for adding, finding, removing, renting, modifying, and displaying media objects. The program will calculate rental fees based on specific criteria for each media type.

## 2. Media Class Hierarchy

### 2.1 Media Class
The Media class serves as the parent class for specific media classes and includes the following:
- Member variables (attributes): id (exactly 5 number characters), title (string that can be more than one word), year published (integer or String).
- Constructor with all attribute values.
- Functionality to calculate the rental fee ($1.50 flat fee for all transactions), change the title, change the year published, and get each attribute value.
- The Media class should be declared abstract to prevent direct creation of this class.

### 2.2 EBook Class
The EBook class is a child class of Media and includes the following:
- Member variables (attributes): inherits from the parent class.
- An attribute for the number of chapters (integer).
- Constructor with all attribute values.
- Functionality inherited from the parent class, plus the ability to display attribute values to the console.
- The rental fee for an EBook is calculated as the number of chapters * 0.10 + $1.50 (flat fee).
- If the EBook was published after the year 2015, an additional $1.00 is added to the rental fee.

### 2.3 MusicCD Class
The MusicCD class is a child class of Media and includes the following:
- Member variables (attributes): inherits from the parent class.
- An attribute for the length in minutes (integer).
- Constructor with all attribute values.
- Functionality inherited from the parent class, plus the ability to display attribute values to the console.
- The rental fee for a MusicCD is calculated as the minute length * 0.045 + $1.50 (flat fee).
- If the MusicCD was published after the year 2014, an additional $2.00 is added to the rental fee.

### 2.4 MovieDVD Class
The MovieDVD class is a child class of Media and includes the following:
- Member variables (attributes): inherits from the parent class.
- An attribute for the size in megabytes (integer).
- Constructor with all attribute values.
- Functionality inherited from the parent class, plus the ability to display attribute values to the console.
- The rental fee for a MovieDVD is a flat fee of $3.25 + $1.50.
- If the MovieDVD was published after the year 2019, the flat fee is $5.00.

## 3. Manager Class

The Manager class is responsible for managing the media objects and includes the following functionality:
- Stores a list of Media objects in an ArrayList.
- Ability to add, find, remove, rent, modify, and display media objects.
- Menu options for each functionality, including an exit option.
- Confirmation and error messages for each action.

## 4. Main Method

The main method serves as the program's entry point and includes the following:
- Instantiation of the Manager class.
- Declaration and definition of an ArrayList to hold the media library.
- Definition of an array with seven fields representing the media attributes.
- Generation of a menu for the major functions: Add, Find, Remove, Rent, Modify, and Display.
- Handling of user inputs and appropriate actions based on the selected menu option.
- All additions and removals are done on the Media ArrayList with proper confirmation or error messages.
- Displaying attribute data and rental fees for each instance.
- Changing attribute values for specific instances and displaying updated rental fees.
- Displaying the data in the modified Media ArrayList when the user requests to close the program.
- The main method should contain minimal code, with most of the functionality implemented in separate methods outside the main method.
- The switch structure should be used for menu selection, calling the appropriate methods based on user input.

## 5. Encapsulation, Polymorphism, and Inheritance

The program should implement appropriate encapsulation by using private/protected attributes and value validation.
Polymorphism should be used through method overloading, overriding, and dynamic binding.
The program should take advantage of inheritance properties where applicable.

## 6. Test Plan and Documentation

The program should include a test plan with multiple test cases.
Each test case should be referenced to a screen capture, clearly indicating the successful compilation and running of each test case.
The test table should include columns for test case number, menu selection, media ID (5 digits only), media type (C, D, E), media title, year published, scope (chapters, minutes, or 1 as default), rented status, cost of rental, processing commands, and messages for errors and other information to the user.

## 7. Style and Documentation

The Java program should follow recommended coding style practices, including meaningful variable names, proper spacing, and the use of comments for clarity and documentation.
Javadoc comments should be provided at the beginning of the program, including the author's name, date, and a brief program description.
Comments should be used to describe variables and code blocks, following the documentation guidelines provided in Week 1.
Class names should be written in upper CamelCase, and constants should be written in all capitals.
