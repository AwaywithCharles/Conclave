
/**
 * this is an evolution of what ive been posting in all of the other discussions. some features still dont work but are a work in progress.
 * right now, It allows the user to create a character, battle monsters, manage inventory, and perform various actions. Also the player can save and load a character to a text file
 *
 * File: RPGGame.java
 * Author: Charles Bostwick
 * Created: 07/05/2023
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class RPGGame {
    private JFrame frame;
    private Character selectedCharacter;
    private Monster currentMonster;
    private int potionCount;
    private int experience;
    private int level;
    private int stellarCoins;
    private int silverCoins;
    private int goldCoins;

    // Save game data to a text file
    private void saveGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Game");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        int userSelection = fileChooser.showSaveDialog(frame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write("PlayerName: " + selectedCharacter.getName());
                writer.newLine();
                writer.write("GoldCoins: " + goldCoins);
                writer.newLine();
                writer.write("SilverCoins: " + silverCoins);
                writer.newLine();
                writer.write("Experience: " + experience);
                writer.newLine();
                JOptionPane.showMessageDialog(frame, "Game saved successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Failed to save game: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Load game data from *.txt
    private void loadGame() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Load Character");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
            fileChooser.setFileFilter(filter);
            int userSelection = fileChooser.showOpenDialog(frame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                reader.close();
                JOptionPane.showMessageDialog(frame, "Character loaded successfully!");
                homeScreen();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error occurred while loading the character!");
        }
    }

    public void selectCharacter(Character character) {
        selectedCharacter = character;
    }

    // Items
    static class Item {
        private String name;
        private String rarity;
        private String slot;
        private int additionalHealth;
        private int additionalAttack;
        private int additionalStrength;
        private int additionalIntelligence;
        private int additionalAgility;

        // Constructs item
        public Item(String name, String rarity, String slot, int additionalHealth, int additionalAttack,
                int additionalStrength, int additionalIntelligence, int additionalAgility) {
            this.name = name;
            this.rarity = rarity;
            this.slot = slot;
            this.additionalHealth = additionalHealth;
            this.additionalAttack = additionalAttack;
            this.additionalStrength = additionalStrength;
            this.additionalIntelligence = additionalIntelligence;
            this.additionalAgility = additionalAgility;
        }

        public String getName() {
            return name;
        }

        public String getRarity() {
            return rarity;
        }

        public String getSlot() {
            return slot;
        }

        public int getAdditionalHealth() {
            return additionalHealth;
        }

        public int getAdditionalAttack() {
            return additionalAttack;
        }

        public int getAdditionalStrength() {
            return additionalStrength;
        }

        public int getAdditionalIntelligence() {
            return additionalIntelligence;
        }

        public int getAdditionalAgility() {
            return additionalAgility;
        }

        public Map<String, Integer> getAdditionalStats() {
            Map<String, Integer> additionalStats = new HashMap<>();
            additionalStats.put("Health", additionalHealth);
            additionalStats.put("Attack", additionalAttack);
            additionalStats.put("Strength", additionalStrength);
            additionalStats.put("Intelligence", additionalIntelligence);
            additionalStats.put("Agility", additionalAgility);
            return additionalStats;
        }
    }

    private static final String[] headItems = {
            "Quantum Helm",
            "Hyper Visor",
            "Nano Headgear",
            "Molecular Crown",
            "Biotech Mask",
            "Cyber Headpiece",
            "Bionic Helmet",
            "Artificial Cap",
            "Virtual Headband",
            "Augmented Circlet"
    };

    private static final String[] chestItems = {
            "Quantum Armor",
            "Hyper Suit",
            "Nano Chestplate",
            "Molecular Vest",
            "Biotech Tunic",
            "Cyber Chestguard",
            "Bionic Breastplate",
            "Artificial Robe",
            "Virtual Jacket",
            "Augmented Plate"
    };

    private static final String[] pantsItems = {
            "Quantum Leggings",
            "Hyper Trousers",
            "Nano Pants",
            "Molecular Greaves",
            "Biotech Legguards",
            "Cyber Legplates",
            "Bionic Leggings",
            "Artificial Bottoms",
            "Virtual Tights",
            "Augmented Pants"
    };

    private static final String[] neckItems = {
            "Quantum Amulet",
            "Hyper Pendant",
            "Nano Neckpiece",
            "Molecular Talisman",
            "Biotech Medallion",
            "Cyber Charm",
            "Bionic Necklace",
            "Artificial Locket",
            "Virtual Choker",
            "Augmented Collar"
    };

    private static final String[] ringItems = {
            "Quantum Ring",
            "Hyper Band",
            "Nano Loop",
            "Molecular Circle",
            "Biotech Band",
            "Cyber Signet",
            "Bionic Seal",
            "Artificial Loop",
            "Virtual Ring",
            "Augmented Band"
    };

    private static final String[] braceletItems = {
            "Quantum Bracelet",
            "Hyper Bangle",
            "Nano Wristlet",
            "Molecular Armlet",
            "Biotech Cuff",
            "Cyber Wristguard",
            "Bionic Gauntlet",
            "Artificial Armlet",
            "Virtual Wristband",
            "Augmented Bracelet"
    };

    private static final String[] beltItems = {
            "Quantum Belt",
            "Hyper Waistband",
            "Nano Sash",
            "Molecular Girdle",
            "Biotech Belt",
            "Cyber Waistguard",
            "Bionic Buckle",
            "Artificial Strap",
            "Virtual Belt",
            "Augmented Sash"
    };

    private static final String[] bootsItems = {
            "Quantum Boots",
            "Hyper Footwear",
            "Nano Greaves",
            "Molecular Sabatons",
            "Biotech Boots",
            "Cyber Treads",
            "Bionic Footguards",
            "Artificial Shoes",
            "Virtual Slippers",
            "Augmented Boots"
    };

    private static final String[] genetics = {
            "Enhanced Musculature",
            "Accelerated Healing",
            "Neural Interface",
            "Genetic Adaptation",
            "Enhanced Senses",
            "Telepathic Resonance",
            "Molecular Regeneration",
            "Invisibility Matrix",
            "Energy Absorption",
            "Nano-Enhanced Reflexes",
            "Bioluminescent Skin",
            "Psionic Amplifier",
            "Temporal Manipulation",
            "Gravity Manipulation",
            "Energy Projection",
            "Phasing Abilities",
            "Cloning Technology",
            "Electromagnetic Control",
            "Holographic Projection",
            "Chameleon Camouflage"
    };

    private static final String[] cybernetics = {
            "Cybernetic Arm",
            "Neural Implant",
            "Optical Augmentation",
            "Artificial Heart",
            "Bionic Leg",
            "Spinal Interface",
            "Nanobot Swarm",
            "Dermal Plating",
            "Enhanced Lungs",
            "Sensory Enhancer",
            "Cybernetic Voice Modulator",
            "Muscle Fiber Enhancement",
            "Implanted Weapon System",
            "Subdermal Shield",
            "Biometric Encryption",
            "Data Link Interface",
            "Micro-Missile Launcher",
            "Adaptive Camouflage",
            "Exoskeletal Frame",
            "Neuro-Link Connector"
    };

    private static final String[] prefixes = {
            "Soul Devouring",
            "Cloudwalking",
            "Jade",
            "Celestial",
            "Dragon",
            "Phoenix",
            "Lotus",
            "Heavenly",
            "Nine-Tailed",
            "Thunderbolt",
            "Martial Spirit",
            "Golden Immortal",
            "Starlight",
            "Cosmic",
            "Infinity",
            "Serpent",
            "Divine",
            "Void",
            "Sage",
            "Moonlit",
            "Ethereal",
            "Mystic",
            "Harmony",
            "Enlightened",
            "Wisdom",
            "Emperor's",
            "Imperial",
            "Enchanting",
            "Crimson",
            "Ancient",
            "Supreme",
            "Eternal",
            "Whispering",
            "Mysterious",
            "Transcendent",
            "Luminous",
            "Resplendent",
            "Thunderous",
            "Blazing",
            "Shimmering",
            "Radiant",
            "Silent",
            "Soul Devouring",
            "Cloudwalking",
            "Jade",
            "Celestial",
            "Dragon",
            "Phoenix",
            "Lotus",
            "Heavenly",
            "Nine-Tailed",
            "Thunderbolt",
            "Martial Spirit",
            "Golden Immortal",
            "Starlight",
            "Cosmic",
            "Infinity",
            "Serpent",
            "Divine",
            "Void",
            "Sage",
            "Moonlit",
            "Ethereal",
            "Mystic",
            "Harmony",
            "Enlightened",
            "Wisdom",
            "Emperor's",
            "Imperial",
            "Enchanting",
            "Crimson",
            "Ancient",
            "Supreme",
            "Eternal",
            "Whispering",
            "Mysterious",
            "Transcendent",
            "Luminous",
            "Resplendent",
            "Thunderous",
            "Blazing",
            "Shimmering",
            "Radiant",
            "Silent"
    };

    private static final String[] suffixes = {
            "of Power",
            "of Agility",
            "of Wisdom",
            "of the Void",
            "of Destruction",
            "of Healing",
            "of the Stars",
            "of the Moon",
            "of the Sun",
            "of the Wind",
            "of the Earth",
            "of Light",
            "of Darkness",
            "of Storms",
            "of Thunder",
            "of Frost",
            "of Flames",
            "of Life",
            "of Death",
            "of the Mind",
            "of the Moon",
            "of Murder",
            "of Darkness",
            "of Exile",
            "of Justice",
            "of Redemption",
            "of Chaos",
            "of Serenity",
            "of Infinity",
            "of Eternity"
    };

    private static final String[] legendaryItems = {
            "Dragon's Fury",
            "Phoenix's Grace",
            "Tiger's Roar",
            "Monkey King's Staff",
            "Jade Emperor's Crown",
            "Nezha's Fire Wheels",
            "Nuwa's Divine Brush",
            "Guanyin's Compassion",
            "Zhulong's Flame Whip",
            "Yandi's Sword",
            "Houyi's Bow",
            "Xi Wangmu's Peach",
            "Bai Suzhen's Serpent Scale",
            "Lei Gong's Thunder Hammer",
            "Chang'e's Moonstone",
            "Pangu's Axe",
            "Jiang Ziya's Conch",
            "Zhu Bajie's Iron Fan",
            "Wukong's Golden Cudgel",
            "Yanluo's Soul Reaper"
    };

    private static final Map<String, String> renamedGenetics = new HashMap<>();
    private static final Map<String, String> renamedCybernetics = new HashMap<>();

    // Characters
    static class Character {
        private String name;
        private String race;
        private String ethnicity;
        private String religion;
        private String profession;
        private String sex;
        private String zodiacSign;
        private int maxHealth;
        private int attack;
        private int strength;
        private int intelligence;
        private int agility;

        private Item[] equipment;
        private Item[] spatialBelt;
        private Item[] genetics;
        private Item[] cybernetics;

        // Constructs a character
        public Character(String name, String race, String ethnicity, String religion, String profession,
                String sex, String zodiacSign) {
            this.name = name;
            this.race = race;
            this.ethnicity = ethnicity;
            this.religion = religion;
            this.profession = profession;
            this.sex = sex;
            this.zodiacSign = zodiacSign;
            this.strength = 5;
            this.intelligence = 5;
            this.agility = 5;
            adjustAttributesBasedOnChoices();
            this.maxHealth = calculateMaxHealth();
            this.attack = calculateAttack();
            this.equipment = new Item[11];
            this.spatialBelt = new Item[5];
            this.genetics = new Item[3];
            this.cybernetics = new Item[2];
        }

        public String getName() {
            return name;
        }

        public String getRace() {
            return race;
        }

        public String getEthnicity() {
            return ethnicity;
        }

        public String getReligion() {
            return religion;
        }

        public String getProfession() {
            return profession;
        }

        public String getSex() {
            return sex;
        }

        public String getZodiacSign() {
            return zodiacSign;
        }

        public int getMaxHealth() {
            return maxHealth;
        }

        public int getAttack() {
            return attack;
        }

        public int getStrength() {
            return strength;
        }

        public int getIntelligence() {
            return intelligence;
        }

        public int getAgility() {
            return agility;
        }

        public void setMaxHealth(int maxHealth) {
            this.maxHealth = maxHealth;
        }

        private int calculateMaxHealth() {
            return 100 + strength * 10;
        }

        private int calculateAttack() {
            return intelligence * 5;
        }

        private void adjustAttributesBasedOnChoices() {
            if (religion.equalsIgnoreCase("Polytheism")) {
                strength += 3;
                intelligence += 2;
                agility += 1;
            } else if (religion.equalsIgnoreCase("Monotheism")) {
                strength += 2;
                intelligence += 3;
                agility += 1;
            } else if (religion.equalsIgnoreCase("Agnostic") || religion.equalsIgnoreCase("Atheist")) {
                strength += 2;
                intelligence += 2;
                agility += 2;
            } else {
                strength += 1;
                intelligence += 1;
                agility += 3;
            }

            if (zodiacSign.equalsIgnoreCase("Leo") || zodiacSign.equalsIgnoreCase("Aries")) {
                strength += 1;
            } else if (zodiacSign.equalsIgnoreCase("Virgo") || zodiacSign.equalsIgnoreCase("Taurus")) {
                intelligence += 1;
            } else if (zodiacSign.equalsIgnoreCase("Libra") || zodiacSign.equalsIgnoreCase("Gemini")) {
                agility += 1;
            }

            if (sex.equalsIgnoreCase("Male")) {
                strength += 2;
            } else if (sex.equalsIgnoreCase("Female")) {
                intelligence += 2;
            }
        }
    }

    // Represents a monster in the game.
    enum Monster {
        XENOMORPH("Xenomorph", 100, 100, 2),
        PREDATOR("Predator", 105, 105, 3),
        ELDAR("Eldar", 200, 200, 4),
        DALEK("Dalek", 205, 205, 5);

        private String name;
        private int maxHealth;
        private int currentHealth;
        private int attack;

        Monster(String name, int maxHealth, int currentHealth, int attack) {
            this.name = name;
            this.maxHealth = maxHealth;
            this.currentHealth = currentHealth;
            this.attack = attack;
        }

        public String getName() {
            return name;
        }

        public int getMaxHealth() {
            return maxHealth;
        }

        public int getCurrentHealth() {
            return currentHealth;
        }

        public void setCurrentHealth(int currentHealth) {
            this.currentHealth = currentHealth;
        }

        public int getAttack() {
            return attack;
        }
    }

    public RPGGame() {
        frame = new JFrame("Order of Eternal Darkness");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        createStartScreen();

        frame.pack();
        frame.setVisible(true);
    }

    // Create start screen layout
    private void createStartScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel gameTitleLabel = new JLabel("Order of Eternal Darkness");
        gameTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        gameTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(gameTitleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JButton createCharacterButton = new JButton("Create Character");
        createCharacterButton.setFont(new Font("Arial", Font.PLAIN, 18));
        createCharacterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createCharacter();
            }
        });
        buttonPanel.add(createCharacterButton);

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });
        buttonPanel.add(loadGameButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        buttonPanel.add(exitButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
    }

    // Create character creation layout
    private void createCharacter() {
        String name = JOptionPane.showInputDialog(frame, "Enter your character name:");
        String race = "Human"; // Fixed race to Human

        String[] ethnicityOptions = getEthnicityOptions(race);
        String ethnicity = (String) JOptionPane.showInputDialog(frame, "Select your character ethnicity:",
                "Ethnicity", JOptionPane.PLAIN_MESSAGE, null, ethnicityOptions, ethnicityOptions[0]);

        String religion = (String) JOptionPane.showInputDialog(frame, "Select your character religion:", "Religion",
                JOptionPane.PLAIN_MESSAGE, null, new String[] { "Polytheism", "Monotheism", "Agnostic", "Spiritualism",
                        "Pantheism", "Transcendentalism", "Atheist" },
                "Polytheism");

        String profession = (String) JOptionPane.showInputDialog(frame, "Select your character profession:",
                "Profession", JOptionPane.PLAIN_MESSAGE, null,
                new String[] { "Hunter", "Assassin", "Trader", "Engineer", "Explorer", "Scientist", "Pilot", "Diplomat",
                        "Medic", "Archaeologist", "Hacker", "Translator" },
                "Hunter");

        // Show sex options
        String[] sexOptions = { "Male", "Female" };
        String sex = (String) JOptionPane.showInputDialog(frame, "Select your character sex:", "Sex",
                JOptionPane.PLAIN_MESSAGE, null, sexOptions, sexOptions[0]);

        String zodiacSign = (String) JOptionPane.showInputDialog(frame, "Select your character zodiac sign:",
                "Zodiac Sign", JOptionPane.PLAIN_MESSAGE, null,
                new String[] { "Aries", "Taurus", "Gemini", "Cancer", "Leo",
                        "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces" },
                "Aries");
        String[] itemNames = new String[0];
        selectedCharacter = new Character(name, race, ethnicity, religion, profession, sex, zodiacSign);
        potionCount = 3;
        experience = 0;
        level = 1;
        stellarCoins = 0;
        silverCoins = 0;
        goldCoins = 0;

        homeScreen();
    }

    // Get random item name from the itemNames array
    private String getRandomItemName() {
        int randomIndex = generateRandomNumber(0, itemNames.length - 1);
        return itemNames[randomIndex];
    }

    // Get random rarity: Common, Uncommon, Rare, Epic, Legendary
    private String getRandomRarity() {
        String[] rarities = { "Common", "Uncommon", "Rare", "Epic", "Legendary" };
        int randomIndex = generateRandomNumber(0, rarities.length - 1);
        return rarities[randomIndex];
    }

    // Get slot name based on the index
    private String getSlotName(int index) {
        switch (index) {
            case 0:
                return "Head";
            case 1:
                return "Chest";
            case 2:
                return "Pants";
            case 3:
                return "Neck";
            case 4:
                return "Ring";
            case 5:
                return "Ring";
            case 6:
                return "Bracelet";
            case 7:
                return "Belt";
            case 8:
                return "Boots";
            case 9:
                return "Genetic Enhancement";
            case 10:
                return "Cybernetic Implant";
            case 11:
                return "Cybernetic Implant";
            default:
                return "";
        }
    }

    // Show home screen
    private void homeScreen() {
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(8, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Order of Eternal Darkness");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);

        JButton adventureButton = new JButton("Adventure");
        adventureButton.setFont(new Font("Arial", Font.PLAIN, 18));
        adventureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startBattle();
            }
        });
        panel.add(adventureButton);

        JButton combatButton = new JButton("Combat");
        combatButton.setFont(new Font("Arial", Font.PLAIN, 18));
        combatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startCombat();
            }
        });
        panel.add(combatButton);

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.setFont(new Font("Arial", Font.PLAIN, 18));
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventoryScreen();
            }
        });
        panel.add(inventoryButton);

        JButton shopButton = new JButton("Shop");
        shopButton.setFont(new Font("Arial", Font.PLAIN, 18));
        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openShop();
            }
        });
        panel.add(shopButton);

        JButton craftingButton = new JButton("Crafting");
        craftingButton.setFont(new Font("Arial", Font.PLAIN, 18));
        craftingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCraftingScreen();
            }
        });
        panel.add(craftingButton);

        JButton travelButton = new JButton("Travel");
        travelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        travelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTravelScreen();
            }
        });
        panel.add(travelButton);

        JButton playerStatsButton = new JButton("Player Stats");
        playerStatsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        playerStatsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPlayerStatsScreen();
            }
        });
        panel.add(playerStatsButton);

        JButton virtualWalletButton = new JButton("Virtual Wallet");
        virtualWalletButton.setFont(new Font("Arial", Font.PLAIN, 18));
        virtualWalletButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showVirtualWalletScreen();
            }
        });
        panel.add(virtualWalletButton);

        JButton saveGameButton = new JButton("Save Game");
        saveGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
        saveGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });
        panel.add(saveGameButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

    }

    private void createCombatScreen(JLabel playerHealthLabel, JLabel monsterHealthLabel) {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new BorderLayout());

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(2, 1));

        JLabel playerNameLabel = new JLabel("Player: " + selectedCharacter.getName());
        playerPanel.add(playerNameLabel);

        playerPanel.add(playerHealthLabel);

        panel.add(playerPanel, BorderLayout.WEST);

        JPanel monsterPanel = new JPanel();
        monsterPanel.setLayout(new GridLayout(2, 1));

        JLabel monsterNameLabel = new JLabel("Monster: " + currentMonster.getName());
        monsterPanel.add(monsterNameLabel);

        monsterPanel.add(monsterHealthLabel);

        panel.add(monsterPanel, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performPlayerAttack();
                updateHealthLabels(playerHealthLabel, monsterHealthLabel);
                if (currentMonster.getMaxHealth() > 0) {
                    performMonsterAttack();
                    updateHealthLabels(playerHealthLabel, monsterHealthLabel);
                }
                checkCombatResult(playerHealthLabel, monsterHealthLabel);
            }
        });
        buttonPanel.add(attackButton);

        JButton potionButton = new JButton("Use Potion");
        potionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (potionCount > 0) {
                    int healthRestored = selectedCharacter.getMaxHealth() / 2;
                    selectedCharacter.maxHealth += healthRestored;
                    if (selectedCharacter.getMaxHealth() > selectedCharacter.calculateMaxHealth()) {
                        selectedCharacter.maxHealth = selectedCharacter.calculateMaxHealth();
                    }
                    potionCount--;
                    updateHealthLabels(playerHealthLabel, monsterHealthLabel);
                } else {
                    JOptionPane.showMessageDialog(frame, "You don't have any potions!");
                }
            }
        });
        buttonPanel.add(potionButton);

        JButton fleeButton = new JButton("Flee");
        fleeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fleeCombat();
            }
        });
        buttonPanel.add(fleeButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    // Show inventory screen
    private void showInventoryScreen() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));

        JLabel titleLabel = new JLabel("Inventory");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);
    }

    // Create inventory screen layout
    private void inventoryScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 1));

        JLabel inventoryTitleLabel = new JLabel("Inventory");
        inventoryTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(inventoryTitleLabel);

        JButton[] equipmentButtons = new JButton[11];
        for (int i = 0; i < equipmentButtons.length; i++) {
            String buttonText;
            if (selectedCharacter.equipment[i] != null) {
                buttonText = selectedCharacter.equipment[i].getName();
            } else {
                buttonText = "Empty";
            }
            equipmentButtons[i] = new JButton(
                    selectedCharacter.equipment[i] != null ? selectedCharacter.equipment[i].getName() : "Empty");
            equipmentButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, buttonText);
                }
            });
            panel.add(equipmentButtons[i]);
        }

        JButton spatialBeltButton = new JButton("Spatial Belt");
        spatialBeltButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spatialBeltScreen();
            }
        });
        panel.add(spatialBeltButton);

        JButton geneticsButton = new JButton("Genetics");
        geneticsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                geneticsScreen();
            }
        });
        panel.add(geneticsButton);

        JButton cyberneticsButton = new JButton("Cybernetics");
        cyberneticsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cyberneticsScreen();
            }
        });

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                homeScreen();
            }
        });
        panel.add(returnButton);

        panel.add(cyberneticsButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    // Create spatial belt screen layout
    private void spatialBeltScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel spatialBeltTitleLabel = new JLabel("Spatial Belt");
        spatialBeltTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(spatialBeltTitleLabel);

        JButton[] spatialBeltButtons = new JButton[5];
        for (int i = 0; i < spatialBeltButtons.length; i++) {
            String buttonText;
            if (selectedCharacter.spatialBelt[i] != null) {
                buttonText = selectedCharacter.spatialBelt[i].getName();
            } else {
                buttonText = "Empty";
            }
            spatialBeltButtons[i] = new JButton(buttonText);
            spatialBeltButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, buttonText);
                }
            });
            panel.add(spatialBeltButtons[i]);
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventoryScreen();
            }
        });
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    // Create genetics screen layout
    private void geneticsScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel geneticsTitleLabel = new JLabel("Genetics");
        geneticsTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(geneticsTitleLabel);

        JButton[] geneticsButtons = new JButton[3];
        for (int i = 0; i < geneticsButtons.length; i++) {
            String buttonText;
            if (selectedCharacter.genetics[i] != null) {
                buttonText = selectedCharacter.genetics[i].getName();
            } else {
                buttonText = "Empty";
            }
            geneticsButtons[i] = new JButton(buttonText);
            geneticsButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, buttonText);
                }
            });
            panel.add(geneticsButtons[i]);
        }

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventoryScreen();
            }
        });
        panel.add(returnButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventoryScreen();
            }
        });
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    // Create cybernetics screen layout
    private void cyberneticsScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel cyberneticsTitleLabel = new JLabel("Cybernetics");
        cyberneticsTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(cyberneticsTitleLabel);

        JButton[] cyberneticsButtons = new JButton[2];
        for (int i = 0; i < cyberneticsButtons.length; i++) {
            String buttonText;
            if (selectedCharacter.cybernetics[i] != null) {
                buttonText = selectedCharacter.cybernetics[i].getName();
            } else {
                buttonText = "Empty";
            }
            cyberneticsButtons[i] = new JButton(buttonText);
            cyberneticsButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, buttonText);
                }
            });
            panel.add(cyberneticsButtons[i]);
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventoryScreen();
            }
        });
        panel.add(backButton);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    // Show the crafting screen
    private void showCraftingScreen() {
        // TODO: Implement crafting screen
    }

    // Show the travel screen
    private void showTravelScreen() {
        // TODO: Implement travel screen
    }

    // Show the player stats screen
    private void showPlayerStatsScreen() {
        StringBuilder statsBuilder = new StringBuilder();
        statsBuilder.append("Player Stats\n\n");
        statsBuilder.append("Name: ").append(selectedCharacter.getName()).append("\n");
        statsBuilder.append("Race: ").append(selectedCharacter.getRace()).append("\n");
        statsBuilder.append("Ethnicity: ").append(selectedCharacter.getEthnicity()).append("\n");
        statsBuilder.append("Religion: ").append(selectedCharacter.getReligion()).append("\n");
        statsBuilder.append("Profession: ").append(selectedCharacter.getProfession()).append("\n");
        statsBuilder.append("Sex: ").append(selectedCharacter.getSex()).append("\n");
        statsBuilder.append("Zodiac Sign: ").append(selectedCharacter.getZodiacSign()).append("\n");
        statsBuilder.append("Max Health: ").append(selectedCharacter.getMaxHealth()).append("\n");
        statsBuilder.append("Attack: ").append(selectedCharacter.getAttack()).append("\n");
        statsBuilder.append("Strength: ").append(selectedCharacter.getStrength()).append("\n");
        statsBuilder.append("Intelligence: ").append(selectedCharacter.getIntelligence()).append("\n");
        statsBuilder.append("Agility: ").append(selectedCharacter.getAgility()).append("\n");

        JOptionPane.showMessageDialog(frame, statsBuilder.toString(), "Player Stats", JOptionPane.INFORMATION_MESSAGE);
    }

    // Show virtual wallet screen
    private void showVirtualWalletScreen() {
        StringBuilder currencyBuilder = new StringBuilder();
        currencyBuilder.append("Virtual Wallet\n\n");
        currencyBuilder.append("Gold Coins: ").append(goldCoins).append("\n");
        currencyBuilder.append("Silver Coins: ").append(silverCoins).append("\n");
        currencyBuilder.append("Stellar Coins: ").append(stellarCoins).append("\n");

        JOptionPane.showMessageDialog(frame, currencyBuilder.toString(), "Virtual Wallet",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Start battle
    private void startBattle() {
        currentMonster = getRandomMonster();
        currentMonster.setCurrentHealth(currentMonster.getMaxHealth());
        JOptionPane.showMessageDialog(frame,
                "Monster: " + currentMonster.getName() + " (" + currentMonster.getMaxHealth() + " HP)");

        while (currentMonster.getMaxHealth() > 0 && selectedCharacter.getMaxHealth() > 0) {
            performAttack();

            if (currentMonster.getMaxHealth() > 0) {
                monsterAttack();
            }
        }

        if (selectedCharacter.getMaxHealth() <= 0) {
            JOptionPane.showMessageDialog(frame, "You were defeated by the " + currentMonster.getName() + "!");
            selectedCharacter.maxHealth = selectedCharacter.calculateMaxHealth(); // Reset health to base stats
        } else {
            JOptionPane.showMessageDialog(frame, "You defeated the " + currentMonster.getName() + "!");
            int coinsEarned = generateRandomNumber(1, 15); // Generate coins earned
            int coinsType = generateRandomNumber(1, 3); // Generate coins type (1 = silver, 2 = gold, 3 = mix)
            if (coinsType == 1) {
                silverCoins += coinsEarned;
            } else if (coinsType == 2) {
                goldCoins += coinsEarned;
            } else {
                silverCoins += coinsEarned / 2;
                goldCoins += coinsEarned / 2;
            }
            JOptionPane.showMessageDialog(frame, "You earned " + coinsEarned + " coins!");
            gainExperience(10); // Increase experience by 10 for defeating a monster
            checkLevelUp(); // Check if player leveled up
        }

        homeScreen();
    }

    // Perform player's attack
    private void performAttack() {
        int playerAttack = selectedCharacter.getAttack();
        int monsterDefense = generateRandomNumber(1, 2);

        int damageDealt = playerAttack - monsterDefense;
        if (damageDealt > 0) {
            int monsterHealth = currentMonster.getMaxHealth() - damageDealt;
            currentMonster = currentMonster.withMaxHealth(monsterHealth);
        }

        if (currentMonster.getMaxHealth() <= 0) {
            JOptionPane.showMessageDialog(frame, "You dealt the final blow to the " + currentMonster.getName() + "!");
        } else {
            JOptionPane.showMessageDialog(frame, "You attacked the " + currentMonster.getName() + "!");
        }
    }

    // Perform monster's attack
    private void monsterAttack() {
        int monsterAttack = currentMonster.getAttack();
        int playerDefense = generateRandomNumber(1, 2);

        int damageReceived = monsterAttack - playerDefense;
        if (damageReceived > 0) {
            selectedCharacter.maxHealth -= damageReceived;
            JOptionPane.showMessageDialog(frame, "The " + currentMonster.getName() + " attacked you!\nYou received "
                    + damageReceived + " damage!\nYour health: " + selectedCharacter.getMaxHealth());
        }
    }

    // Gain experience points
    private void gainExperience(int points) {
        experience += points;
        JOptionPane.showMessageDialog(frame, "You gained " + points + " experience!\nTotal experience: " + experience);
    }

    // Check if player leveled up
    private void checkLevelUp() {
        int levelThreshold = 50; // Experience threshold for leveling up
        if (experience >= levelThreshold) {
            level++;
            JOptionPane.showMessageDialog(frame, "Congratulations! You leveled up to Level " + level + "!");
            experience -= levelThreshold;
            JOptionPane.showMessageDialog(frame, "Experience: " + experience);
            selectedCharacter.maxHealth = selectedCharacter.calculateMaxHealth(); // Fully restore health on level up
            JOptionPane.showMessageDialog(frame, "Your health has been fully restored!");
            potionCount++; // Increase the number of available potions on level up
            JOptionPane.showMessageDialog(frame, "You gained a potion! Total potions: " + potionCount);
        }
    }

    private JLabel playerHealthLabel;
    private JLabel monsterHealthLabel;
    private boolean isPlayerTurn;

    private void startCombat() {
        currentMonster = getRandomMonster();
        isPlayerTurn = true;

        // Create the player and monster health labels
        playerHealthLabel = new JLabel("Health: " + selectedCharacter.getMaxHealth());
        monsterHealthLabel = new JLabel("Health: " + currentMonster.getMaxHealth());

        // Call createCombatScreen() with the health labels
        createCombatScreen(playerHealthLabel, monsterHealthLabel);

        while (currentMonster.getCurrentHealth() > 0 && selectedCharacter.getMaxHealth() > 0) {
            if (isPlayerTurn) {
                performPlayerAttack();
            } else {
                performMonsterAttack();
            }

            // Delay between attacks for better visibility
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (selectedCharacter.getMaxHealth() <= 0) {
            JOptionPane.showMessageDialog(frame, "You were defeated by the " + currentMonster.getName() + "!");
            selectedCharacter.maxHealth = selectedCharacter.calculateMaxHealth();
        } else {
            JOptionPane.showMessageDialog(frame, "You defeated the " + currentMonster.getName() + "!");
            int coinsEarned = generateRandomNumber(1, 15);
            int coinsType = generateRandomNumber(1, 3);
            if (coinsType == 1) {
                silverCoins += coinsEarned;
            } else if (coinsType == 2) {
                goldCoins += coinsEarned;
            } else {
                silverCoins += coinsEarned / 2;
                goldCoins += coinsEarned / 2;
            }
            JOptionPane.showMessageDialog(frame, "You earned " + coinsEarned + " coins!");
            gainExperience(10);
            checkLevelUp();
        }

        homeScreen();
    }

    // player attack
    private void performPlayerAttack() {
        int playerAttack = selectedCharacter.getAttack();
        int monsterDefense = generateRandomNumber(1, 2);

        int damageDealt = playerAttack - monsterDefense;
        if (damageDealt > 0) {
            int monsterHealth = currentMonster.getCurrentHealth() - damageDealt;
            currentMonster.setCurrentHealth(Math.max(monsterHealth, 0)); // Ensure current health doesn't go below 0
        }

        updateHealthLabels();
        isPlayerTurn = false;
    }

    private void performMonsterAttack() {
        int monsterAttack = currentMonster.getAttack();
        int playerDefense = generateRandomNumber(1, 2);

        int damageReceived = monsterAttack - playerDefense;
        if (damageReceived > 0) {
            int playerHealth = selectedCharacter.getMaxHealth() - damageReceived;
            selectedCharacter.setMaxHealth(Math.max(playerHealth, 0)); // Ensure player health doesn't go below 0
            JOptionPane.showMessageDialog(frame, "The " + currentMonster.getName() + " attacked you!\nYou received "
                    + damageReceived + " damage!\nYour health: " + selectedCharacter.getMaxHealth());
        }

        updateHealthLabels();
        isPlayerTurn = true;
    }

    private void updateHealthLabels() {
        playerHealthLabel.setText("Health: " + selectedCharacter.getMaxHealth());
        monsterHealthLabel.setText("Health: " + currentMonster.getCurrentHealth());
    }

    // flee method
    private void fleeCombat() {
        JOptionPane.showMessageDialog(frame, "You fled from the " + currentMonster.getName() + "!");
        selectedCharacter.maxHealth = selectedCharacter.calculateMaxHealth();
        homeScreen();
    }

    // Generate a random number between min and max (inclusive)
    private int generateRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    // Get a random monster for battle
    private Monster getRandomMonster() {
        Monster[] monsters = Monster.values();
        int randomIndex = generateRandomNumber(0, monsters.length - 1);
        return monsters[randomIndex];
    }

    // Get ethnicity options based on selected race
    private String[] getEthnicityOptions(String race) {
        if (race.equalsIgnoreCase("Human")) {
            return new String[] { "Caucasian", "African American", "Asian", "Hispanic", "Native American",
                    "Middle Eastern",
                    "Pacific Islander", "South Asian", "Mixed Race", "Other" };
        }
        // Add conditions for other races here
        return new String[0];
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RPGGame();
            }
        });
    }
}