# Software Requirements Specification (SRS) for Book Inventory in Java

## 1. Introduction

### 1.1 Purpose
The purpose of this document is to provide a comprehensive Software Requirements Specification (SRS) for developing a Java program called Book Inventory. The program allows users to manage a list of books, providing functionalities for adding, removing, finding, and displaying books in the inventory. This document outlines the functional and non-functional requirements, design constraints, and system behavior of the program.

### 1.2 Scope
The Book Inventory program will be implemented using the Java programming language. It aims to provide an efficient and user-friendly interface for managing a list of books. The program enforces specific requirements for the book attributes and ensures proper error handling and documentation.

## 2. Functional Requirements

### 2.1 Book Class

#### 2.1.1 Attribute: Book ID
- The Book class should have an attribute called "id" representing the book's ID.
- The ID should be a five-digit number (string).

#### 2.1.2 Attribute: Book Title
- The Book class should have an attribute called "title" representing the book's title.
- The title should allow for multiple words and be of string type.

#### 2.1.3 Attribute: Book Price
- The Book class should have an attribute called "price" representing the book's price.
- The price should be a floating-point number (float or double).

#### 2.1.4 Constructor and Getter Methods
- The Book class should have a constructor that initializes the book object with the provided ID, title, and price.
- Getter methods should be provided to allow access to the book's ID, title, and price.

### 2.2 Inventory Class

#### 2.2.1 Book List
- The Inventory class should store and manage a list of Book objects using an ArrayList.

#### 2.2.2 Functionality: Add Book
- The Inventory class should provide a functionality to add a book to the inventory list.
- The user should be prompted for book data (ID, title, and price).
- The book should be added to the inventory list if all entries are correct.

#### 2.2.3 Functionality: Remove Book
- The Inventory class should provide a functionality to remove a book from the inventory list.
- The user should be prompted for a book ID.
- The corresponding book in the inventory should be found and removed from the list.

#### 2.2.4 Functionality: Find Book
- The Inventory class should provide a functionality to find a book in the inventory list.
- The user should be prompted for a book ID.
- The corresponding book in the inventory should be found and its information (ID, title, and price) should be displayed.

#### 2.2.5 Functionality: Display All Books
- The Inventory class should provide a functionality to display all book information in the inventory list.

### 2.3 Main Method

#### 2.3.1 Program Entry Point
- The main method should serve as the program's entry point.

#### 2.3.2 User Interface
- The main method should provide a user-friendly interface with the following menu options: Add book, Remove book, Find book, Display all books, and Exit program.
- The user should be able to select an option from the menu.
- Based on the selected option, the corresponding functionality/method should be called.
- The menu should be continuously displayed until the user chooses to exit the program.

#### 2.3.3 Error Handling
- Proper error handling should be implemented to handle incorrect input or invalid book IDs provided by the user.
- Error messages should be displayed to guide the user and ensure proper program execution.

## 3. Non-functional Requirements

### 3.1 Coding Style
- The Java program should follow recommended coding style practices, including meaningful variable names, proper spacing, and the use of comments for clarity and documentation.

### 3.2 User-Friendly Interface
- The program should have a clean and simple user interface.
- Clear prompts, confirmation messages, and appropriate error messages should be provided to guide the user throughout the interaction.

### 3.3 Efficiency
- The program should be coded efficiently, avoiding process coding and maintaining a modular structure with reusable methods.

### 3.4 Documentation
- The project should include proper documentation, such as Javadoc comments and comments for variables and code blocks.

## 4. Glossary

- Java: A high-level programming language widely used for developing various applications.
- ArrayList: A class in Java's collection framework used to create dynamic arrays that can be modified at runtime.
- ID: Identifier, a unique value used to represent and differentiate entities.
- String: A data type in Java used to represent a sequence of characters.
- Float/Double: Floating-point data types in Java used to represent decimal numbers with varying precision.
