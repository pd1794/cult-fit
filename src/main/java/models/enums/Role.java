package models.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    ADMIN("admin"),
    CONSUMER("consumer");

    private String role;

    public String getRole() {
        return this.role;
    }
}
