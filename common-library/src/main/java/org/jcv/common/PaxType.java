package org.jcv.common;

import lombok.Getter;

@Getter
public enum PaxType {
    ADULT("A", "Adult"),
    TEEN("T", "Teen"),
    CHILD("C", "Child"),
    INFANT("I", "Infant");

    private final String code;
    private final String name;

    PaxType(String code, String name) {
        this.code = code;
        this.name = name;
    }


    // Find enum by code (useful for parsing)
    public static PaxType fromCode(String code) {
        for (PaxType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid pax type code: " + code);
    }
}
