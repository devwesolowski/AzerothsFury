package com.homedepot.om.student;

import java.util.HashMap;

// holds player information stats as well as health and mana potion count,
// allows player to then replenish those stats respectively when used.
public class Player extends Character {
    private int healthPots, manaPots;
    //default constructor will play as mage
    public Player(){
        setClass("mage");
    }
    public Player(String heroClass){
        setClass(heroClass);
    }
    Player(int hPots, int mPots){
        this.healthPots = hPots;
        this.manaPots = mPots;
    }
    Player(int hp, int mp, int ap, int sp, int def, int crt){
        super(hp, mp, ap, sp, def, crt);
    }
    Player(int hp, int mp, int ap, int sp, int def, int crt, int hPots, int mPots){
        super(hp, mp, ap, sp, def, crt);
        this.healthPots = hPots;
        this.manaPots = mPots;
    }
    //adds current potions to the stats hashmap for saving data
    public HashMap<String,Integer> savePotsMap(HashMap<String,Integer> statsMap) {
        statsMap.put("HealthPots" , healthPots);
        statsMap.put("ManaPots", manaPots);
        return statsMap;
    }
    //Loads in data from save data, loads it if there are values, but does nothing if not.
    //Will initialize and load the old number of potions from the save data hashmap
    public void loadPotsMap(HashMap<String,Integer> statsMap){
        if(statsMap.get("HealthPots") == null || statsMap.get("ManaPots") == null){

        } else {
            this.healthPots = statsMap.get("HealthPots");
            this.manaPots = statsMap.get("ManaPots");
        }
    }
    //takes drop data from enemy drop hashmap, and adds the items using respective add methods
    public void receivePots(HashMap<String,Integer> dropMap){
        addHealthPots(dropMap.get("Health Potion"));
        addManaPots(dropMap.get("Mana Potion"));
    }
    //getters and setters
    public int getHealthPots() {
        return healthPots;
    }
    public void setHealthPots(int healthPots) {
        this.healthPots = healthPots;
    }
    public int getManaPots() {
        return manaPots;
    }
    public void setManaPots(int manaPots) {
        this.manaPots = manaPots;
    }
    public void savePotsMap(int x, int y){
        this.healthPots += x;
        this.manaPots += y;
    }
    public void removePots(int x, int y){
        this.healthPots -= x;
        this.manaPots -= y;
    }
    public void addManaPots(int i){
        this.manaPots += i;
    }
    public void removeManaPots(int i){
        this.manaPots -= i;
    }
    public void addHealthPots(int i){
        this.healthPots += i;

    }
    public void removeHealthPots(int i){
        this.healthPots -= i;
    }
}
