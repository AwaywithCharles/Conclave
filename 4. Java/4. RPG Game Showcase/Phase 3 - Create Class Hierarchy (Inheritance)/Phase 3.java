/*
 * Purpose of Program: Demonstrate inheritance via weapon class
 * Author: Charles Bostwick
 * Date: 6/3/2023
 * Modifications:  
 */

import java.util.Scanner;

// Parent class "Weapon"
class Weapon {
    private int damage; // Attribute, stores the weapon's damage

    // damage constroter/initialization
    public Weapon(int damage) {
        this.damage = damage;
    }

    // attack method
    public void attack() {
        System.out.println("Attacking with the weapon and causing " + damage + " damage!");
    }
}

// Child class "Sword" (inherits weapon)
class Sword extends Weapon {
    // initialization constructor
    public Sword(int damage) {
        super(damage); // Call parent class constuctor
    }
}

// Child class "Bow"  (inherits weapon)
class Bow extends Weapon {
    // initialization constructor
    public Bow(int damage) {
        super(damage); // Call parent class constuctor
    }

    // Method to shoot an arrow
    public void shootArrow() {
        System.out.println("Shooting an arrow!");
    }
}

public class Wk3BostwickC {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // enter sword damage
        System.out.print("Enter damage for the sword: ");
        int swordDamage = input.nextInt();
        Sword mySword = new Sword(swordDamage);

        // enter bow damage
        System.out.print("Enter damage for the bow: ");
        int bowDamage = input.nextInt();
        Bow myBow = new Bow(bowDamage);

        input.close();

        // attack with the sword
        mySword.attack();

        // attack with the bow and shooting an arrow
        myBow.attack();
        myBow.shootArrow();
    }
}
