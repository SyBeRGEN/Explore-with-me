package ru.practicum.base.dto.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.practicum.base.utils.StateActionUser;

class UpdateEventUserRequestTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateEventUserRequest#UpdateEventUserRequest()}
     *   <li>{@link UpdateEventUserRequest#setStateAction(StateActionUser)}
     *   <li>{@link UpdateEventUserRequest#getStateAction()}
     * </ul>
     */
    @Test
    void testConstructor() {
        UpdateEventUserRequest actualUpdateEventUserRequest = new UpdateEventUserRequest();
        actualUpdateEventUserRequest.setStateAction(StateActionUser.SEND_TO_REVIEW);
        assertEquals(StateActionUser.SEND_TO_REVIEW, actualUpdateEventUserRequest.getStateAction());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateEventUserRequest#UpdateEventUserRequest(StateActionUser)}
     *   <li>{@link UpdateEventUserRequest#setStateAction(StateActionUser)}
     *   <li>{@link UpdateEventUserRequest#getStateAction()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        UpdateEventUserRequest actualUpdateEventUserRequest = new UpdateEventUserRequest(StateActionUser.SEND_TO_REVIEW);
        actualUpdateEventUserRequest.setStateAction(StateActionUser.SEND_TO_REVIEW);
        assertEquals(StateActionUser.SEND_TO_REVIEW, actualUpdateEventUserRequest.getStateAction());
    }
}

