package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.enums.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserInfo {

    private String userId;

    private String password;

    private Role role;
}
