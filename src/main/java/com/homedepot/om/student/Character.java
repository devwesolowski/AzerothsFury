package com.homedepot.om.student;

import java.util.HashMap;
import java.util.Random;

public class Character {

    private int currentHealth, maxHealth, currentMana, maxMana, attack, spell, defense, critical, id, kills;
    private String description, heroClass, special;
    Random rand = new Random();
    private boolean isDefending = false;
    private boolean isOOM = false;
    HeroClass heroMap = new HeroClass();

    Character() {

    }

    Character(int hp, int mp, int ap, int sp, int def, int crt){
        maxHealth = hp;
        currentHealth = hp;
        maxMana = mp;
        currentMana = mp;
        attack = ap;
        spell = sp;
        defense = def;
        critical = crt;
    }

    //Takes attack power to calculate dmg
    //Always 15% chance to miss
    int attack() {
        int dmg = 0;
        //15% miss logic
        if(rand.nextInt(99)>14){
            dmg += attack*35;
        }

        return dmg;
    }

    //Sets the status that character is now defending.
    //Used in the receiveDamage method to determine damage taken
    void defend() {
        isDefending = true;
    }

    //Special Move, uses mana to attack. Slightly more powerful than normal attack.
    //Always 10% to fizzle out and miss. If not enough mana it will just attack instead.
    //Currently mana cost is always 50, but will change in future
    int special(int manaCost){
        int dmg = 0;
        //if this spell costs more than the current manapool, attacks instead.
        if((currentMana-manaCost)<0){
            isOOM = true;
            dmg = attack();
            if (dmg!=0){
                System.out.println("\n\n\n\n\nNot enough mana, attacking instead...");
            }
        } else {
            currentMana -= manaCost;
            //15% miss logic
            if(rand.nextInt(99)>9){
                dmg += spell*50;
            }
        }
        return dmg;
    }

    //The damage from attack or special is passed through and ran through modifiers.
    //It first checks if the character receiving damage is defending or not. If they are,
    //the damage is multiplied but the characters defence, then 0.1, taking reduced damage.
    //If they are not defending, it first checks if damage will kill them, if not then subtract
    //damage from players current health.
    public int receiveDamage(int dmg){
        if(isDefending){
            isDefending = false;
            dmg = (int) (dmg*defense*0.1);
        }
        if((currentHealth - dmg) < 0) {
            currentHealth = 0;
        } else {
            currentHealth -= dmg;
        }
        return dmg;
    }
    public void setOOM(boolean isOOM){
        this.isOOM = isOOM;
    }
    public boolean isOOM(){
        return isOOM;
    }

    public boolean isDefending() {
        return isDefending;
    }

