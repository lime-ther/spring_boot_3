package com.dev.identity.service.mapper;

import com.dev.identity.service.dto.request.UserCreationRequest;
import com.dev.identity.service.dto.request.UserUpdateRequest;
import com.dev.identity.service.dto.response.UserResponse;
import com.dev.identity.service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
//    UserResponse toUserResponse(User user);
}
