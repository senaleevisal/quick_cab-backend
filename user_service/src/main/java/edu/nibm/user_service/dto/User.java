package edu.nibm.user_service.dto;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;

}