package de.artsem.springcourse.Project2Boot.dto;

import jakarta.validation.constraints.NotEmpty;

public class AuthenticationDTO {

    @NotEmpty(message = "Name should not be empty")
    private String username;

    private String password;

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
}
