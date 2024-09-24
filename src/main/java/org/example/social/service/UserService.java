package org.example.social.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.social.dto.request.UserCreateRequest;
import org.example.social.dto.request.UserUpdateRequest;
import org.example.social.dto.response.UserResponse;
import org.example.social.entity.User;
import org.example.social.mapper.UserMapper;
import org.example.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreateRequest request) {

        User user = userMapper.createUser(request);
        return userRepository.save(user);
    }

    public UserResponse getUserById(String id) {
        UserResponse userResponse = userMapper.userToUserResponse(userRepository.findById(id).orElseThrow(null));;
        return userResponse;

    }

    public List<UserResponse> getAllUser () {

        return userRepository.findAll().stream()
                .map(userMapper::userToUserResponse).toList();
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {

        User user = userRepository.findById(id).orElseThrow(null);
        if(user == null) {
            return null;
        }
        userMapper.updateUser(user, request);
        userRepository.save(user);
        return userMapper.userToUserResponse(user);
    }
}
