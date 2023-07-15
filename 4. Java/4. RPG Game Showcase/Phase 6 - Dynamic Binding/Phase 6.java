/*
 * Purpose of Program: Demonstrate dynamic binding via Real and Practice weapons
 * Author: Charles Bostwick
 * Date: 6/19/2023
 * Modifications:  
 */

 import javax.swing.*;
 import java.awt.*;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.Random;
 import java.util.Set;
 
 // Weapon class (parent class)
 class Weapon {
     protected String name;
     protected int damage;
 
     public Weapon(String name, int damage) {
         this.name = name;
         this.damage = damage;
     }
 
     public int getWeaponDamage() {
         return damage;
     }
 
     public void attack() {
         // Display attack message in GUI instead of console
     }
 }
 
 // PracticeWeapon class (child class of Weapon)
 class PracticeWeapon extends Weapon {
     public PracticeWeapon(String name, int damage) {
         super(name, damage);
     }
 
     @Override
     public int getWeaponDamage() {
         return (int) (super.getWeaponDamage() * 0.5); // Reduce damage by half for practice weapons
     }
 }
 
 // RealWeapon class (child class of Weapon)
 class RealWeapon extends Weapon {
     public RealWeapon(String name, int damage) {
         super(name, damage);
     }
 
     @Override
     public int getWeaponDamage() {
         return (int) (super.getWeaponDamage() * 1.5); // Increase damage by 50% for real weapons
     }
 }
 
 public class Wk6BostwickC {
     private static Set<String> weapons = new HashSet<>();
     private static ArrayList<Integer> numbers = new ArrayList<>();
     private static Random rand = new Random();
     private static int lastRoll;
     private static int swordOldDamage;
     private static int axeOldDamage;
     private static int bowOldDamage;
     private static JLabel lastRollLabel;
     private static String selectedProfession;
     private static JLabel professionLabel;
     private static JLabel bowDamageLabel;
     private static JLabel axeDamageLabel;
     private static JLabel swordDamageLabel;
     private static JTextArea attackTextArea;
     private static boolean isRealWeapons = true;
 
     private static void createProfessionSelectionGUI() {
         JFrame frame = new JFrame("Profession Selection");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(600, 600);
         frame.setLocationRelativeTo(null);
 
         JPanel panel = new JPanel();
         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
 
         JLabel titleLabel = new JLabel("Wk5BostwickC Discussion");
         titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         panel.add(titleLabel);
 
         JLabel professionSelectLabel = new JLabel("Select a profession:");
         professionSelectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         panel.add(professionSelectLabel);
 
         JButton hunterButton = new JButton("Hunter");
         hunterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         hunterButton.addActionListener(e -> {
             selectedProfession = "Hunter";
             frame.dispose();
             createGUI();
         });
         panel.add(hunterButton);
 
         JButton lumberjackButton = new JButton("Lumberjack");
         lumberjackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
         lumberjackButton.addActionListener(e -> {
             selectedProfession = "Lumberjack";
             frame.dispose();
             createGUI();
         });
         panel.add(lumberjackButton);
 
         frame.getContentPane().add(panel);
         frame.setVisible(true);
     }
 
     private static void createGUI() {
         JFrame frame = new JFrame("Weapon Selection");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(600, 600);
         frame.setLocationRelativeTo(null);
 
         createPanel(frame);
 
         frame.setVisible(true);
     }
 
     // panels and buttons
     private static void createPanel(JFrame frame) {
         JPanel panel = new JPanel();
         panel.setLayout(new BorderLayout());
 
         JPanel infoPanel = new JPanel();
         infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
 
         professionLabel = new JLabel("Selected Profession: " + selectedProfession);
         professionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
         infoPanel.add(professionLabel);
 
         lastRollLabel = new JLabel("Last roll: -");
         infoPanel.add(lastRollLabel);
 
         bowDamageLabel = new JLabel("Bow Damage: -");
         infoPanel.add(bowDamageLabel);
 
         axeDamageLabel = new JLabel("Axe Damage: -");
         infoPanel.add(axeDamageLabel);
 
         swordDamageLabel = new JLabel("Sword Damage: -");
         infoPanel.add(swordDamageLabel);
 
         panel.add(infoPanel, BorderLayout.NORTH);
 
         JPanel buttonsPanel = new JPanel();
         buttonsPanel.setLayout(new BorderLayout());
 
         JPanel weaponsPanel = new JPanel();
         weaponsPanel.setLayout(new GridLayout(3, 1, 0, 5));
 
         JButton bowButton = new JButton("Bow");
         bowButton.setPreferredSize(new Dimension(100, 30));
         bowButton.addActionListener(e -> {
             attackTextArea.setText("Bow Damage: " + getWeaponDamage("Bow") + "\n");
         });
         weaponsPanel.add(bowButton);
 
         JButton axeButton = new JButton("Axe");
         axeButton.setPreferredSize(new Dimension(100, 30));
         axeButton.addActionListener(e -> {
             attackTextArea.setText("Axe Damage: " + getWeaponDamage("Axe") + "\n");
         });
         weaponsPanel.add(axeButton);
 
         JButton swordButton = new JButton("Sword");
         swordButton.setPreferredSize(new Dimension(100, 30));
         swordButton.addActionListener(e -> {
             attackTextArea.setText("Sword Damage: " + getWeaponDamage("Sword") + "\n");
         });
         weaponsPanel.add(swordButton);
 
         buttonsPanel.add(weaponsPanel, BorderLayout.WEST);
 
         JPanel testButtonsPanel = new JPanel();
         testButtonsPanel.setLayout(new GridLayout(3, 1, 0, 5));
 
         JButton testBowButton = new JButton("Test Bow");
         testBowButton.setPreferredSize(new Dimension(100, 30));
         testBowButton.addActionListener(e -> {
             Weapon weapon = createWeaponInstance("Bow");
             attackTextArea.append("You shoot an arrow at a wooden log for " + weapon.getWeaponDamage() + " damage" + "\n");
         });
         testButtonsPanel.add(testBowButton);
 
         JButton testAxeButton = new JButton("Test Axe");
         testAxeButton.setPreferredSize(new Dimension(100, 30));
         testAxeButton.addActionListener(e -> {
             Weapon weapon = createWeaponInstance("Axe");
             attackTextArea.append("You chop at a log with " + weapon.getWeaponDamage() + " damage" + "\n");
         });
         testButtonsPanel.add(testAxeButton);
 
         JButton testSwordButton = new JButton("Test Sword");
         testSwordButton.setPreferredSize(new Dimension(100, 30));
         testSwordButton.addActionListener(e -> {
             Weapon weapon = createWeaponInstance("Sword");
             attackTextArea.append("You swing at a wooden log for " + weapon.getWeaponDamage() + " damage" + "\n");
         });
         testButtonsPanel.add(testSwordButton);
 
         buttonsPanel.add(testButtonsPanel, BorderLayout.EAST);
 
         panel.add(buttonsPanel, BorderLayout.CENTER);
 
         attackTextArea = new JTextArea();
         attackTextArea.setEditable(false);
         attackTextArea.setLineWrap(true);
         attackTextArea.setWrapStyleWord(true);
         JScrollPane scrollPane = new JScrollPane(attackTextArea);
         scrollPane.setPreferredSize(new Dimension(380, 200));
         panel.add(scrollPane, BorderLayout.SOUTH);
 
         JPanel bottomPanel = new JPanel();
 
         JButton diceRollButton = new JButton("Dice Roll");
         diceRollButton.setPreferredSize(new Dimension(120, 30));
         diceRollButton.addActionListener(e -> {
             lastRoll = generateRandomNumber(1, 6);
             lastRollLabel.setText("You rolled a " + lastRoll);
             numbers.add(lastRoll);
             updateDamageLabels();
             attackTextArea.setText("Now updating weapons values\n");
 
             int swordNewDamage = getWeaponDamage("Sword");
             attackTextArea.append("Sword Damage updated from " + swordOldDamage + " to " + swordNewDamage + "\n");
             swordOldDamage = swordNewDamage;
 
             int axeNewDamage = getWeaponDamage("Axe");
             attackTextArea.append("Axe Damage updated from " + axeOldDamage + " to " + axeNewDamage + "\n");
             axeOldDamage = axeNewDamage;
 
             int bowNewDamage = getWeaponDamage("Bow");
             attackTextArea.append("Bow Damage updated from " + bowOldDamage + " to " + bowNewDamage + "\n");
             bowOldDamage = bowNewDamage;
         });
         bottomPanel.add(diceRollButton);
 
         JButton changeProfessionButton = new JButton("Change Profession");
         changeProfessionButton.addActionListener(e -> {
             frame.dispose();
             createProfessionSelectionGUI();
         });
         bottomPanel.add(changeProfessionButton);
 
         JButton weaponTypeButton = new JButton("Switch Weapon Type");
         weaponTypeButton.addActionListener(e -> {
             isRealWeapons = !isRealWeapons;
             weaponTypeButton.setText(isRealWeapons ? "Switch to Practice Weapons" : "Switch to Real Weapons");
             updateDamageLabels();
             attackTextArea.append("Weapon type switched to " + (isRealWeapons ? "Real Weapons" : "Practice Weapons") + "\n");
         });
         bottomPanel.add(weaponTypeButton);
 
         JButton exitButton = new JButton("Exit");
         exitButton.addActionListener(e -> System.exit(0));
         bottomPanel.add(exitButton);
 
         frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
         frame.getContentPane().add(panel);
     }
 
 
     private static int generateRandomNumber(int min, int max) {
         return rand.nextInt((max - min) + 1) + min;
     }
 
     private static int getWeaponDamage(String weapon) {
         double damageMultiplier = 1 + (lastRoll - 1) * 0.2;
         int baseDamage = getBaseWeaponDamage(weapon);
         return (int) (baseDamage * damageMultiplier);
     }
 
     private static int getBaseWeaponDamage(String weapon) {
         switch (weapon) {
             case "Bow":
                 return isRealWeapons ? 15 : 10; // Adjust the base damage for real and practice weapons
             case "Axe":
                 return isRealWeapons ? 12 : 8;
             case "Sword":
                 return isRealWeapons ? 10 : 6;
             default:
                 return 0;
         }
     }
 
     // Dynamic binding demonstrated here via real vs practice weapon 
     private static Weapon createWeaponInstance(String weapon) {
         int baseDamage = getBaseWeaponDamage(weapon);
         if (isRealWeapons) {
             return new RealWeapon(weapon, baseDamage);
         } else {
             return new PracticeWeapon(weapon, baseDamage);
         }
     }
 
     private static void updateDamageLabels() {
         bowDamageLabel.setText("Bow Damage: " + getWeaponDamage("Bow"));
         axeDamageLabel.setText("Axe Damage: " + getWeaponDamage("Axe"));
         swordDamageLabel.setText("Sword Damage: " + getWeaponDamage("Sword"));
     }
 
     public static void main(String[] args) {
         createProfessionSelectionGUI();
     }
 }
 