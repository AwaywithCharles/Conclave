/*
 * Purpose of Program: To model a weapon as a Java class
 * Author: Charles Bostwick
 * Date: 5/17/2023
 * Modifications:
 *
 */

import java.util.Scanner;

public class WK1BostwickC {
    static class Weapon {

        // Declare variables
        private String type;
        private int damage;

        // Establish a Constructor
        public Weapon() {
            type = "Unarmed";
            damage = 1;
        } // end Weapon Constructor

        // Method to set weapon type and damage
        public void chooseWeapon() {
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter a weapon type: ");
            type = input.nextLine();
            System.out.println("Please enter damage for this weapon: ");
            damage = input.nextInt();
        } // end chooseWeapon()

        // Method to display weapon information
        public void displayWeaponInfo() {
            System.out.println("The weapon type is " + type + " and the damage it can cause is " + damage);
        } // end displayWeaponInfo()

    } // end class Weapon

    // Method to display the assignment banner
    static void displayBanner() {
        System.out.println("WK1BostwickC\n");
    } // end displayBanner()

    public static void main(String[] args) {
        // Declare an instance of the Weapon class
        Weapon myWeapon = new Weapon();

        // Call a method in the class WK1BostwickC
        displayBanner();

        // Call a method(s) in the class Weapon
        myWeapon.chooseWeapon();
        myWeapon.displayWeaponInfo();

        System.out.println("\nThank you for playing.\n");

    } // end main()

} // end WK1BostwickC