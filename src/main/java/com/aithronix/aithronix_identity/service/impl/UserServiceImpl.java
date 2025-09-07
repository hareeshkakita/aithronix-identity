package com.aithronix.aithronix_identity.service.impl;

import com.aithronix.aithronix_identity.dto.request.UserLoginRequest;
import com.aithronix.aithronix_identity.dto.request.UserRegisterRequest;
import com.aithronix.aithronix_identity.dto.response.UserLoginResponse;
import com.aithronix.aithronix_identity.exception.InvalidCredentialsException;
import com.aithronix.aithronix_identity.exception.UserAlreadyExistsException;
import com.aithronix.aithronix_identity.mapper.UserMapper;
import com.aithronix.aithronix_identity.model.User;
import com.aithronix.aithronix_identity.repository.UserRepository;
import com.aithronix.aithronix_identity.service.UserService;
import com.aithronix.aithronix_identity.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Override
    public User register(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("Email is already in use");
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail()); // replace with real JWT later

        return new UserLoginResponse(user.getId(), user.getName(), user.getEmail(), token);

    }
}
