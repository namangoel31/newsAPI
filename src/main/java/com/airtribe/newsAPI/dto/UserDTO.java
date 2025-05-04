package com.airtribe.newsAPI.dto;

public class UserDTO {
    private String username;
    private String password;
    private String preferences;

    public UserDTO(String username, String password, String preferences) {
        this.username = username;
        this.password = password;
        this.preferences = preferences;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}
