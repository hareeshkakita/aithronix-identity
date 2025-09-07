package com.aithronix.aithronix_identity.service;

import com.aithronix.aithronix_identity.dto.request.UserLoginRequest;
import com.aithronix.aithronix_identity.dto.request.UserRegisterRequest;
import com.aithronix.aithronix_identity.dto.response.UserLoginResponse;
import com.aithronix.aithronix_identity.model.User;

public interface UserService {

     User register(UserRegisterRequest request);
     UserLoginResponse login(UserLoginRequest request) ;

}
