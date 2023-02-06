package com.homedepot.om.student;

import java.util.HashMap;

//made static and final as this should not change, and then allows it to be called by the class
// without creating its own instance
public class HeroClass {
    private static final HashMap<String, int[]> CLASS_MAP = new HashMap<>();

    public static HashMap<String, int[]> getClassData(){
        CLASS_MAP.put("mage", new int[] {600, 800, 5, 6, 3, 5, 0});
        CLASS_MAP.put("warlock", new int[] {500, 600, 4, 7, 3, 7, 1});
        CLASS_MAP.put("hunter", new int[] {700, 200, 7, 6, 5, 5, 2});
        CLASS_MAP.put("rogue", new int[] {600, 150, 6, 5, 5, 7, 3});
        CLASS_MAP.put("warrior", new int[] {900, 100, 8, 7, 7, 4, 4});
        CLASS_MAP.put("paladin", new int[] {700, 300, 5, 8, 5, 5, 5});
        return CLASS_MAP;
    }
}
