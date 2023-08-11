package ru.practicum.base.dto.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import ru.practicum.base.dto.request.ParticipationRequestDto;

class EventRequestStatusUpdateResultTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventRequestStatusUpdateResult#EventRequestStatusUpdateResult()}
     *   <li>{@link EventRequestStatusUpdateResult#setConfirmedRequests(List)}
     *   <li>{@link EventRequestStatusUpdateResult#setRejectedRequests(List)}
     *   <li>{@link EventRequestStatusUpdateResult#getConfirmedRequests()}
     *   <li>{@link EventRequestStatusUpdateResult#getRejectedRequests()}
     * </ul>
     */
    @Test
    void testConstructor() {
        EventRequestStatusUpdateResult actualEventRequestStatusUpdateResult = new EventRequestStatusUpdateResult();
        ArrayList<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
        actualEventRequestStatusUpdateResult.setConfirmedRequests(confirmedRequests);
        ArrayList<ParticipationRequestDto> rejectedRequests = new ArrayList<>();
        actualEventRequestStatusUpdateResult.setRejectedRequests(rejectedRequests);
        List<ParticipationRequestDto> confirmedRequests2 = actualEventRequestStatusUpdateResult.getConfirmedRequests();
        assertSame(confirmedRequests, confirmedRequests2);
        assertEquals(rejectedRequests, confirmedRequests2);
        List<ParticipationRequestDto> rejectedRequests2 = actualEventRequestStatusUpdateResult.getRejectedRequests();
        assertSame(rejectedRequests, rejectedRequests2);
        assertEquals(confirmedRequests2, rejectedRequests2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EventRequestStatusUpdateResult#EventRequestStatusUpdateResult(List, List)}
     *   <li>{@link EventRequestStatusUpdateResult#setConfirmedRequests(List)}
     *   <li>{@link EventRequestStatusUpdateResult#setRejectedRequests(List)}
     *   <li>{@link EventRequestStatusUpdateResult#getConfirmedRequests()}
     *   <li>{@link EventRequestStatusUpdateResult#getRejectedRequests()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ArrayList<ParticipationRequestDto> confirmedRequests = new ArrayList<>();
        ArrayList<ParticipationRequestDto> rejectedRequests = new ArrayList<>();
        EventRequestStatusUpdateResult actualEventRequestStatusUpdateResult = new EventRequestStatusUpdateResult(
                confirmedRequests, rejectedRequests);
        ArrayList<ParticipationRequestDto> confirmedRequests2 = new ArrayList<>();
        actualEventRequestStatusUpdateResult.setConfirmedRequests(confirmedRequests2);
        ArrayList<ParticipationRequestDto> rejectedRequests2 = new ArrayList<>();
        actualEventRequestStatusUpdateResult.setRejectedRequests(rejectedRequests2);
        List<ParticipationRequestDto> confirmedRequests3 = actualEventRequestStatusUpdateResult.getConfirmedRequests();
        assertSame(confirmedRequests2, confirmedRequests3);
        assertEquals(confirmedRequests, confirmedRequests3);
        assertEquals(rejectedRequests, confirmedRequests3);
        assertEquals(rejectedRequests2, confirmedRequests3);
        List<ParticipationRequestDto> rejectedRequests3 = actualEventRequestStatusUpdateResult.getRejectedRequests();
        assertSame(rejectedRequests2, rejectedRequests3);
        assertEquals(confirmedRequests, rejectedRequests3);
        assertEquals(rejectedRequests, rejectedRequests3);
        assertEquals(confirmedRequests3, rejectedRequests3);
    }
}

