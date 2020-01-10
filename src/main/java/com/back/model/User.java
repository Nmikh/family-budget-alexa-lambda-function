package com.back.model;

import lombok.Builder;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
