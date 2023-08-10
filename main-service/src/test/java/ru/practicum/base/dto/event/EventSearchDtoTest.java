package ru.practicum.base.dto.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class EventSearchDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventSearchDto#EventSearchDto()}
     *   <li>{@link EventSearchDto#setCategories(List)}
     *   <li>{@link EventSearchDto#setOnlyAvailable(Boolean)}
     *   <li>{@link EventSearchDto#setPaid(Boolean)}
     *   <li>{@link EventSearchDto#setRangeEnd(LocalDateTime)}
     *   <li>{@link EventSearchDto#setRangeStart(LocalDateTime)}
     *   <li>{@link EventSearchDto#setText(String)}
     *   <li>{@link EventSearchDto#getCategories()}
     *   <li>{@link EventSearchDto#getOnlyAvailable()}
     *   <li>{@link EventSearchDto#getPaid()}
     *   <li>{@link EventSearchDto#getRangeEnd()}
     *   <li>{@link EventSearchDto#getRangeStart()}
     *   <li>{@link EventSearchDto#getText()}
     * </ul>
     */
    @Test
    void testConstructor() {
        EventSearchDto actualEventSearchDto = new EventSearchDto();
        ArrayList<Long> categories = new ArrayList<>();
        actualEventSearchDto.setCategories(categories);
        actualEventSearchDto.setOnlyAvailable(true);
        actualEventSearchDto.setPaid(true);
        LocalDateTime rangeEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventSearchDto.setRangeEnd(rangeEnd);
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventSearchDto.setRangeStart(rangeStart);
        actualEventSearchDto.setText("Text");
        assertSame(categories, actualEventSearchDto.getCategories());
        assertTrue(actualEventSearchDto.getOnlyAvailable());
        assertTrue(actualEventSearchDto.getPaid());
        assertSame(rangeEnd, actualEventSearchDto.getRangeEnd());
        assertSame(rangeStart, actualEventSearchDto.getRangeStart());
        assertEquals("Text", actualEventSearchDto.getText());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventSearchDto#EventSearchDto(String, List, Boolean, LocalDateTime, LocalDateTime, Boolean)}
     *   <li>{@link EventSearchDto#setCategories(List)}
     *   <li>{@link EventSearchDto#setOnlyAvailable(Boolean)}
     *   <li>{@link EventSearchDto#setPaid(Boolean)}
     *   <li>{@link EventSearchDto#setRangeEnd(LocalDateTime)}
     *   <li>{@link EventSearchDto#setRangeStart(LocalDateTime)}
     *   <li>{@link EventSearchDto#setText(String)}
     *   <li>{@link EventSearchDto#getCategories()}
     *   <li>{@link EventSearchDto#getOnlyAvailable()}
     *   <li>{@link EventSearchDto#getPaid()}
     *   <li>{@link EventSearchDto#getRangeEnd()}
     *   <li>{@link EventSearchDto#getRangeStart()}
     *   <li>{@link EventSearchDto#getText()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ArrayList<Long> categories = new ArrayList<>();
        LocalDateTime rangeStart = LocalDate.of(1970, 1, 1).atStartOfDay();
        EventSearchDto actualEventSearchDto = new EventSearchDto("Text", categories, true, rangeStart,
                LocalDate.of(1970, 1, 1).atStartOfDay(), true);
        ArrayList<Long> categories2 = new ArrayList<>();
        actualEventSearchDto.setCategories(categories2);
        actualEventSearchDto.setOnlyAvailable(true);
        actualEventSearchDto.setPaid(true);
        LocalDateTime rangeEnd = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventSearchDto.setRangeEnd(rangeEnd);
        LocalDateTime rangeStart2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualEventSearchDto.setRangeStart(rangeStart2);
        actualEventSearchDto.setText("Text");
        List<Long> categories3 = actualEventSearchDto.getCategories();
        assertSame(categories2, categories3);
        assertEquals(categories, categories3);
        assertTrue(actualEventSearchDto.getOnlyAvailable());
        assertTrue(actualEventSearchDto.getPaid());
        assertSame(rangeEnd, actualEventSearchDto.getRangeEnd());
        assertSame(rangeStart2, actualEventSearchDto.getRangeStart());
        assertEquals("Text", actualEventSearchDto.getText());
    }
}

