package com.dev.identity.service.controller;

import com.dev.identity.service.dto.response.ApiResponse;
import com.dev.identity.service.dto.request.UserCreationRequest;
import com.dev.identity.service.dto.response.UserResponse;
import com.dev.identity.service.entity.User;
import com.dev.identity.service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/owner")
    ApiResponse<UserResponse> getOwner() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getOwner())
                .build();
    }
}
