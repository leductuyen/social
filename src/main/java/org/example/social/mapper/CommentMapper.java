package org.example.social.mapper;

import org.example.social.dto.request.CommentCreateRequest;
import org.example.social.dto.request.CommentUpdateRequest;
import org.example.social.dto.response.CommentResponse;
import org.example.social.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment createComment (CommentCreateRequest request);

    @Mapping(source = "post", target = "post")
    CommentResponse commentResponse (Comment request);

    @Mapping(target = "id", ignore = true)
    void updateComment (@MappingTarget Comment comment, CommentUpdateRequest request);
}
