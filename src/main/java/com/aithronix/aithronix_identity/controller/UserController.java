package com.aithronix.aithronix_identity.controller;

import com.aithronix.aithronix_identity.dto.request.UserLoginRequest;
import com.aithronix.aithronix_identity.dto.request.UserRegisterRequest;
import com.aithronix.aithronix_identity.dto.response.UserLoginResponse;
import com.aithronix.aithronix_identity.dto.response.UserRegisterResponse;
import com.aithronix.aithronix_identity.mapper.UserMapper;
import com.aithronix.aithronix_identity.model.User;
import com.aithronix.aithronix_identity.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "User management endpoints")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request) {
        User user = userService.register(request);
        UserRegisterResponse response = userMapper.toUserRegisterResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Login user and get JWT token")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        var response = userService.login(request);
        return ResponseEntity.ok(response);
    }

}
