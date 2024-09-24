package org.example.social.mapper;

import org.example.social.dto.request.PostCreateRequest;
import org.example.social.dto.request.PostUpdateRequest;
import org.example.social.dto.response.PostResponse;
import org.example.social.dto.response.UserResponse;
import org.example.social.entity.Post;
import org.example.social.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post createPost (PostCreateRequest request);

    @Mapping(source = "user", target = "user")
    PostResponse postResponse(Post post);

    @Mapping(target = "id", ignore = true)
    void updatePost(@MappingTarget Post post, PostUpdateRequest request);

}

