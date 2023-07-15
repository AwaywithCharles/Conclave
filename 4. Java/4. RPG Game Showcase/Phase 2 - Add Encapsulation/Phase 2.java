/*
 * Purpose of Program: To model a weapon as a Java class with user interaction and encapsulation.
 * Author: Charles Bostwick
 * Date: 5/26/2023
 * Modifications: 
 * - Added encapsulation by making attributes private. 
 * - Control over attributes has been achieved by providing user interaction through methods instead of traditional getter and setter.
 * - The chooseWeapon method takes input from the user to set the weapon type and damage. 
 * - The displayWeaponInfo method prints out the weapon type and damage.
 * - The WeaponSimulation class is the main class that interacts with the user and facilitates the process.
 * - Improved documentation. 
 */

 import java.util.Scanner;

 public class WeaponSimulation {
 
     static class Weapon {
 
         // Private attributes for weapon type and damage to ensure encapsulation
         private String type;
         private int damage;
 
         // Default constructor setting initial weapon type and damage
         public Weapon() {
             type = "Unarmed";
             damage = 1;
         }
 
         // Method to set weapon type and damage through user interaction
         public void chooseWeapon() {
             Scanner input = new Scanner(System.in);
             System.out.println("Please enter a weapon type: ");
             this.type = input.nextLine();
             System.out.println("Please enter damage for this weapon: ");
             this.damage = input.nextInt();
         }
 
         // Method to display weapon type and damage
         public void displayWeaponInfo() {
             System.out.println("The weapon type is " + this.type + " and the damage it can cause is " + this.damage);
         }
 
     }
 
     // Method to display the assignment banner
     static void displayBanner() {
         System.out.println("Weapon Simulation\n");
     }
 
     // Main method to run the weapon simulation
     public static void main(String[] args) {
         // Declare an instance of the Weapon class
         Weapon myWeapon = new Weapon();
 
         // Display the assignment banner
         displayBanner();
 
         // User interaction with the Weapon class instance
         myWeapon.chooseWeapon();
         myWeapon.displayWeaponInfo();
 
         System.out.println("\nThank you for playing.\n");
 
     }
 
 }