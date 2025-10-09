package by.internship.practical_course.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinkedListTest {

    private CustomLinkedList<String> list;


    @BeforeEach
    void setUp() {
        list = new CustomLinkedList<>();
    }


    @Test
    void testIfListIsEmpty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }


    @Test
    void size() {
        assertEquals(0, list.size());
        list.addFirst("Стол");
        assertEquals(1, list.size());
        list.addLast("Стул");
        assertEquals(2, list.size());
        list = new CustomLinkedList<>();
        assertTrue(list.isEmpty());
    }


    @Test
    void testAddFirst() {
       list.addFirst("Шторы");
       list.addFirst("Тюль");
       assertEquals(2, list.size());
       assertEquals("Тюль", list.getFirst());
    }


    @Test
    void testAddLast() {
        list.addLast("Молоко");
        list.addLast("Хлеб");
        list.addLast("Яблоки");
        assertEquals(3, list.size());
        assertEquals("Яблоки", list.getLast());
    }


    @Test
    void testAddWithIndex() {
        list.add(0, "Молоко");
        list.add(1, "Йогурт");
        list.add(2, "Яблоки");
        list.add(3, "Фундук");

        assertEquals(4, list.size());
        assertEquals("Молоко", list.get(0));
        assertEquals("Йогурт", list.get(1));
        assertEquals("Яблоки", list.get(2));
        assertEquals("Фундук", list.get(3));

        list.add(1, "Сыр");
        assertEquals(5, list.size());
        assertEquals("Сыр", list.get(1));
        assertEquals("Яблоки", list.get(3));
    }


    @Test
    void testAddWithIndex_InvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "test"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "test"));
    }


    @Test
    void testToGetFirst() {
        list.addFirst("Шторы");
        list.addFirst("Тюль");
        assertEquals("Тюль", list.getFirst());
    }


    @Test
    void testToGetLast() {
        list.addLast("Молоко");
        list.addLast("Хлеб");
        list.addLast("Яблоки");
        assertEquals("Яблоки", list.getLast());
    }


    @Test
    void testToGetWithIndex() {
        list.add(0, "Молоко");
        list.add(1, "Йогурт");
        list.add(2, "Яблоки");
        assertEquals("Йогурт", list.get(1));
    }


    @Test
    void testToGetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.addFirst("Книга");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }


    @Test
    void testToRemoveFirst() {
        list.addLast("Молоко");
        list.addLast("Хлеб");
        list.addLast("Яблоки");

        assertEquals("Молоко", list.removeFirst());
        assertEquals(2, list.size());
        assertEquals("Хлеб", list.removeFirst());
        assertEquals(1, list.size());
    }


    @Test
    void testToRemoveLast() {
        list.addLast("Молоко");
        list.addLast("Хлеб");
        list.addLast("Яблоки");

        assertEquals("Яблоки", list.removeLast());
        assertEquals(2, list.size());
        assertEquals("Хлеб", list.removeLast());
        assertEquals(1, list.size());
    }


    @Test
    void testToRemove() {
        list.addLast("Молоко");
        list.addLast("Хлеб");
        list.addLast("Яблоки");
        list.addLast("Сок");

        assertEquals("Хлеб", list.remove(1));
        assertEquals(3, list.size());
        assertEquals("Яблоки", list.get(1));

        assertEquals("Молоко", list.remove(0));
        assertEquals(2, list.size());
        assertEquals("Яблоки", list.getFirst());

        assertEquals("Сок", list.remove(1));
        assertEquals(1, list.size());
        assertEquals("Яблоки", list.getLast());
    }


    @Test
    void testToString() {
        assertEquals("[]", list.toString());

        list.addLast("Молоко");
        assertEquals("[Молоко]", list.toString());

        list.addLast("Хлеб");
        list.addLast("Яблоки");
        assertEquals("[Молоко, Хлеб, Яблоки]", list.toString());

    }
}