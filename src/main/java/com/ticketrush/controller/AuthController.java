package com.ticketrush.controller;


import com.ticketrush.model.respone.AuthRespone;
import com.ticketrush.model.resquest.AuthenticationRequest;
import com.ticketrush.model.resquest.RegisterRequest;
import com.ticketrush.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * POST /api/auth/register
     * Body: { "username": "alice", "password": "123456" }
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Đăng ký thành công");
    }

    /**
     * POST /api/auth/login
     * Body: { "username": "alice", "password": "123456" }
     * Response: { "access_token": "...", "roles": ["ROLE_USER"] }
     */
    @PostMapping("/login")
    public ResponseEntity<AuthRespone> login(@RequestBody AuthenticationRequest request) {
        AuthRespone response = authService.authenticate(request);
        return ResponseEntity.ok(response);

    }
}
