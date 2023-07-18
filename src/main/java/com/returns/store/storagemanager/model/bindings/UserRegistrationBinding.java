package com.returns.store.storagemanager.model.bindings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegistrationBinding {
    @NotNull
    @Size(min = 2, max = 30,message = "The length of the username must be between 2 and 30 symbols.")
    private String username;

    @NotNull
    @Pattern(regexp = "[A-z].+[@][A-z]+[.][A-z+]+", message = "The email must contains @!")
    private String email;

    @NotNull
    @Size(min = 3, message = "The length of the password must be at least 3 symbols.")
    private String password;

    @NotBlank
    @Size(min = 3, message = "The length of the confirm password must be at least 3 symbols.")
    private String confirmPassword;

    public UserRegistrationBinding(){
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationBinding setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationBinding setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBinding setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBinding setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
