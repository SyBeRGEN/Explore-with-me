package ru.practicum.base.dto.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import ru.practicum.base.utils.Status;

class EventRequestStatusUpdateRequestTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventRequestStatusUpdateRequest#EventRequestStatusUpdateRequest()}
     *   <li>{@link EventRequestStatusUpdateRequest#setRequestIds(List)}
     *   <li>{@link EventRequestStatusUpdateRequest#setStatus(Status)}
     *   <li>{@link EventRequestStatusUpdateRequest#getRequestIds()}
     *   <li>{@link EventRequestStatusUpdateRequest#getStatus()}
     * </ul>
     */
    @Test
    void testConstructor() {
        EventRequestStatusUpdateRequest actualEventRequestStatusUpdateRequest = new EventRequestStatusUpdateRequest();
        ArrayList<Long> requestIds = new ArrayList<>();
        actualEventRequestStatusUpdateRequest.setRequestIds(requestIds);
        actualEventRequestStatusUpdateRequest.setStatus(Status.CONFIRMED);
        assertSame(requestIds, actualEventRequestStatusUpdateRequest.getRequestIds());
        assertEquals(Status.CONFIRMED, actualEventRequestStatusUpdateRequest.getStatus());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventRequestStatusUpdateRequest#EventRequestStatusUpdateRequest(List, Status)}
     *   <li>{@link EventRequestStatusUpdateRequest#setRequestIds(List)}
     *   <li>{@link EventRequestStatusUpdateRequest#setStatus(Status)}
     *   <li>{@link EventRequestStatusUpdateRequest#getRequestIds()}
     *   <li>{@link EventRequestStatusUpdateRequest#getStatus()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ArrayList<Long> requestIds = new ArrayList<>();
        EventRequestStatusUpdateRequest actualEventRequestStatusUpdateRequest = new EventRequestStatusUpdateRequest(
                requestIds, Status.CONFIRMED);
        ArrayList<Long> requestIds2 = new ArrayList<>();
        actualEventRequestStatusUpdateRequest.setRequestIds(requestIds2);
        actualEventRequestStatusUpdateRequest.setStatus(Status.CONFIRMED);
        List<Long> requestIds3 = actualEventRequestStatusUpdateRequest.getRequestIds();
        assertSame(requestIds2, requestIds3);
        assertEquals(requestIds, requestIds3);
        assertEquals(Status.CONFIRMED, actualEventRequestStatusUpdateRequest.getStatus());
    }
}

