package ru.practicum.base.dto.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NewUserRequestTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link NewUserRequest#NewUserRequest()}
     *   <li>{@link NewUserRequest#setEmail(String)}
     *   <li>{@link NewUserRequest#setName(String)}
     *   <li>{@link NewUserRequest#getEmail()}
     *   <li>{@link NewUserRequest#getName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        NewUserRequest actualNewUserRequest = new NewUserRequest();
        actualNewUserRequest.setEmail("jane.doe@example.org");
        actualNewUserRequest.setName("Name");
        assertEquals("jane.doe@example.org", actualNewUserRequest.getEmail());
        assertEquals("Name", actualNewUserRequest.getName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link NewUserRequest#NewUserRequest(String, String)}
     *   <li>{@link NewUserRequest#setEmail(String)}
     *   <li>{@link NewUserRequest#setName(String)}
     *   <li>{@link NewUserRequest#getEmail()}
     *   <li>{@link NewUserRequest#getName()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        NewUserRequest actualNewUserRequest = new NewUserRequest("jane.doe@example.org", "Name");
        actualNewUserRequest.setEmail("jane.doe@example.org");
        actualNewUserRequest.setName("Name");
        assertEquals("jane.doe@example.org", actualNewUserRequest.getEmail());
        assertEquals("Name", actualNewUserRequest.getName());
    }
}

