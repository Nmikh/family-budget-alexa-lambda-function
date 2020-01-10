package com.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthoriseModel {
    private String userId;
    private String userOTP;
}
