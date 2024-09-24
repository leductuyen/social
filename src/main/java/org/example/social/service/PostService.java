package org.example.social.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.social.dto.request.PostCreateRequest;
import org.example.social.dto.request.PostUpdateRequest;
import org.example.social.dto.response.PostResponse;
import org.example.social.dto.response.UserResponse;
import org.example.social.entity.Post;
import org.example.social.entity.User;
import org.example.social.mapper.PostMapper;
import org.example.social.repository.PostRepository;
import org.example.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    PostMapper postMapper;

    public Post createPost (PostCreateRequest request) {
        Post post = postMapper.createPost(request);
        User user = userRepository.findById(request.getUserId()).orElse(null);
        post.setUser(user);
        return postRepository.save(post);
    }

    public List<PostResponse> getAllPosts() {
            return postRepository.findAll().stream()
                    .map(postMapper::postResponse).toList();

    }

    public PostResponse updatePost (String id, PostUpdateRequest request) {
        Post post = postRepository.findById(id).orElse(null);
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (post != null && user != null) {
            postMapper.updatePost(post, request);
            postRepository.save(post);
            return postMapper.postResponse(post);
        } else {
            return null;
        }

    }

    public PostResponse deletePost(String postId, String userId) {

        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return null;
        }

        if (!post.getUser().getId().equals(userId)) {
            return null;
        }

        postRepository.delete(post);

        return postMapper.postResponse(post);
    }

}
