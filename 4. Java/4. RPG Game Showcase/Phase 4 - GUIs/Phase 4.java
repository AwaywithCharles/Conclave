/*
 * Purpose of Program: Continuation of last week, implement GUI and recursion
 * Author: Charles Bostwick
 * Date: 6/7/2023
 * Modifications:  
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Parent class "Weapon"
class Weapon {
    protected int damage;

    public Weapon(int damage) {
        this.damage = damage;
    }

    public void attack() {
        System.out.println("Attacking with the weapon and causing " + damage + " damage!");
    }
}

// Child class "Sword"
class Sword extends Weapon {
    public Sword(int damage) {
        super(damage);
    }
}

// Child class "Bow"
class Bow extends Weapon {
    public Bow(int damage) {
        super(damage);
    }

    public void shootArrow() {
        System.out.println("Shooting an arrow!");
    }
}

public class Wk4BostwickC {
    private static DefaultListModel<String> model;
    private static DefaultListModel<String> probabilityModel;
    private static ArrayList<Integer> numbers = new ArrayList<>();
    private static Random rand = new Random();
    private static int lastRoll;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Weapon Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);

        // Create menu items
        JMenuItem menuItem = new JMenuItem("Generate Random Numbers");
        menu.add(menuItem);

        JPanel panel = new JPanel();

        String[] weapons = {"Sword", "Bow"};

        model = new DefaultListModel<>();
        JList<String> weaponList = new JList<>(model);

        for (String weapon : weapons) {
            model.addElement(weapon);
        }

        weaponList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(weaponList);

        weaponList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedWeapon = weaponList.getSelectedValue();
                if (selectedWeapon != null) {
                    selectWeapon(selectedWeapon);
                }
            }
        });

        probabilityModel = new DefaultListModel<>();
        JList<String> probabilityList = new JList<>(probabilityModel);

        panel.add(new JLabel("Probabilities:"));
        panel.add(probabilityList);

        JLabel lastRollLabel = new JLabel("Last roll: -");
        panel.add(lastRollLabel);

        menuItem.addActionListener(e -> {
            int randomNumber = generateRandomNumber(1, 6);
            lastRoll = randomNumber;
            lastRollLabel.setText("Last roll: " + lastRoll);
            numbers.add(randomNumber);
            updateProbabilities();
        });

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    private static void selectWeapon(String weapon) {
        double damageMultiplier = 1 + (lastRoll - 1) * 0.2;  // Adjust the damage based on the last dice roll
        if (weapon.equals("Sword")) {
            int swordDamage = (int) (10 * damageMultiplier);
            Sword mySword = new Sword(swordDamage);
            mySword.attack();
        } else if (weapon.equals("Bow")) {
            int bowDamage = (int) (15 * damageMultiplier);
            Bow myBow = new Bow(bowDamage);
            myBow.attack();
            myBow.shootArrow();
        }
    }

    private static int generateRandomNumber(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    private static void updateProbabilities() {
        probabilityModel.clear();
        for (int i = 1; i <= 6; i++) {
            int count = Collections.frequency(numbers, i);
            double probability = (double) count / numbers.size();
            probabilityModel.addElement("P(" + i + ") = " + probability);
        }
    }
}