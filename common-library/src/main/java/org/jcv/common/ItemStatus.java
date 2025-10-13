package org.jcv.common;

import lombok.Getter;

@Getter
public enum ItemStatus {
    NEW("100", "NEW"),
    MODIFIED("200", "MODIFIED"),
    UNCHANGED("300", "UNCHANGED"),
    EXPIRED("400", "EXPIRED");

    private final String code;
    private final String name;

    ItemStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }


    // Find enum by code (useful for parsing)
    public static ItemStatus fromCode(String code) {
        for (ItemStatus type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid item status code: " + code);
    }
}
