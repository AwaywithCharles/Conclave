import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Purpose of Program: This program manages a book inventory, enabling users to add, remove,
 *                     find and display books in the inventory.
 * Author: Charles Bostwick
 * Date: 5/26/2023
 * To Do:
 *  - improve error handling
 *  - add about page
 *  - work on documentation
 *  - file menu exit
 *  - improve UI experience
 */
public class BookInventoryManager {
    // Books is an ArrayList that holds all the Book objects in the inventory.
    private ArrayList<Book> books;
    // Scanner for user input.
    private Scanner input;

    // Default constructor initializes books ArrayList and input scanner.
    public BookInventoryManager() {
        books = new ArrayList<>();
        input = new Scanner(System.in);
    }

    // Method to add a book to the inventory.
    public void addBook() {
        System.out.print("What is the book id (5 integers)? ");
        String bookId = input.nextLine();

        // Error checking for book id. It must be numeric and of length 5.
        while (bookId.length() != 5 || !bookId.matches("[0-9]+")) {
            System.out.println("Error: Invalid id. Please enter 5 integers.");
            System.out.print("What is the book id (5 integers)? ");
            bookId = input.nextLine();
        }

        System.out.print("What is the book title? ");
        String bookTitle = input.nextLine();

        // Initialize book price to an invalid value.
        double bookPrice = -1;
        // Error checking for book price. It must be a non-negative double value.
        while (bookPrice < 0) {
            System.out.print("What is the book price (double value)? ");
            try {
                bookPrice = input.nextDouble();
                input.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid price. Please enter a valid double value.");
                input.nextLine(); // Discard the incorrect input
            }
        }

        Book newBook = new Book(bookId, bookTitle, bookPrice);
        books.add(newBook);

        System.out.println("Book added successfully.");
    }

    // Method to remove a book from the inventory by its id.
    public void removeBook() {
        System.out.print("Enter the book id to remove: ");
        String idToRemove = input.nextLine();

        boolean isRemoved = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(idToRemove)) {
                books.remove(i);
                System.out.println("Book removed successfully.");
                isRemoved = true;
                break;
            }
        }

        if (!isRemoved) {
            System.out.println("Error: Book with the given id not found.");
        }
    }

    // Method to find a book in the inventory by its id.
    public void findBook() {
        System.out.print("Enter the book id to find: ");
        String idToFind = input.nextLine();

        boolean isFound = false;
        for (Book book : books) {
            if (book.getId().equals(idToFind)) {
                System.out.println("Book found:");
                book.displayBookInfo();
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            System.out.println("Error: Book with the given id not found.");
        }
    }

    // Method to display all books in the inventory.
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("The inventory has no books.");
        } else {
            System.out.println("Inventory Books:");
            for (Book book : books) {
                book.displayBookInfo();
            }
        }
    }

    // Method to display the menu.
    public static void displayMenu() {
        System.out.println("MENU");
        System.out.println("1: Add book");
        System.out.println("2: Remove book");
        System.out.println("3: Find book");
        System.out.println("4: Display all books");
        System.out.println("9: Exit program");
    }

    // Method to close the scanner when we are done using it.
    public void closeScanner() {
        if (input != null) {
            input.close();
        }
    }

    // Main method to manage the inventory. 
    public void manageInventory() {
        while (true) {
            displayMenu();
            System.out.print("Enter your selection: ");
            int userChoice = -1;
            try {
                userChoice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid selection. Please choose a valid option.");
                input.nextLine();
            }

            switch (userChoice) {
                case 1:
                    this.addBook();
                    break;
                case 2:
                    this.removeBook();
                    break;
                case 3:
                    this.findBook();
                    break;
                case 4:
                    this.displayAllBooks();
                    break;
                case 9:
                    System.out.println("Thank you for using the program. Goodbye!");
                    this.closeScanner();
                    return;
                default:
                    System.out.println("Error: Invalid selection. Please choose a valid option.");
            }
        }
    }

    public static void main(String[] args) {
        BookInventoryManager inventory = new BookInventoryManager();
        inventory.manageInventory();
    }
}

// Book class represents a book with an id, title, and price.
class Book {
    // Unique identifier for the book.
    private String id;
    // Title of the book.
    private String title;
    // Price of the book.
    private double price;

    // Book constructor initializes id, title, and price.
    public Book(String id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    // Returns the id of the book.
    public String getId() {
        return id;
    }

    // Returns the title of the book.
    public String getTitle() {
        return title;
    }

    // Returns the price of the book.
    public double getPrice() {
        return price;
    }

    // Method to display book information.
    public void displayBookInfo() {
        System.out.println("Book id: " + getId() + ", Title: " + getTitle() + ", Price: " + getPrice());
    }
}
