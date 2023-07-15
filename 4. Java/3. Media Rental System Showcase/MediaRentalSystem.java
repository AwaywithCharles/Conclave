/**
 * Purpose of Program: Design and implement a Media Rental application
 * demononstrate core OOP concepts via ....
 * Author: Charles Bostwick
 * Date: June 30, 2023
 * to do:
 *  - optimize main() (shrink it down and work on optimization
 *  - switch to a gui base system
 *  - work on error handling
 *  - improve documentation
 */

 import java.io.*;
 import java.util.ArrayList;
 import java.util.InputMismatchException;
 import java.util.Scanner;
 
 // Abstract class representing a generic media item
 abstract class Media {
     private final String id; // Unique identifier for media item
     private String title; // Title of media item
     private String yearPublished; // Year when media item was published
     private boolean rented; // Indicates if media item is currently rented
 
     //  Media class Constructor 
     public Media(String id, String title, String yearPublished, boolean rented) {
         // Check if ID is exactly 5 numeric characters
         if (id.length() != 5 || !id.matches("\\d{5}")) {
             throw new IllegalArgumentException("ID must be exactly 5 numeric characters.");
         }
         this.id = id;
         this.title = title;
         this.yearPublished = yearPublished;
         this.rented = rented;
     }
 
     // Getter for ID 
     public String getId() {
         return id;
     }
 
     // Getter for title
     public String getTitle() {
         return title;
     }
 
     // Setter for title 
     public void setTitle(String newTitle) {
         this.title = newTitle;
     }
 
     // Getter for year 
     public String getYearPublished() {
         return yearPublished;
     }
 
     // Setter for year 
     public void setYearPublished(String newYearPublished) {
         // Check if the year is a 4-digit number
         if (newYearPublished.length() != 4 || !newYearPublished.matches("\\d{4}")) {
             throw new IllegalArgumentException("Year must be a 4-digit number.");
         }
         this.yearPublished = newYearPublished;
     }
 
     // Getter for rental status
     public boolean isRented() {
         return rented;
     }
 
     // Setter the rental status
     public void setRented(boolean rented) {
         this.rented = rented;
     }
 
     // Abstract method to calculate rental fee for media item
     public abstract double calculateRentalFee();
 
     // Display details of media item
     public void display() {
         System.out.printf("%-6s%-8s%-8s%-23s%-6s%.2f%n", getId(), isRented() ? "Y" : "N", getType(), getTitle(), getYearPublished(), calculateRentalFee());
     }
 
     public void saveData(PrintWriter writer) {
         writer.printf("%s, %s, %s, %s%n", getId(), isRented() ? "Y" : "N", getType(), getTitle(), getYearPublished());
     }
 
     public void loadData(String line) {
         String[] fields = line.split(", ");
         setTitle(fields[3]);
         setYearPublished(fields[4]);
         setRented(fields[1].equalsIgnoreCase("Y"));
     }
 
     public abstract String getType();
 }
 
 // Ebook Class
 class EBook extends Media {
     private int numberOfChapters; // Number of chapters in the Ebook
 
     // EBook class constructor 
     public EBook(String id, String title, String yearPublished, int numberOfChapters, boolean rented) {
         super(id, title, yearPublished, rented);
         this.numberOfChapters = numberOfChapters;
     }
 
     // Getter for number of chapters in Ebook
     public int getNumberOfChapters() {
         return numberOfChapters;
     }
 
     // Setter for number of chapters in Ebook
     public void setNumberOfChapters(int numberOfChapters) {
         this.numberOfChapters = numberOfChapters;
     }
 
     // Calculate rental fee for Ebook
     @Override
     public double calculateRentalFee() {
         double fee = numberOfChapters * 0.10 + 1.50;
         if (Integer.parseInt(getYearPublished()) > 2015) {
             fee += 1.00;
         }
         return fee;
     }
 
     // Display details of Ebook
     @Override
     public void display() {
         super.display();
         System.out.printf("Number of Chapters: %d%n", getNumberOfChapters());
         System.out.printf("Rental Fee: $%.2f%n", calculateRentalFee());
     }
 
     // Save data of Ebook to PrintWriter
     @Override
     public void saveData(PrintWriter writer) {
         writer.printf("%s, %s, %s, %s, %d%n", getId(), isRented() ? "Y" : "N", getType(), getTitle(), getYearPublished(), getNumberOfChapters());
     }
 
     // Load data of Ebook from text
     @Override
     public void loadData(String line) {
         super.loadData(line);
         String[] fields = line.split(", ");
         setNumberOfChapters(Integer.parseInt(fields[5]));
     }
 
     // Get type of media item (E - Ebook)
     @Override
     public String getType() {
         return "E";
     }
 }
 
 // Music CD Class representing 
 class MusicCD extends Media {
     private int lengthInMinutes; // Length of the Music CD in minutes
 
     // MusicCD Constructor
     public MusicCD(String id, String title, String yearPublished, int lengthInMinutes, boolean rented) {
         super(id, title, yearPublished, rented);
         this.lengthInMinutes = lengthInMinutes;
     }
 
     // Getter for the length of Music CD in minutes
     public int getLengthInMinutes() {
         return lengthInMinutes;
     }
 
     // Setter for the length Music CD in minutes
     public void setLengthInMinutes(int lengthInMinutes) {
         this.lengthInMinutes = lengthInMinutes;
     }
 
     // Calculate the rental fee for the Music CD
     @Override
     public double calculateRentalFee() {
         double fee = lengthInMinutes * 0.045 + 1.50;
         if (Integer.parseInt(getYearPublished()) > 2014) {
             fee += 2.00;
         }
         return fee;
     }
 
     // Display details of Music CD
     @Override
     public void display() {
         super.display();
         System.out.println("Length in Minutes: " + lengthInMinutes);
     }
 
     // Save the data Music CD to PrintWriter
     @Override
     public void saveData(PrintWriter writer) {
         writer.printf("%s, %s, %s, %s, %d%n", getId(), isRented() ? "Y" : "N", getType(), getTitle(), getYearPublished(), getLengthInMinutes());
     }
 
     // Load the data of Music CD
     @Override
     public void loadData(String line) {
         super.loadData(line);
         String[] fields = line.split(", ");
         setLengthInMinutes(Integer.parseInt(fields[5]));
     }
 
     // Get the type of media item 
     @Override
     public String getType() {
         return "C";
     }
 }
 
 // Movie DVD Class
 class MovieDVD extends Media {
     private int sizeInMegabytes; 
 
     // Constructor 
     public MovieDVD(String id, String title, String yearPublished, int sizeInMegabytes, boolean rented) {
         super(id, title, yearPublished, rented);
         this.sizeInMegabytes = sizeInMegabytes;
     }
 
     // Getter for size of Movie DVD in megabytes
     public int getSizeInMegabytes() {
         return sizeInMegabytes;
     }
 
     // Setter for size of Mvie DVD in megabytes
     public void setSizeInMegabytes(int sizeInMegabytes) {
         this.sizeInMegabytes = sizeInMegabytes;
     }
 
     // Calculate rental fee
     @Override
     public double calculateRentalFee() {
         double fee = 3.25 + 1.50;
         if (Integer.parseInt(getYearPublished()) > 2019) {
             fee += 5.00;
         }
         return fee;
     }
 
     // Display details
     @Override
     public void display() {
         super.display();
         System.out.println("Size in Megabytes: " + sizeInMegabytes);
     }
 
     // Save the data 
     @Override
     public void saveData(PrintWriter writer) {
         writer.printf("%s, %s, %s, %s, %d%n", getId(), isRented() ? "Y" : "N", getType(), getTitle(), getYearPublished(), getSizeInMegabytes());
     }
 
     // Load the data 
     @Override
     public void loadData(String line) {
         super.loadData(line);
         String[] fields = line.split(", ");
         setSizeInMegabytes(Integer.parseInt(fields[5]));
     }
 
     // Get
     @Override
     public String getType() {
         return "D";
     }
 }
 
 // Class representing the media manager
 class MediaManager {
     private ArrayList<Media> mediaLibrary; 
 
     // Constructor 
     public MediaManager() {
         mediaLibrary = new ArrayList<>();
     }
 
     // Add a media item
     public void addMedia(Media media) {
         mediaLibrary.add(media);
     }
 
     // Search for a media item by ID
     public Media searchMedia(String id) {
         for (Media media : mediaLibrary) {
             if (media.getId().equals(id)) {
                 return media;
             }
         }
         return null;
     }
 
     // Find and display by ID
     public void findMedia(String id) {
         Media media = searchMedia(id);
         if (media != null) {
             System.out.println("Media found:");
             media.display();
         } else {
             System.out.println("Media with ID " + id + " not found.");
         }
     }
 
     // Remove a media item by ID
     public void removeMedia(String id) {
         Media media = searchMedia(id);
         if (media != null) {
             mediaLibrary.remove(media);
             System.out.println("Media with ID " + id + " has been removed.");
         } else {
             System.out.println("Media with ID " + id + " not found.");
         }
     }
 
     // Rent a media item by ID
     public void rentMedia(String id) {
         Media media = searchMedia(id);
         if (media != null) {
             media.setRented(true);
             System.out.println("Media with ID " + id + " has been rented.");
         } else {
             System.out.println("Media with ID " + id + " not found.");
         }
     }
 
     // Modify the details by ID
     public void modifyMedia(String id) {
         Media media = searchMedia(id);
         if (media != null) {
             Scanner scanner = new Scanner(System.in);
             System.out.print("Enter new title: ");
             String newTitle = scanner.nextLine();
             System.out.print("Enter new year published: ");
             String newYearPublished = scanner.nextLine();
             System.out.print("Is the media rented? (Y/N): ");
             String rentedInput = scanner.nextLine();
             boolean rented = rentedInput.equalsIgnoreCase("Y");
 
             media.setTitle(newTitle);
             media.setYearPublished(newYearPublished);
             media.setRented(rented);
             System.out.println("Media with ID " + id + " has been modified.");
             System.out.println("New rental fee: $" + media.calculateRentalFee());
         } else {
             System.out.println("Media with ID " + id + " not found.");
         }
     }
 
     // Display the details by ID
     public void displayOneMedia(String id) {
         Media media = searchMedia(id);
         if (media != null) {
             media.display();
         } else {
             System.out.println("Media with ID " + id + " not found.");
         }
     }
 
     // Display all media items 
     public void displayAllMedia() {
         System.out.printf("%-6s%-8s%-8s%-23s%-6s%-9s%n", "ID", "Rented", "Type", "Title", "Year", "Rental Fee");
         for (Media media : mediaLibrary) {
             media.display();
             System.out.println();
         }
     }
 
     // Display specific type
     public void displayMediaByType(char mediaType) {
         boolean found = false;
         for (Media media : mediaLibrary) {
             if (media.getType().charAt(0) == mediaType) {
                 media.display();
                 found = true;
             }
         }
         if (!found) {
             System.out.println("No media of type " + mediaType + " found.");
         }
     }
 
     // Save the data of the media library to a file (testing for week 8 post)
     public void saveData(String filename) {
         try {
             FileWriter writer = new FileWriter(filename);
             PrintWriter printWriter = new PrintWriter(writer);
 
             for (Media media : mediaLibrary) {
                 media.saveData(printWriter);
             }
 
             printWriter.close();
             System.out.println("Data saved successfully.");
         } catch (IOException e) {
             System.out.println("Error occurred while saving data: " + e.getMessage());
         }
     }
 
     // Load data from a file into the media library (testing for week 8 post)
     public void loadData(String filename) {
         try {
             FileReader reader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(reader);
 
             String line;
             while ((line = bufferedReader.readLine()) != null) {
                 String[] fields = line.split(", ");
                 if (fields.length < 5) {
                     System.out.println("Invalid data format: " + line);
                     continue;
                 }
 
                 String id = fields[0];
                 String title = fields[3];
                 String yearPublished = fields[4];
                 char mediaType = fields[2].charAt(0);
                 boolean rented = fields[1].equalsIgnoreCase("Y");
 
                 try {
                     if (mediaType == 'E') {
                         if (fields.length < 6) {
                             System.out.println("Invalid data format: " + line);
                             continue;
                         }
                         int numberOfChapters = Integer.parseInt(fields[5]);
                         EBook eBook = new EBook(id, title, yearPublished, numberOfChapters, rented);
                         addMedia(eBook);
                     } else if (mediaType == 'C') {
                         if (fields.length < 6) {
                             System.out.println("Invalid data format: " + line);
                             continue;
                         }
                         int lengthInMinutes = Integer.parseInt(fields[5]);
                         MusicCD musicCD = new MusicCD(id, title, yearPublished, lengthInMinutes, rented);
                         addMedia(musicCD);
                     } else if (mediaType == 'D') {
                         if (fields.length < 6) {
                             System.out.println("Invalid data format: " + line);
                             continue;
                         }
                         int sizeInMegabytes = Integer.parseInt(fields[5]);
                         MovieDVD movieDVD = new MovieDVD(id, title, yearPublished, sizeInMegabytes, rented);
                         addMedia(movieDVD);
                     } else {
                         System.out.println("Invalid media type: " + mediaType);
                     }
                 } catch (NumberFormatException e) {
                     System.out.println("Invalid numeric data format: " + line);
                 }
             }
 
             bufferedReader.close();
             System.out.println("Data loaded successfully.");
         } catch (FileNotFoundException e) {
             System.out.println("File not found: " + e.getMessage());
         } catch (IOException e) {
             System.out.println("Error occurred while loading data: " + e.getMessage());
         }
     }
 }
 
 // Main class 
 public class CMIS242ProjectBostwickC {
     public static void main(String[] args) {
         MediaManager mediaManager = new MediaManager();
         mediaManager.loadData("media.txt");
 
         Scanner scanner = new Scanner(System.in);
         int choice;
         do {
             displayMenu();
             System.out.print("Enter your choice: ");
             try {
                 choice = scanner.nextInt();
                 scanner.nextLine(); 
 
                 switch (choice) {
                     case 1:
                         System.out.print("Enter media ID (must be 5 digits): ");
                         String mediaId = scanner.nextLine();
                         if (mediaId.length() != 5 || !mediaId.matches("\\d{5}")) {
                             System.out.println("Invalid ID. ID must be exactly 5 numeric characters.");
                             break;
                         }
                         System.out.print("Enter media title: ");
                         String title = scanner.nextLine();
                         System.out.print("Enter year published (must be 4 digits): ");
                         String yearPublished = scanner.nextLine();
                         if (yearPublished.length() != 4 || !yearPublished.matches("\\d{4}")) {
                             System.out.println("Invalid year. Year must be a 4-digit number.");
                             break;
                         }
                         System.out.print("Is the media rented? (Y/N): ");
                         String rentedInput = scanner.nextLine().toLowerCase();
                         boolean rented = rentedInput.equals("y") || rentedInput.equals("yes");
                         System.out.print("Enter media model (E - Ebook, C - Music CD, D - Movie DVD): ");
                         String mediaModel = scanner.nextLine().toUpperCase();
                         if (!mediaModel.equals("E") && !mediaModel.equals("C") && !mediaModel.equals("D")) {
                             System.out.println("Invalid media model: " + mediaModel);
                             break;
                         }
                         System.out.print("Enter scope media (number of chapters, length in minutes, or size in megabytes): ");
                         int scopeMedia = scanner.nextInt();
                         scanner.nextLine(); // Consume newline character
 
                         if (mediaModel.equals("E")) {
                             EBook eBook = new EBook(mediaId, title, yearPublished, scopeMedia, rented);
                             mediaManager.addMedia(eBook);
                             System.out.println("EBook added to the media library.");
                         } else if (mediaModel.equals("C")) {
                             MusicCD musicCD = new MusicCD(mediaId, title, yearPublished, scopeMedia, rented);
                             mediaManager.addMedia(musicCD);
                             System.out.println("Music CD added to the media library.");
                         } else if (mediaModel.equals("D")) {
                             MovieDVD movieDVD = new MovieDVD(mediaId, title, yearPublished, scopeMedia, rented);
                             mediaManager.addMedia(movieDVD);
                             System.out.println("Movie DVD added to the media library.");
                         }
                         break;
                     case 2:
                         System.out.print("Enter media ID: ");
                         String id = scanner.nextLine();
                         mediaManager.findMedia(id);
                         break;
                     case 3:
                         System.out.print("Enter media ID: ");
                         id = scanner.nextLine();
                         mediaManager.removeMedia(id);
                         break;
                     case 4:
                         System.out.print("Enter media ID: ");
                         id = scanner.nextLine();
                         mediaManager.rentMedia(id);
                         break;
                     case 5:
                         System.out.print("Enter media ID: ");
                         id = scanner.nextLine();
                         mediaManager.modifyMedia(id);
                         break;
                     case 6:
                         System.out.print("Enter media ID: ");
                         id = scanner.nextLine();
                         mediaManager.displayOneMedia(id);
                         break;
                     case 7:
                         System.out.print("Enter media type (E - Ebook, C - Music CD, D - Movie DVD): ");
                         char mediaType = scanner.nextLine().toUpperCase().charAt(0);
                         mediaManager.displayMediaByType(mediaType);
                         break;
                     case 8:
                         mediaManager.displayAllMedia();
                         break;
                     case 9:
                         mediaManager.saveData("media.txt");
                         break;
                     case 10:
                         mediaManager.loadData("media.txt");
                         break;
                     case 11:
                         System.out.println("Exiting program...");
                         break;
                     default:
                         System.out.println("Invalid choice. Please try again.");
                 }
             } catch (InputMismatchException e) {
                 System.out.println("Invalid input. Please enter a number.");
                 scanner.nextLine();
                 choice = 0;
             }
         } while (choice != 11);
     }
 
     // Display the menu options
     public static void displayMenu() {
         System.out.println("--------- CMIS242 Media Manager ---------");
         System.out.println("1. Add media");
         System.out.println("2. Find media");
         System.out.println("3. Remove media");
         System.out.println("4. Rent media");
         System.out.println("5. Modify media");
         System.out.println("6. Display one media by ID");
         System.out.println("7. Display all by media type");
         System.out.println("8. Display entire media library");
         System.out.println("9. Save data");
         System.out.println("10. Load data");
         System.out.println("11. Exit");
         System.out.println("--------- By Charles Bostwick ---------");
     }
 }
 