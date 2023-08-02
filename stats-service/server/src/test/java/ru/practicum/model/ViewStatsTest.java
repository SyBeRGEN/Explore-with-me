package ru.practicum.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ViewStatsTest {

    @Test
    void testConstructor() {
        ViewStats actualViewStats = new ViewStats();
        actualViewStats.setApp("App");
        actualViewStats.setHits(1L);
        actualViewStats.setUri("Uri");
        assertEquals("App", actualViewStats.getApp());
        assertEquals(1L, actualViewStats.getHits().longValue());
        assertEquals("Uri", actualViewStats.getUri());
    }

    @Test
    void testConstructor2() {
        ViewStats actualViewStats = new ViewStats("App", "Uri", 1L);
        actualViewStats.setApp("App");
        actualViewStats.setHits(1L);
        actualViewStats.setUri("Uri");
        assertEquals("App", actualViewStats.getApp());
        assertEquals(1L, actualViewStats.getHits().longValue());
        assertEquals("Uri", actualViewStats.getUri());
    }
}

