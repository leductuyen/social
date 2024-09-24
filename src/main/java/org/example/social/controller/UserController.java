package org.example.social.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.social.dto.request.UserCreateRequest;
import org.example.social.dto.request.UserUpdateRequest;
import org.example.social.dto.response.ApiResponse;
import org.example.social.dto.response.UserResponse;
import org.example.social.entity.User;
import org.example.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
     ApiResponse<User> createUser(@RequestBody UserCreateRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Success");
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping("/{id}")
     ApiResponse<UserResponse> getUser(@PathVariable("id") String id) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Success");
        apiResponse.setResult(userService.getUserById(id));
        return apiResponse;
    }

    @GetMapping()
    ApiResponse<List<UserResponse>> getUserAllUser() {
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Success");
        apiResponse.setResult(userService.getAllUser());
        return apiResponse;
    }

    @PutMapping("/{id}")
    ApiResponse<UserResponse> updateUser(@PathVariable("id") String id, @RequestBody UserUpdateRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Success");
        apiResponse.setResult(userService.updateUser(id, request));
        return apiResponse;
    }


}
