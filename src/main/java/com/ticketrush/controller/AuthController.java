package com.ticketrush.controller;


import com.ticketrush.model.respone.AuthRespone;
import com.ticketrush.model.resquest.AuthenticationRequest;
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

    @PostMapping("/login")
    public ResponseEntity<AuthRespone> login(@RequestBody AuthenticationRequest request) {
        AuthRespone response = authService.authenticate(request);
        return ResponseEntity.ok(response);

    }
}
