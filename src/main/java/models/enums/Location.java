package models.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Location {
    KORAMANGALA("Koramangala"),
    BELLANDUR("Bellandur");

    private String location;

    public String getLocation() {
        return this.location;
    }
}
