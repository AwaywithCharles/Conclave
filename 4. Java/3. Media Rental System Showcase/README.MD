# Media Rental System

## Introduction

The Media Rental System is a Java program that models a media rental system. It allows users to manage and rent different types of media, including EBooks, Music CDs, and Movie DVDs. This document serves as a comprehensive Software Requirements Specification (SRS) for the development of the program.

## Scope

The Media Rental System will be implemented as a Java program with the following functionalities:

- Adding media objects
- Finding media objects by ID
- Removing media objects by ID
- Renting media objects by ID
- Modifying media object details by ID
- Displaying details of media objects
- Displaying media objects of a specific type
- Saving data to a file
- Loading data from a file

## Media Class Hierarchy

### Media Class

The `Media` class serves as the parent class for specific media classes and includes the following attributes:

- `id`: Unique identifier for the media item (exactly 5 numeric characters)
- `title`: Title of the media item (string)
- `yearPublished`: Year when the media item was published (integer or string)
- `rented`: Indicates if the media item is currently rented (boolean)

The `Media` class provides methods to get and set attribute values, calculate the rental fee, and display the details of the media item. It is declared as abstract to prevent direct creation of objects from this class.

### EBook Class

The `EBook` class is a child class of `Media` and includes the following additional attribute:

- `numberOfChapters`: Number of chapters in the EBook (integer)

The `EBook` class inherits attributes and methods from the `Media` class. It provides functionality to calculate the rental fee based on the number of chapters and additional rules. The class also includes a method to display the attribute values to the console.

### MusicCD Class

The `MusicCD` class is a child class of `Media` and includes the following additional attribute:

- `lengthInMinutes`: Length of the Music CD in minutes (integer)

The `MusicCD` class inherits attributes and methods from the `Media` class. It provides functionality to calculate the rental fee based on the length in minutes and additional rules. The class also includes a method to display the attribute values to the console.

### MovieDVD Class

The `MovieDVD` class is a child class of `Media` and includes the following additional attribute:

- `sizeInMegabytes`: Size of the Movie DVD in megabytes (integer)

The `MovieDVD` class inherits attributes and methods from the `Media` class. It provides functionality to calculate the rental fee based on additional rules. The class also includes a method to display the attribute values to the console.

## Manager Class

The `Manager` class is responsible for managing the media objects. It includes functionalities to store a list of media objects in an ArrayList, add, find, remove, rent, modify, and display media objects. The class provides menu options for each functionality, along with appropriate confirmation and error messages.

## Main Method

The `main` method serves as the entry point of the program. It instantiates the `Manager` class, declares and defines an ArrayList to hold the media library, and defines an array with fields representing the media attributes. The `main` method generates a menu for the major functions, handles user inputs, and calls the appropriate methods based on the selected menu option. It also includes code for displaying, modifying, and saving/loading media data.

## Encapsulation, Polymorphism, and Inheritance

The Media Rental System program implements encapsulation by using private/protected attributes and value validation. Polymorphism is achieved through method overloading, overriding, and dynamic binding. The program takes advantage of inheritance properties by utilizing the class hierarchy and inheriting attributes and methods from parent classes.

## Test Plan and Documentation

The program includes a test plan with multiple test cases. Each test case is referenced to a screen capture, clearly indicating the successful compilation and running of each test case. The test table contains columns for test case number, menu selection, media ID (5 digits only), media type (E, C, D), media title, year published, scope (chapters, minutes, or default), rented status, cost of rental, processing commands, and messages for errors and other information to the user.

## Style and Documentation

The Java program follows recommended coding style practices, including meaningful variable names, proper spacing, and the use of comments for clarity and documentation. Javadoc comments are provided at the beginning of the program, including the author's name, date, and a brief program description. Comments are used to describe variables and code blocks, following the documentation guidelines provided in Week 1. Class names are written in upper CamelCase, and constants are written in all capitals.

---

Code sample:

