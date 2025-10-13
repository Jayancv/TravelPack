package org.jcv.common;

import lombok.Getter;

@Getter
public enum BookingStatus {
    QUOTE("1000", "QUOTE"),
    CONFIRMED("2000", "CONFIRMED"),
    ON_REQUEST("3000", "ON_REQUEST"),
    CANCELLED("4000", "CANCELLED");

    private final String code;
    private final String name;

    BookingStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }


    // Find enum by code (useful for parsing)
    public static BookingStatus fromCode(String code) {
        for (BookingStatus type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid booking status code: " + code);
    }
}
