package org.jcv.common;

public enum ProductType {
    TOU("TOU", "Tour"),
    HTL("HTL", "Hotel"),
    FLT("FLT", "Flight");

    private final String code;
    private final String name;

    ProductType(String code, String name) {
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
    public static ProductType fromCode(String code) {
        for (ProductType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid product type code: " + code);
    }
}
