package com.ticketrush.service;

import com.ticketrush.model.respone.AuthRespone;
import com.ticketrush.model.resquest.AuthenticationRequest;

public interface IAuthService {
     public AuthRespone authRespone(AuthenticationRequest authRequest);
}
