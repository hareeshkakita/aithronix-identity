package com.aithronix.aithronix_identity.mapper;

import com.aithronix.aithronix_identity.dto.request.UserRegisterRequest;
import com.aithronix.aithronix_identity.dto.response.UserRegisterResponse;
import com.aithronix.aithronix_identity.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toUser(UserRegisterRequest request);

    UserRegisterResponse toUserRegisterResponse(User user);

}
