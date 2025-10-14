package by.internship.practical_course.skynet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactionTest {

    private Storage storage;
    private Faction faction;


    @BeforeEach
    void setUp() {
        storage = new Storage();
        faction = new Faction("TestFaction", storage);
    }


    @Test
    void testBuildRobotWithCompleteSet() {
        faction.heads = 1;
        faction.torsos = 1;
        faction.hands = 2;
        faction.feet = 2;

        faction.buildRobots();

        assertEquals(1, faction.getRobotsBuilt());
        assertEquals(0, faction.heads);
        assertEquals(0, faction.torsos);
        assertEquals(0, faction.hands);
        assertEquals(0, faction.feet);
    }

    @Test
    void testBuildRobotWithNoSet() {
        faction.heads = 1;
        faction.torsos = 0;
        faction.hands = 2;
        faction.feet = 2;

        faction.buildRobots();

        assertEquals(0, faction.getRobotsBuilt());
    }


    @Test
    void testAddPartToInventory() {
        faction.addPartToInventory("head");
        faction.addPartToInventory("torso");
        faction.addPartToInventory("hand");
        faction.addPartToInventory("foot");

        assertEquals(1, faction.heads);
        assertEquals(1, faction.torsos);
        assertEquals(1, faction.hands);
        assertEquals(1, faction.feet);
    }

    @Test
    void testGetRobotsBuilt() {
        assertEquals(0, faction.getRobotsBuilt());

        faction.heads = 1;
        faction.torsos = 1;
        faction.hands = 2;
        faction.feet = 2;
        faction.buildRobots();

        assertEquals(1, faction.getRobotsBuilt());
    }

}