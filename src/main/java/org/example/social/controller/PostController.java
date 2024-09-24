package org.example.social.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.social.dto.request.PostCreateRequest;
import org.example.social.dto.request.PostUpdateRequest;
import org.example.social.dto.response.ApiResponse;
import org.example.social.dto.response.PostResponse;
import org.example.social.entity.Post;
import org.example.social.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @PostMapping
    ApiResponse<Post> createPost (@RequestBody PostCreateRequest request) {
        ApiResponse<Post> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Success");
        apiResponse.setResult(postService.createPost(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<PostResponse>> getAllPosts () {
        ApiResponse<List<PostResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Success");
        apiResponse.setResult(postService.getAllPosts());
        return apiResponse;
    }

    @PutMapping("/{id}")
    ApiResponse<PostResponse> updatePost (@PathVariable("id") String id, @RequestBody PostUpdateRequest request) {
        ApiResponse<PostResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Success");
        apiResponse.setResult(postService.updatePost(id, request));
        return apiResponse;
    }

    @DeleteMapping("/{postId}")
    ApiResponse<PostResponse> deletePost (@PathVariable("postId") String id, @RequestParam String userId) {
        ApiResponse<PostResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Success");
        apiResponse.setResult(postService.deletePost(id, userId));
        return apiResponse;
    }
}
