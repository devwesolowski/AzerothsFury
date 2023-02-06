package com.homedepot.om.student;

import java.util.HashMap;
import java.util.Random;

public class Enemy extends Character {
    //default constructor uses setClass method to randomize class selection
    public Enemy() {
        setClass();
    }
    Enemy(int hp, int mp, int ap, int sp, int def, int crt) {
        super(hp, mp, ap, sp, def, crt);
    }
    Enemy(String heroClass) {
        setClass(heroClass);
    }

    // Method returns Health Potion or Mana Potion and how many dropped.
    // Will be chance to drop then smaller chance to double the drop
    public HashMap<String, Integer> drop() {
        int amt = 0;
        Random rnd = new Random();
        HashMap<String, Integer> dropMap = new HashMap<>();
        if((rnd.nextInt(2) > 0)){
            amt = 1;
        }
        dropMap.put("Mana Potion", amt);
        if(rnd.nextInt(10) > 8){
            amt = 2;
        } else { amt = 1; }
        dropMap.put("Health Potion", amt);
        return dropMap;
    }
    // Method will set enemy class, while also handicapping the stats -1
    @Override
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
        setStats(hp-100, mp-50, ap-1, sp-1, def-1, crt-1);
        setDescription(id);
    }
}

