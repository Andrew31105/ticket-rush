package com.ticketrush.model.resquest;

import lombok.Data;

@Data
public class AuthenticationRequest {
    // BUG CŨ: tên biến viết hoa đầu (Username, Password) → getter/setter bị lệch convention
    // FIX: lowercase, dùng Lombok @Data để tự generate getter/setter chuẩn
    private String username;
    private String password;

}
