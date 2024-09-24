package org.example.social.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.social.dto.request.CommentCreateRequest;
import org.example.social.dto.request.CommentUpdateRequest;
import org.example.social.dto.response.CommentResponse;
import org.example.social.entity.Comment;
import org.example.social.entity.Post;
import org.example.social.entity.User;
import org.example.social.mapper.CommentMapper;
import org.example.social.repository.CommentRepository;
import org.example.social.repository.PostRepository;
import org.example.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    UserRepository userRepository;
    CommentMapper commentMapper;


    public CommentResponse createComment(CommentCreateRequest request) {
        Comment comment = commentMapper.createComment(request);
        Post post = postRepository.findById(request.getPostId()).orElse(null);
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (post != null && user != null) {
            comment.setPost(post);
            comment.setUser(user);
            comment = commentRepository.save(comment);
            return commentMapper.commentResponse(comment);
        } else {
            return null;
        }

    }

    public List<CommentResponse> getAllComments() {

        return commentRepository.findAll().stream()
                .map(commentMapper::commentResponse).toList();
    }

    public CommentResponse updateComment (String commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        User user = userRepository.findById(request.getUserId()).orElse(null);
        Post post = postRepository.findById(request.getPostId()).orElse(null);
        if (comment != null && user != null && post != null) {
            commentMapper.updateComment(comment, request);
            comment.setUser(user);
            comment.setPost(post);

            comment = commentRepository.save(comment);
            return commentMapper.commentResponse(comment);
        } else {
            return null;
        }
    }

    public void deleteComment (String commentId, String postId, String userId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Post post = postRepository.findById(postId).orElse(null);
        if (comment != null && user != null && post != null) {
            commentRepository.delete(comment);
        } else {
            return;
        }
    }
}
