package ru.practicum.base.utils;

public enum State {
    PENDING,
    PUBLISHED,
    CANCELED;

    public static State convertFromString(String state) {
        for (State value : State.values()) {
            if (value.name().equalsIgnoreCase(state)) {
                return value;
            }
        }
        return null;
    }
}
