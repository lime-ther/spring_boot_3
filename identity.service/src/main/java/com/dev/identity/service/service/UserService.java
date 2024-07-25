package com.dev.identity.service.service;

import com.dev.identity.service.dto.request.UserCreationRequest;
import com.dev.identity.service.dto.request.UserUpdateRequest;
import com.dev.identity.service.dto.response.UserResponse;
import com.dev.identity.service.entity.Role;
import com.dev.identity.service.entity.User;
import com.dev.identity.service.exception.AppException;
import com.dev.identity.service.exception.ErrorCode;
import com.dev.identity.service.mapper.UserMapper;
import com.dev.identity.service.repository.RoleRepository;
import com.dev.identity.service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
//        roles.add(Role.USER.name());

//        user.setRoles(roles);

//        return userMapper.toUserResponse(userRepository.save(user));
        return null;
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<UserResponse> getUsers() {
//        return userRepository.findAll().stream()
//                .map(userMapper::toUserResponse).toList();
        return new ArrayList<>();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String userId) {
//        return userMapper.toUserResponse(userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found.")));
        return null;
    }

    public UserResponse getOwner() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );

//        return userMapper.toUserResponse(user);
        return null;
    }
}
