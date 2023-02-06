import com.google.gson.Gson;
import com.homedepot.om.student.Enemy;
import com.homedepot.om.student.Main;
import com.homedepot.om.student.Player;
import org.junit.jupiter.api.*;

import static com.homedepot.om.student.Main.gameLoad;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;


public class AzerothsFuryTest {

    @BeforeAll
    static void setup() {
    }

    @BeforeEach
    void init() {

    }
    @Test
    @DisplayName("Tests that game loads defaults when missing save file")
    public void testMissingFile() {
        File f = new File("afSave.json");
        if(f.exists()) {
            f.delete();
        }

        HashMap<String,Integer> result = gameLoad();
        Player defaults = new Player();
        HashMap<String,Integer> expected = defaults.getStats();

        assertEquals(expected, result);
    }

    @Test
    public void testLoadSavedGame() {
        Gson gson = new Gson();
        // create a dummy save file
        HashMap<String, Integer> mapStats = new HashMap<>();
        mapStats.put("Health", 600);
        mapStats.put("MaxHealth", 600);
        mapStats.put("Mana", 750);
        mapStats.put("MaxMana", 800);
        mapStats.put("Attack", 3);
        mapStats.put("Spell", 6);
        mapStats.put("Defense", 3);
        mapStats.put("Critical", 5);
        mapStats.put("Class", 0);
        mapStats.put("Kills", 10);
        mapStats.put("HealthPots", 1);
        mapStats.put("ManaPots", 2);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("afSave.json"));
            gson.toJson(mapStats, writer);
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        // call the load function and check the result
        HashMap<String, Integer> loadedData = gameLoad();
        assertEquals(600, loadedData.get("Health"));
        assertEquals(600, loadedData.get("MaxHealth"));
        assertEquals(750, loadedData.get("Mana"));
        assertEquals(800, loadedData.get("MaxMana"));
        assertEquals(3, loadedData.get("Attack"));
        assertEquals(6, loadedData.get("Spell"));
        assertEquals(3, loadedData.get("Defense"));
        assertEquals(5, loadedData.get("Critical"));
        assertEquals(0, loadedData.get("Class"));
        assertEquals(10, loadedData.get("Kills"));
        assertEquals(1, loadedData.get("HealthPots"));
        assertEquals(2, loadedData.get("ManaPots"));

    }
    @Test
    //tests that class description set correctly when loading savefile
    void testCorrectDescription(){
        Player paladin = new Player("paladin");
        Gson gson = new Gson();
        // create a dummy save file
        HashMap<String, Integer> mapStats = new HashMap<>();
        mapStats.put("Health", 600);
        mapStats.put("MaxHealth", 600);
        mapStats.put("Mana", 750);
        mapStats.put("MaxMana", 800);
        mapStats.put("Attack", 3);
        mapStats.put("Spell", 6);
        mapStats.put("Defense", 3);
        mapStats.put("Critical", 5);
        mapStats.put("Class", 5);
        mapStats.put("Kills", 10);
        mapStats.put("HealthPots", 1);
        mapStats.put("ManaPots", 2);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("afSave.json"));
            gson.toJson(mapStats, writer);
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        Main.init(gameLoad());
        assertEquals("\u001B[38;5;13mTroll Paladin\033[0m", paladin.getDescription());
    }

    //tests that when enemy drops health potions, it is accurately set to the player object
    @Test
    public void testAddPots() {
        Player player = new Player();
        Enemy enemy = new Enemy();
        HashMap<String,Integer> dropMap = enemy.drop();
        System.out.println(dropMap);
        int hp, mp;
        hp = dropMap.get("Health Potion");
        System.out.println(hp);
        mp = dropMap.get("Mana Potion");
        System.out.println(mp);
        player.receivePots(dropMap);

        assertEquals(hp, player.getHealthPots(), "Health Pots Test");
        assertEquals(mp, player.getManaPots(), "Mana Pots Test");
    }

    @Test
    public void testMapConversion(){
        Player player = new Player();
        Gson gson = new Gson();
        HashMap<String,Integer> originalStats = player.getStats();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("afSave.json"));
            gson.toJson(player.getStats(), writer);
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        HashMap<String,Integer> loadedStats = gameLoad();
        assertEquals(originalStats, loadedStats, "Tests to check if original written hashmap is converted back to integer correctly.");
    }

    @Test
    public void testPlayerHPZero() {
        Player player = new Player();
        player.setCurrentHealth(10);
        System.out.println("hp: " + player.getCurrentHealth());
        player.receiveDamage(15);
        assertEquals(0, player.getCurrentHealth(), "Health under zero test");
    }
}
