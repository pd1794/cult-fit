package controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.UserInfo;
import models.enums.DaySequence;
import models.enums.Role;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class UserController {

    private Map<String, UserInfo> userInfo;

    public String registerUser(String userId, String password, String role) {
        UserInfo newUser = UserInfo.builder()
                .userId(userId)
                .password(password)
                .role(Role.valueOf(role.toUpperCase()))
                .build();

        if(!doesUserExist(userId)) {
            userInfo.put(userId, newUser);
            return "User Registered";
        } else {
            return "User already registered. Please login";
        }
    }

    public boolean doesUserExist(String userId) {
        return userInfo.containsKey(userId);
    }

    public boolean userPasswordMatch(String userId, String password) {
        return (userInfo.get(userId).getPassword().equals(password));
    }

    public UserInfo getUserInfo(String userId) {
        return userInfo.get(userId);
    }
}
