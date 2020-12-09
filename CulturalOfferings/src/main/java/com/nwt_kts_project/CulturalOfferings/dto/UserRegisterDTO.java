package com.nwt_kts_project.CulturalOfferings.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRegisterDTO {

    private Long id;


    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Email format is not valid.")
    private String email;



    @NotBlank(message = "Username cannot be empty.")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(Long id, @NotBlank(message = "Email cannot be empty.") @Email(message = "Email format is not valid.") String email,
                   @NotBlank(message = "Username cannot be empty.")String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public UserRegisterDTO(Long id, @NotBlank(message = "Email cannot be empty.") @Email(message = "Email format is not valid.") String email,
                           @NotBlank(message = "Username cannot be empty.")String username, @NotBlank(message = "Password cannot be empty") String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
