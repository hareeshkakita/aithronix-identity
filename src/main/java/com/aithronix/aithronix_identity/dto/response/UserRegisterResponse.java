package com.aithronix.aithronix_identity.dto.response;

import java.time.LocalDateTime;

public record UserRegisterResponse(Long id, String name, String email, LocalDateTime createdAt) {
}
