package com.homedepot.om.student;

import com.google.gson.Gson;
import java.io.*;
import java.util.*;

public class Main {

    private static int kills = 0;
    private static String strInput;
    private static Scanner sc = new Scanner(System.in);
    private static Player player;
    private static Enemy enemy;
    private static Gson gson = new Gson();
    private static boolean gameActive;
    private static boolean isPrompt = false;


    public static void main(String[] args) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n                     This game is best ran in a terminal with window size of: 105x26\n\n  Terminal should be displayed with a BLACK background. Some GUI elements may not look correct if not. \n\n                              Please resize and press any key to continue\n\n\n\n\n\n\n\n\n\n");
        sc.nextLine();
        gameActive = true;
        while(gameActive) {
            prompt();
            gameLoop();
        }

    }

    private static void logCheck() {
        System.out.println("Enemy:  " + " CurrentHP:" + enemy.getCurrentHealth() + "  MaxHP:" + enemy.getMaxHealth() + "  CurrentMana:" + enemy.getCurrentMana() + "  MaxMana:" + enemy.getMaxMana());
        System.out.println("Player:  " + " CurrentHP:" + player.getCurrentHealth() + "  MaxHP:" + player.getMaxHealth() + "  CurrentMana:" + player.getCurrentMana() + "  MaxMana:" + player.getMaxMana());
    }

    //prompt method will run immediately once opening program
    //method will prompt user to either begin game by selecting one of the classes, or
    //option to load a save state via JSON, which will feed any necessary data into init
    private static void prompt() {
        isPrompt = true;
        String classInput;
        System.out.println("\n"+ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + "                                                                                                         " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + "  " + ConsoleColors.BLACK_BACKGROUND + ConsoleColors.RED_BOLD + "                                           AZEROTH'S FURY                                            "+ ConsoleColors.WHITE_BACKGROUND_BRIGHT +"  " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + "  " + ConsoleColors.BLACK_BACKGROUND + ConsoleColors.WHITEBRIGHTBOLD + "                                                                                                     "+ ConsoleColors.WHITE_BACKGROUND_BRIGHT +"  " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + "  " + ConsoleColors.BLACK_BACKGROUND + ConsoleColors.WHITEBRIGHTBOLD + "                                                                                                     "+ ConsoleColors.WHITE_BACKGROUND_BRIGHT +"  " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + "  " + ConsoleColors.BLACK_BACKGROUND + ConsoleColors.WHITEBRIGHTBOLD + "                              Command Line Turn Based Combat Simulator                               "+ ConsoleColors.WHITE_BACKGROUND_BRIGHT +"  " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.BLACK_BOLD + "                                                                                                         " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.WHITE_BOLD + "                                (enter 'exit' at anytime to quit program)" + ConsoleColors.RESET);

        System.out.println("\u001b[1m\u001b[38;5;196m                                          ___====-_  _-====___ ");
        System.out.println("                                    _--^^^     //      \\\\     ^^^--_");
        System.out.println("                                  _-^         // (    ) \\\\         ^-_");
        System.out.println("                                 -           //  |\\^^/|  \\\\           -");
        System.out.println("                               _/           //   (0::0)   \\\\            \\_");
        System.out.println("                              /            ((     \\\\//     ))             \\ ");
        System.out.println("                            -               \\\\    (oo)    //               -");
        System.out.println("                           -                 \\\\  / \\/ \\  //                 -");
        System.out.println("                          -                   \\\\/      \\//                   -");
        System.out.println("                        / /|           /\\      (        )      /\\           |\\ \\");
        System.out.println("                        |/ | /\\_/\\_/\\_/  \\_/\\  (   /\\   )  /\\_/  \\_/\\_/\\_/\\ | \\|");
        System.out.println("                        `  |/  V  V  `    V  \\_(| |  | |)_/  V    '  V  V  \\|  '");
        System.out.println("                           `   `  `       `   /_|^|  |^|_\\   '       '  '   '");

        System.out.println("Please Select Option (by number only)\n" + ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + "0." + ConsoleColors.RESET +
                " LOAD PREVIOUS GAME   (If you start new game, save is overwritten)\n" +
                "-------------------------------- OR --------------------------------\n"  +
                ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + "1." + ConsoleColors.CYAN_BOLD +
                " Mage  " + ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + "2." + ConsoleColors.PURPLE +
                " Warlock  " + ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + "3." + "\u001B[38;5;35m" +
                " Hunter  " + ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + "4." + ConsoleColors.YELLOW +
                " Rogue  " + ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + "5." + ConsoleColors.BROWN +
                " Warrior  " + ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + "6." + ConsoleColors.MAGENTA_BRIGHT + " Paladin" + ConsoleColors.RESET);
        strInput = getInput(sc);
        //todo (use heroMap hashmap data?)
        switch(strInput){
            case "0":
                classInput = null;
                isPrompt = false;
                break;
            case "1":
                classInput = "mage";
                isPrompt = false;
                break;
            case "2":
                classInput = "warlock";
                isPrompt = false;
                break;
            case "3":
                classInput = "hunter";
                isPrompt = false;
                break;
            case "4":
                classInput = "rogue";
                isPrompt = false;
                break;
            case "5":
                classInput = "warrior";
                isPrompt = false;
                break;
            case "6":
                classInput = "paladin";
                isPrompt = false;
                break;
            default:
                classInput = "mage";
                isPrompt = false;
                break;
        }
        if(strInput.equals("0")){
            HashMap<String, Integer> mapLoad = gameLoad();
            init(mapLoad);
        } else {
            init(classInput); //new game with selected class
        }

    }

    //default init method will initialize player and enemy instances, used if new game, no selection
    //will randomize the character objects classes
    private static void init() {
        player = new Player(); //default mage
        enemy = new Enemy();
        kills = 0;
        player.setKills(kills);
        System.out.println("\n\n\n\n\n\n" + player.getDescription() + " chosen. Enjoy!");
        updateDisplay();
    }
    //overloaded init method that takes in manual class entry
    private static void init(String heroClass) {
        player = new Player(heroClass); //pass through class choice
        enemy = new Enemy();
        kills = 0;
        player.setKills(kills);
        System.out.println("\n\n\n\n\n\n" + player.getDescription() + " chosen. Enjoy!");
        updateDisplay();
    }

    //overloaded init method that takes in saveState information
    public static void init(HashMap<String,Integer> mapLoad) {
        player = new Player();
        player.setStats(mapLoad);
        player.loadPotsMap(mapLoad);
        enemy = new Enemy();
        kills = player.getKills();
        player.setDescription();
        updateDisplay();
    }

    //will be used to update gui, includes player/enemy health. player turn. ansi/ascii art. prompt.
    //todo throw exception if objects don't exist...?
    private static void updateDisplay() {
        System.out.println(ConsoleColors.WHITE_BACKGROUND + ConsoleColors.BLACK_BOLD + " YOU                                                                                              ENEMY " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + player.getCurrentHealth() + " of " + player.getMaxHealth()  + "hp  " + ConsoleColors.BLUE + player.getCurrentMana() + " of " + player.getMaxMana() + "mp                                                    " + ConsoleColors.RED + enemy.getCurrentHealth() + " of " + enemy.getMaxHealth() +  "hp  " + ConsoleColors.BLUE + enemy.getCurrentMana() + " of " + enemy.getMaxMana() + "mp");
        System.out.println(player.getDescription() + "                                                                      " + enemy.getDescription());
        ASCIIArt.displayArt(enemy.getHeroClass());
        System.out.println("                                                                                                        ");
        System.out.println(ConsoleColors.WHITE_BACKGROUND + "                                                                                                        " + ConsoleColors.RESET);
        System.out.println("Health Potions: " + ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + player.getHealthPots() + ConsoleColors.RESET + "  Mana Potions: " +  ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + player.getManaPots() + ConsoleColors.RESET);
        System.out.println("What do you do next?   " + "1. Attack " + " 2. Defend " + " 3. Special Move  4. Health Potion  5. Mana Potion \n");
    }

    //runs the game loop, for each turn, and checks player health to determine if game ends
    private static void gameLoop() {
        while(player.isAlive()){
            gameSave();
            playerCombat();
            if (enemy.isAlive()) {
                enemyCombat();
            } else {
                System.out.println(enemy.getDescription() + " has DIED!!");
                player.receivePots(enemy.drop());
                kills++;
                player.setKills(kills);
                enemy.setClass();
                System.out.println("A wild " + enemy.getDescription() + " appears!!!");
            }
            updateDisplay();
        }
        if (!player.isAlive()) {
            gameEnd();
        }
    }

    //get prompt input from player and processes combat
    private static void playerCombat(){
        String input = getInput(sc);
        int dmg, finaldmg;
        int scInt = Integer.parseInt(input);
        player.setDefending(false);
        switch(scInt){
            case 1:
                dmg = player.attack();
                if(dmg==0){
                    System.out.println("\n\n\n\nYou MISSED!!");
                    break;
                }
                finaldmg = enemy.receiveDamage(dmg);
                System.out.println("\n\n\n\nYou attack the " + enemy.getDescription() + " for " + finaldmg + " damage!");
                break;
            case 2:
                player.defend();
                System.out.println("\n\n\n\nYou take a defensive stance...");
                break;
            case 3:
                dmg = player.special(50);
                //checks if player is out of mana AND did no damage to determine they attacked instead
                //if just 0 damage, then their spell failed
                if(dmg==0 && player.getCurrentMana()==0){
                    System.out.println("\n\n\n\nYou tried to attack instead, but missed.");
                    break;
                } else if (dmg==0) {
                    System.out.println("\n\n\n\nYour spell fizzled out!!");
                    break;
                }
                finaldmg = enemy.receiveDamage(dmg);
                //checks if player is out of mana to determine message thrown
                if(player.isOOM()){
                    System.out.println("\n\n\n\nYou attack the " + enemy.getDescription() + " for " + finaldmg + " damage!");
                } else {
                    System.out.println("\n\n\n\nYou use " + player.getSpecial() + " on " + enemy.getDescription() + " for " + finaldmg + " damage!");
                }
                break;
            case 4:
                //TODO run in function in player? useHealthPotion
                if (player.getHealthPots() > 0){
                    player.removeHealthPots(1);
                    player.setCurrentHealth(player.getMaxHealth());
                    System.out.println("\n\n\n\n\nYou heal back to full health");
                } else {
                    System.out.println("\n\n\n\nYou searched and searched, but found nothing! Defended instead!");
                    player.defend();
                }
                break;
            case 5:
                if (player.getManaPots() > 0){
                    player.removeManaPots(1);
                    player.setCurrentMana(player.getMaxMana());
                    player.setOOM(false);
                    System.out.println("\n\n\n\n\nYou restore your mana back to full");
                } else {
                    System.out.println("\n\n\n\nYou searched and searched, but found nothing! Defended instead!");
                    player.defend();
                }
                break;
            default:
                System.out.println("Invalid selection, attacking...");
                dmg = player.attack();
                if(dmg==0){
                    System.out.println("\n\n\n\nInvalid selection, attacking... You MISSED!!");
                    break;
                }
                finaldmg = enemy.receiveDamage(dmg);
                System.out.println("\n\n\n\nInvalid selection... instead, you attack the " + enemy.getDescription() + " for " + finaldmg + " damage!");
                break;
        }
    }
    private static void enemyCombat(){
        int dmg, finaldmg;
        Random rnd = new Random();
//        System.out.println("enemy hp now: " + enemy.getCurrentHealth());
//        System.out.println("enemy hp max: " + enemy.getMaxHealth());
        double hpEnemy = (enemy.getCurrentHealth()/enemy.getMaxHealth());
        double hpPlayer = (player.getCurrentHealth()/player.getMaxHealth());
        enemy.setDefending(false);
        //Check to see if enemy has enough to use a special move, which costs 50mana at the moment
        if (enemy.getCurrentMana() >= 50) {
            //If enemy has enough mana, check if they could die soon
            if (hpEnemy <= hpPlayer) {
                //60% chance to use special over attack
                int i = rnd.nextInt(9);
                if(i<6) {
                    //If they could die soon use the special move
                    dmg = enemy.special(50);
                    if(dmg==0){
                        System.out.println(enemy.getDescription() + " missed!");
                    } else {
                        finaldmg = player.receiveDamage(dmg);
                        System.out.println(enemy.getDescription() + " used " + enemy.getSpecial() + " on you for " + finaldmg + " damage!");
                    }
                } else {
                    dmg = enemy.attack();
                    if(dmg==0){
                        System.out.println(enemy.getDescription() + " missed!");
                    } else {
                        finaldmg = player.receiveDamage(dmg);
                        System.out.println(enemy.getDescription() + " attacks you for " + finaldmg + " damage!");
                    }
                }
                //If they wont die soon
            } else {
                //Check to see if players health is lower than the enemys
                if (hpPlayer < hpEnemy) {
                    //If players health is lower, attack
                    dmg = enemy.attack();
                    if(dmg==0){
                        System.out.println(enemy.getDescription() + " missed!");
                    } else {
                        finaldmg = player.receiveDamage(dmg);
                        System.out.println(enemy.getDescription() + " attacks you for " + finaldmg + " damage!");
                    }
                    //If players health is higher
                } else {
                    //Defend to protect health
                    enemy.defend();
                    System.out.println(enemy.getDescription() + " takes a defensive stance...");
                }
            }
        //If the enemy does not have enough mana for their special move
        } else {
            //Check if the player's health is lower than the enemy's
            if (hpPlayer < hpEnemy) {
                //If the player's health is lower, attack normally
                dmg = enemy.attack();
                if(dmg==0){
                    System.out.println(enemy.getDescription() + " missed!");
                } else {
                    finaldmg = player.receiveDamage(dmg);
                    System.out.println(enemy.getDescription() + " attacks you for " + finaldmg + " damage!");
                }
                //If the player's health is higher
            } else {
                //60% chance to attack, 40% chance defend
                int i = rnd.nextInt(9);
                if(i<6) {
                    dmg = enemy.attack();
                    if(dmg==0){
                        System.out.println(enemy.getDescription() + " missed!");
                    } else {
                        finaldmg = player.receiveDamage(dmg);
                        System.out.println(enemy.getDescription() + " attacks you for " + finaldmg + " damage!");
                    }
                }else{
                    enemy.defend();
                    System.out.println(enemy.getDescription() + " takes a defensive stance...");
                }
            }
        }
    }

    //uses io stream to save player/enemy instance data to a HashMap to be saved to JSON file
    private static void gameSave(){
        try {
            HashMap<String,Integer> saveMap = player.savePotsMap(player.getStats()); //takes original stats and runs them through addPots method, saves to map
            BufferedWriter writer = new BufferedWriter(new FileWriter("afSave.json"));
            gson.toJson(saveMap, writer);
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //uses io stream to load data from JSON file into HashMap data and pass through the init method
    public static HashMap<String,Integer> gameLoad(){
            File f = new File("afSave.json");
            Player defaults = new Player();
            HashMap<String,Integer> defaultMap = defaults.getStats();
            if(!f.exists()) {
                System.out.println(ConsoleColors.RED + "No save file found, loading defaults" + ConsoleColors.RESET);
                return defaultMap;
            } else {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("afSave.json"));
                        HashMap<String, Double> map;
                        try {
                            map = gson.fromJson(reader, HashMap.class);
                        } catch (Exception e) {
                            System.out.println(ConsoleColors.RED + "Corrupt save file, loading defaults" + ConsoleColors.RESET);
                            return defaultMap;
                        }
                        reader.close();
                        System.out.println(ConsoleColors.GREEN_BOLD + "Save file successfully loaded :)" + ConsoleColors.RESET);
                        return convertHashMap(map);
                    } catch (Exception e) {
                        System.out.println("Error: An unexpected error occurred");
                        return defaultMap;
                    }
            }
    }

    //checks if player health reaches <= 0, then ends game, displays score/stats screen.
    //TODO actual logic and end game screen via display
    private static void gameEnd() {
            System.out.println(ConsoleColors.BLACK_BACKGROUND + ConsoleColors.RED_BOLD + "YOU DIED..." + ConsoleColors.RESET);
            System.out.println("SCORE: " + kills*785);
            gameActive = false;
            System.out.println("\nStart new game? (use number) (" + ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + "1." + ConsoleColors.RESET + " Yes - " + ConsoleColors.WHITEBRIGHTBOLD + ConsoleColors.BLACK_BACKGROUND + "2." + ConsoleColors.RESET + " No)");
            String userInput = getInput(sc);
            if(userInput.equals("1")){
            gameActive = true;
            } else {
                System.out.println("Good Game :)\nGoodbye!");
                System.exit(0);
            }
    }
    public static HashMap<String,Integer> convertHashMap(HashMap<String,Double> doubleMap){
        HashMap<String,Integer> intMap = new HashMap<>();

        for (HashMap.Entry<String, Double> entry : doubleMap.entrySet()) {
            intMap.put(entry.getKey(), (int) entry.getValue().doubleValue());
        }

        return intMap;
    }

    public static String getInput(Scanner input) {
        boolean isValid = false;
        String userInput;
        do {
            userInput = input.nextLine();
            //checks for exit to quit program before anything else
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                System.exit(0);
            } else if (userInput.matches("\\d+")) {
                int i = Integer.parseInt(userInput);
                if(isPrompt) {
                    if (i >= 0 && i < 7) {
                        isValid = true;
                    } else {
                        System.out.println("Please enter a valid number\n");
                    }
                } else {
                    if (i > 0 && i < 6) {
                        isValid = true;
                    } else {
                        System.out.println("Please enter a valid number\n");
                    }
                }
            } else {
                System.out.println("Please enter a valid selection");
            }
        } while(!isValid);
            System.out.println("\n");
            return userInput; //returns the string once properly validated
    }

}