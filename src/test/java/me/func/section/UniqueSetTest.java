package me.func.section;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueSetTest {

    private UniqueSet<String> uniqueSet;

    @BeforeEach
    void setUp() {
        uniqueSet = new ConcurrentUniqueSet<>();
    }

    @Test
    void testAddAndGetUnique() {
        uniqueSet.add("A");
        assertEquals("A", uniqueSet.getUnique());
    }

    @Test
    void testAddSameElementMultipleTimes() {
        uniqueSet.add("A");
        uniqueSet.add("A");
        assertNull(uniqueSet.getUnique());

        uniqueSet.add("B");
        assertEquals("B", uniqueSet.getUnique());
    }

    @Test
    void testRemoveElement() {
        uniqueSet.add("A");
        uniqueSet.add("B");
        uniqueSet.add("A");

        uniqueSet.remove("A");
        assertEquals("A", uniqueSet.getUnique());

        uniqueSet.remove("A");
        assertEquals("B", uniqueSet.getUnique());
    }

    @Test
    void testRemoveNonExistingElement() {
        uniqueSet.add("A");
        uniqueSet.remove("B");
        assertEquals("A", uniqueSet.getUnique());
    }

    @Test
    void testRemoveUntilEmpty() {
        uniqueSet.add("A");
        uniqueSet.add("B");
        uniqueSet.add("A");

        uniqueSet.remove("A");
        assertEquals("A", uniqueSet.getUnique());

        uniqueSet.remove("B");
        assertEquals("A", uniqueSet.getUnique());

        uniqueSet.remove("A");
        assertNull(uniqueSet.getUnique());
    }
}