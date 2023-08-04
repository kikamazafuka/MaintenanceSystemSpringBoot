package de.artsem.springcourse.Project2Boot.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public class UserAccountDTO {

    @NotEmpty(message = "Name should not be empty")
    private String userAccountName;

    private  String password;

    public String getUserAccountName() {
        return userAccountName;
    }

    public void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
