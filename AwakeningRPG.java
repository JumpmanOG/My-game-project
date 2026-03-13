import java.util.Random;
import java.util.Scanner;

// =========================
// RELIC CLASS
// =========================
class Relic {
    String name;
    String description;
    int strengthBonus;
    int agilityBonus;
    int luckBonus;

    public Relic(String name, String description, int strengthBonus, int agilityBonus, int luckBonus) {
        this.name = name;
        this.description = description;
        this.strengthBonus = strengthBonus;
        this.agilityBonus = agilityBonus;
        this.luckBonus = luckBonus;
    }
}

// =========================
// ENEMY CLASS
// =========================
class Enemy {
    String name;
    int hp;
    int strength;

    public Enemy(String name, int hp, int strength) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
    }
}

public class AwakeningRPG {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void typewriter(String text, int delayMs) throws InterruptedException {
        int lineLength = 0;
        int maxWidth = 80;
        for (char c : text.toCharArray()) {
            System.out.print(c);
            Thread.sleep(delayMs);
            lineLength++;
            if (lineLength >= maxWidth && c == ' ') {
                System.out.println();
                lineLength = 0;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        runIntro();

        String mercenaryName = "Troy the Tenacious";

        // --- CHARACTER STATS ---
        int hp = 30 + random.nextInt(20);
        int strength = 5 + random.nextInt(5);
        int agility = 5 + random.nextInt(5);
        int luck = 1 + random.nextInt(5);

        System.out.println("=== CHARACTER SHEET ===");
        System.out.println("Name: " + mercenaryName);
        System.out.println("HP: " + hp);
        System.out.println("Strength: " + strength);
        System.out.println("Agility: " + agility);
        System.out.println("Luck: " + luck);
        System.out.println("========================\n");

        Thread.sleep(1000);

        // --- STORY CHOICE ---
        System.out.println("Ahead, a faint harmonic resonance vibrates through the dunes...");
        System.out.println("How do you approach the anomaly?");
        System.out.println("1) Charge forward\n2) Sneak closer\n3) Call out to the presence\n4) Observe quietly");
        int approach = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                approach = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input! Please enter a number, Starseed.");
                scanner.next();
            }
        }
        if (approach == 1) {
            System.out.println("\nYou charge boldly. The resonance flares!");
            strength += 1;
        } else if (approach == 2) {
            System.out.println("\nYou move silently...");
            if (random.nextInt(10) < agility + luck) {
                System.out.println("You gain the advantage!");
                agility += 1;
            }
        } else if (approach == 3) {
            System.out.println("\nYou call out. The resonance shifts...");
            luck += 1;
        } else if (approach == 4) {
            System.out.println("\nYou study the anomaly. Knowledge flows into you.");
            agility += 1; luck += 1;
        }

        Thread.sleep(1000);

        // --- RARE RELIC DISCOVERY ---
        Relic regaliaOfSirius = new Relic("Regalia of Sirius", "A star-forged relic of the Celestial Order.", 3, 2, 4);
        if (random.nextInt(100) < 20) {
            System.out.println("\nThe sand parts, revealing a glowing relic: " + regaliaOfSirius.name);
            strength += regaliaOfSirius.strengthBonus;
            agility += regaliaOfSirius.agilityBonus;
            luck += regaliaOfSirius.luckBonus;
            System.out.println("The Regalia empowers you! New Strength: " + strength + " | Agility: " + agility + " | Luck: " + luck + "\n");
        } else {
            System.out.println("\nYou search the sands, but find only dust. The battle awaits.");
        }

        // --- COMBAT ---
        Enemy currentEnemy = new Enemy("Lumen Warden", 25 + random.nextInt(12), 5 + random.nextInt(4));
        System.out.println("A " + currentEnemy.name + " emerges from the sand!\n");

        while (hp > 0 && currentEnemy.hp > 0) {
            System.out.println("1) Strike | 2) Dodge | 3) Intimidate");
            int choice = 0;
            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a number, Starseed.");
                    scanner.next();
                }
            }
            boolean enemyCanAttack = true;

            if (choice == 1) {
                int damage = strength + random.nextInt(4);
                currentEnemy.hp -= damage;
                System.out.println("You strike for " + damage + " damage!");
            } else if (choice == 2) {
                if (random.nextInt(20) + (agility / 2) > 12) {
                    System.out.println("Perfect evasion! The enemy misses.");
                    enemyCanAttack = false;
                }
            } else if (choice == 3) {
                if (random.nextInt(20) + luck > 18) {
                    System.out.println("The " + currentEnemy.name + " self-destructs in terror!");
                    currentEnemy.hp = 0; enemyCanAttack = false;
                }
            }

