package ru.practicum.base.dto.location;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LocationTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Location#Location()}
     *   <li>{@link Location#setLat(Float)}
     *   <li>{@link Location#setLon(Float)}
     *   <li>{@link Location#getLat()}
     *   <li>{@link Location#getLon()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Location actualLocation = new Location();
        actualLocation.setLat(10.0f);
        actualLocation.setLon(10.0f);
        assertEquals(10.0f, actualLocation.getLat().floatValue());
        assertEquals(10.0f, actualLocation.getLon().floatValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Location#Location(Float, Float)}
     *   <li>{@link Location#setLat(Float)}
     *   <li>{@link Location#setLon(Float)}
     *   <li>{@link Location#getLat()}
     *   <li>{@link Location#getLon()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Location actualLocation = new Location(10.0f, 10.0f);
        actualLocation.setLat(10.0f);
        actualLocation.setLon(10.0f);
        assertEquals(10.0f, actualLocation.getLat().floatValue());
        assertEquals(10.0f, actualLocation.getLon().floatValue());
    }
}