    public void setDefending(boolean defending) {
        isDefending = defending;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpell() {
        return spell;
    }

    public void setSpell(int spell) {
        this.spell = spell;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public String getSpecial(){
        return special;
    }

    public void setSpecial(String special){
        this.special = special;
    }

    public void setKills(int kills){
        this.kills = kills;
    }

    public int getKills(){
        return this.kills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public HashMap<String, int[]> getHeroes(){
        return heroMap.getClassData();
    }

    public boolean isAlive() {
        return this.currentHealth > 0;
    }

    //takes in class id, and assigns the class, special, and name of enemy, then assigns the id as well
    public void setDescription(int id){
        switch(id){
            case 0:
                description = "\u001B[38;5;32mHuman Mage       \033[0m";
                special = "\u001B[38;5;69m[Pyroblast]\033[0m";
                heroClass = "mage";
                this.id = 0;
                break;
            case 1:
                description = "\u001B[38;5;63mGnome Warlock    \033[0m";
                special = "\u001B[38;5;69m[Drain Health]\033[0m";
                heroClass = "warlock";
                this.id = 1;
                break;
            case 2:
                description = "\u001B[38;5;35mNight Elf Hunter \033[0m";
                special = "\u001B[38;5;69m[Aimed Shot]\033[0m";
                heroClass = "hunter";
                this.id = 2;
                break;
            case 3:
                description = "\u001B[38;5;11mGoblin Rogue     \033[0m";
                special = "\u001B[38;5;69m[Backstab]\033[0m";
                heroClass = "rogue";
                this.id = 3;
                break;
            case 4:
                description = "\u001B[38;5;94mOrc Warrior      \033[0m";
                special = "\u001B[38;5;69m[Hamstring]\033[0m";
                heroClass = "warrior";
                this.id = 4;
                break;
            case 5:
                description = "\u001B[38;5;13mTroll Paladin\033[0m";
                special = "\u001B[38;5;69m[Judgement]\033[0m";
                heroClass = "paladin";
                this.id = 5;
                break;
            default:
                description = "\u001B[38;5;32mHuman Mage\033[0m";
                special = "\u001B[38;5;69m[Pyroblast]\033[0m";
                heroClass = "mage";
                this.id = 0;
                break;
        }
    }

    public void setDescription(){
        switch(this.id){
            case 0:
                description = "\u001B[38;5;32mHuman Mage       \033[0m";
                special = "\u001B[38;5;69m[Pyroblast]\033[0m";
                heroClass = "mage";
                break;
            case 1:
                description = "\u001B[38;5;63mGnome Warlock    \033[0m";
                special = "\u001B[38;5;69m[Drain Health]\033[0m";
                heroClass = "warlock";
                break;
            case 2:
                description = "\u001B[38;5;35mNight Elf Hunter \033[0m";
                special = "\u001B[38;5;69m[Aimed Shot]\033[0m";
                heroClass = "hunter";
                break;
            case 3:
                description = "\u001B[38;5;11mGoblin Rogue     \033[0m";
                special = "\u001B[38;5;69m[Backstab]\033[0m";
                heroClass = "rogue";
                break;
            case 4:
                description = "\u001B[38;5;94mOrc Warrior      \033[0m";
                special = "\u001B[38;5;69m[Hamstring]\033[0m";
                heroClass = "warrior";
                break;
            case 5:
                description = "\u001B[38;5;13mTroll Paladin\033[0m";
                special = "\u001B[38;5;69m[Judgement]\033[0m";
                heroClass = "paladin";
                break;
            default:
                description = "\u001B[38;5;32mHuman Mage\033[0m";
                special = "\u001B[38;5;69m[Pyroblast]\033[0m";
                heroClass = "mage";
                break;
        }
    }

    //default method randomizes class when called
    public void setClass(){
        String classChoice;
        //randomizes 0-5 using the size of hashmap from our HeroClass, then sets class
        classChoice = switch (rand.nextInt(getHeroes().size())) {
            case 0 -> "mage";
            case 1 -> "warlock";
            case 2 -> "hunter";
            case 3 -> "rogue";
            case 4 -> "warrior";
            case 5 -> "paladin";
            default -> "mage";
        };
        setClass(classChoice);
    }

    // Method will take in the class name, then set the characters stats correctly...
    public void setClass(String heroClass){
        HashMap<String,int[]> heroes = heroMap.getClassData();
        int[] stats = heroes.get(heroClass);
        int hp, mp, ap, sp, def, crt, id, index;
        index = 0;
        hp = stats[index++];
        mp = stats[index++];
        ap = stats[index++];
        sp = stats[index++];
        def = stats[index++];
        crt = stats[index++];
        id = stats[index];
        setStats(hp, mp, ap, sp, def, crt);
        setDescription(id);
    }

    //Method to take in given stats and apply to character
    public void setStats(int hp, int mp, int ap, int sp, int def, int crt) {
        maxHealth = hp;
        currentHealth = hp;
        maxMana = mp;
        currentMana = mp;
        attack = ap;
        spell = sp;
        defense = def;
        critical = crt;
    }

    //Gets all Character Stats and returns data saved in hashmap
    public HashMap<String,Integer> getStats() {
        HashMap<String, Integer> mapStats = new HashMap<>();
        mapStats.put("Health", currentHealth);
        mapStats.put("MaxHealth", maxHealth);
        mapStats.put("Mana", currentMana);
        mapStats.put("MaxMana", maxMana);
        mapStats.put("Attack", attack);
        mapStats.put("Spell", spell);
        mapStats.put("Defense", defense);
        mapStats.put("Critical", critical);
        mapStats.put("Class", id);
        mapStats.put("Kills", kills);
        return mapStats;
    }

    //Takes in hashmap of character stats and sets character accordingly
    public void setStats(HashMap<String, Integer> mapStats){
        currentHealth = mapStats.get("Health");
        maxHealth = mapStats.get("MaxHealth");
        currentMana = mapStats.get("Mana");
        maxMana = mapStats.get("MaxMana");
        attack = mapStats.get("Attack");
        spell = mapStats.get("Spell");
        defense = mapStats.get("Defense");
        critical = mapStats.get("Critical");
        id = mapStats.get("Class");
        kills = mapStats.get("Kills");
    }
}

