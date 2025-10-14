package by.internship.practical_course.skynet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage();
    }

    @Test
    void addAndTakePartOfBody() {
        storage.addPartOfBody("head");
        storage.addPartOfBody("torso");
        storage.addPartOfBody("hand");
        storage.addPartOfBody("foot");

        assertTrue(storage.takePartOfBody("head"));
        assertTrue(storage.takePartOfBody("torso"));
        assertTrue(storage.takePartOfBody("hand"));
        assertTrue(storage.takePartOfBody("foot"));

    }

    @Test
    void takeFromEmptyStorage() {
        assertFalse(storage.takePartOfBody("head"));
        assertFalse(storage.takePartOfBody("torso"));
        assertFalse(storage.takePartOfBody("hand"));
        assertFalse(storage.takePartOfBody("foot"));
    }
}