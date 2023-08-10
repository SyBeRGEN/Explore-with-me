package ru.practicum.base.dto.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import ru.practicum.base.utils.StateActionAdmin;

class UpdateEventAdminRequestTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateEventAdminRequest#UpdateEventAdminRequest()}
     *   <li>{@link UpdateEventAdminRequest#setStateAction(StateActionAdmin)}
     *   <li>{@link UpdateEventAdminRequest#getStateAction()}
     * </ul>
     */
    @Test
    void testConstructor() {
        UpdateEventAdminRequest actualUpdateEventAdminRequest = new UpdateEventAdminRequest();
        actualUpdateEventAdminRequest.setStateAction(StateActionAdmin.PUBLISH_EVENT);
        assertEquals(StateActionAdmin.PUBLISH_EVENT, actualUpdateEventAdminRequest.getStateAction());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateEventAdminRequest#UpdateEventAdminRequest(StateActionAdmin)}
     *   <li>{@link UpdateEventAdminRequest#setStateAction(StateActionAdmin)}
     *   <li>{@link UpdateEventAdminRequest#getStateAction()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        UpdateEventAdminRequest actualUpdateEventAdminRequest = new UpdateEventAdminRequest(
                StateActionAdmin.PUBLISH_EVENT);
        actualUpdateEventAdminRequest.setStateAction(StateActionAdmin.PUBLISH_EVENT);
        assertEquals(StateActionAdmin.PUBLISH_EVENT, actualUpdateEventAdminRequest.getStateAction());
    }
}

