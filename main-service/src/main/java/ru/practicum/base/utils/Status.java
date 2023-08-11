package ru.practicum.base.utils;

public enum Status {
    CONFIRMED,
    REJECTED,
    PENDING,
    CANCELED;

    public static Status containsCheck(String status) {
        for (Status value : Status.values()) {
            if (value.name().equalsIgnoreCase(status)) {
                return value;
            }
        }
        return null;
    }
}
