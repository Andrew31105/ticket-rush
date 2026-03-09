package com.ticketrush.model.resquest;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    // Mặc định register sẽ là ROLE_USER, admin tạo bằng cách khác
}

