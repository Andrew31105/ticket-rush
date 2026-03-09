package com.ticketrush.service;

import com.ticketrush.model.respone.AuthRespone;
import com.ticketrush.model.resquest.AuthenticationRequest;
import com.ticketrush.model.resquest.RegisterRequest;

public interface IAuthService {
    AuthRespone authenticate(AuthenticationRequest authRequest);
    void register(RegisterRequest registerRequest);
}