            if (currentEnemy.hp > 0 && enemyCanAttack) {
                int eDamage = currentEnemy.strength + random.nextInt(3);
                hp -= eDamage;
                System.out.println("The " + currentEnemy.name + " hits you for " + eDamage + " damage!");
            }
            System.out.println("STATUS -> HP: " + hp + " | Enemy HP: " + currentEnemy.hp + "\n");
        }

        if (hp <= 0) {
            System.out.println("[SYSTEM CRITICAL] Your signal fades into the dunes...");
            return;
        }

        // --- TRANSITION TO SANCTUM ---
        typewriter("\nAs the enemy collapses, the heavy stone doors behind it begin to grind open...", 40);
        System.out.println("You step into the dark. Glowing blue runes ignite along the walls.");
        Thread.sleep(1000);

        // --- THE PRISM OF SIRIUS PUZZLE ---
        solvePrismPuzzle();

        // Enter the Inner Sanctum after solving the puzzle
        enterInnerSanctum(hp, strength, agility, luck);

        System.out.println("\nCongratulations, Starseed. You have unlocked the deeper secrets of the Order.");
        scanner.close();
    }

    // --- INNER SANCTUM SEQUENCE ---
    public static void enterInnerSanctum(int hp, int strength, int agility, int luck) throws InterruptedException {
        typewriter("\nThe stone doors slide open, revealing a vast chamber bathed in pale blue light.", 35);
        typewriter("Ancient murals spiral across the walls—stars, worlds, and figures with elongated forms.", 35);
        Thread.sleep(800);

        typewriter("\nAt the center stands a towering construct, motionless but humming faintly.", 35);
        typewriter("Its eyes flicker to life as you approach.", 35);

        System.out.println("\n\"Starseed... bearer of the Regalia... you have returned.\"");

        System.out.println("\nHow do you respond?");
        System.out.println("1) \"What are you?\"\n2) \"Why did the Order fall?\"\n3) Remain silent.");

        int choice = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input! Please enter a number, Starseed.");
                scanner.next();
            }
        }
        if (choice == 1) {
            typewriter("\"I am the Archivist. Last sentinel of the Celestial Order.\"", 40);
        } else if (choice == 2) {
            typewriter("\"The Order reached too far into the cosmic veil. Some truths were not meant to be held.\"", 40);
        } else {
            typewriter("The construct studies your silence. \"Wisdom in restraint. The stars favor you.\"", 40);
            luck += 1;
        }

        Thread.sleep(800);
        typewriter("\nThe Archivist raises an arm. A holographic star map ignites above you.", 35);
        typewriter("Constellations shift, aligning with symbols carved into the chamber floor.", 35);

        System.out.println("\nA new path opens deeper into the sanctum...");
    }

    public static void solvePrismPuzzle() throws InterruptedException {
        String target = "01010011"; // ASCII for 'S'
        int[] crystals = {0, 0, 0, 0, 0, 0, 0, 0};
        String[] colors = {"DIM", "RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "INDIGO", "VIOLET"};

        System.out.println("\n--- LOCATION: THE HALL OF ETERNITY ---");
        typewriter("Eight crystals stand before a mural of the 'S' constellation.", 30);
        System.out.println("Clue: 'Only the frequencies of the second, fourth, seventh, and eighth suns shall open the way.'");

        while (true) {
            System.out.print("\nCurrent Alignment: ");
            for (int i = 0; i < 8; i++) {
                System.out.print("[" + (crystals[i] == 0 ? "DIM" : colors[i]) + "] ");
            }
            
            System.out.println("\nToggle crystal (1-8) or 0 to finalize:");
            int choice = 0;
            while (true) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a number, Starseed.");
                    scanner.next();
                }
            }
            
            if (choice == 0) {
                StringBuilder attempt = new StringBuilder();
                for (int val : crystals) attempt.append(val);
                
                if (attempt.toString().equals(target)) {
                    typewriter("\nThe crystals harmonize! The path to the Inner Sanctum is revealed.", 30);
                    break;
                } else {
                    System.out.println("\nThe resonance is discordant. The door remains shut.");
                }
            } else if (choice >= 1 && choice <= 8) {
                crystals[choice - 1] = (crystals[choice - 1] == 0) ? 1 : 0;
                System.out.println("Crystal " + choice + " is now " + (crystals[choice - 1] == 1 ? colors[choice - 1] : "DIM") + ".");
            }
        }
    }

    public static void runIntro() throws InterruptedException {
        System.out.println();
        typewriter("Long ago, the Celestial Order ruled the shifting sands with wisdom and power. Destiny calls, and your legend is about to unfold...", 33);
        Thread.sleep(800);
        System.out.println(">> INITIALIZING ANCIENT RUNES...");
        Thread.sleep(800);
        System.out.print(">> VERIFYING ARCANE SIGILS...");
        Thread.sleep(1000);
        System.out.println(" [OK]");
        System.out.print(">> LOADING CELESTIAL GUILD DATABASE...");
        for (int i = 0; i < 3; i++) { Thread.sleep(500); System.out.print("."); }
        System.out.println(" [SUCCESS]");
        Thread.sleep(500);
        System.out.println("\n=========================================");
        System.out.println("Awakening: The Age of The Celestial Order");
        System.out.println("=========================================");
        System.out.println("Welcome back, Starseed. System link established with 'Troy the Tenacious'.\n");
        Thread.sleep(1000);
    }
}