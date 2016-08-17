package com.smartfarmh2.transfer;

import java.util.Map;

/**
 * Created by Asus on 18/8/2559.
 */
public class UserTransfer {
    private final String username;
    private final Map<String, Boolean> roles;


    public UserTransfer(String username, Map<String, Boolean> roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Boolean> getRoles() {
        return roles;
    }
}