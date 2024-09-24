package org.example.social.mapper;

import org.example.social.dto.request.UserCreateRequest;
import org.example.social.dto.request.UserUpdateRequest;
import org.example.social.dto.response.UserResponse;
import org.example.social.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createUser (UserCreateRequest request);

    UserResponse userToUserResponse (User user);


    @Mapping(target = "id", ignore = true)
    void updateUser (@MappingTarget User user, UserUpdateRequest request);
}
