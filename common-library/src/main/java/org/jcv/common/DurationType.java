package org.jcv.common;

public enum DurationType {
    D("D", "Days"),
    H("H", "Hours"),
    N("N", "Nights");

    private final String code;
    private final String name;

    DurationType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    // Find enum by code (useful for parsing)
    public static DurationType fromCode(String code) {
        for (DurationType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid duration type code: " + code);
    }
}
