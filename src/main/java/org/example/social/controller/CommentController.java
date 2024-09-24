package org.example.social.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.social.dto.request.CommentCreateRequest;
import org.example.social.dto.request.CommentUpdateRequest;
import org.example.social.dto.response.ApiResponse;
import org.example.social.dto.response.CommentResponse;
import org.example.social.entity.Comment;
import org.example.social.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/comments")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CommentController {
    CommentService commentService;

    @PostMapping
    ApiResponse<CommentResponse> createComment (@RequestBody CommentCreateRequest request) {
        ApiResponse<CommentResponse> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Success");
        apiResponse.setCode(200);
        apiResponse.setResult(commentService.createComment(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<CommentResponse>> getAllComments() {
        ApiResponse<List<CommentResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Success");
        apiResponse.setCode(200);
        apiResponse.setResult(commentService.getAllComments());
        return apiResponse;
    }

    @PutMapping("/{commentId}")
    ApiResponse<CommentResponse> updateComment (@PathVariable("commentId") String commentId, @RequestBody CommentUpdateRequest request) {
        ApiResponse<CommentResponse> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Success");
        apiResponse.setCode(200);
        apiResponse.setResult(commentService.updateComment(commentId, request));
        return apiResponse;
    }

    @DeleteMapping("/{commentId}")
    ApiResponse<Void> deleteComment(@PathVariable("commentId") String commentId,
                                    @RequestParam String userId,
                                    @RequestParam String postId) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        try {
            commentService.deleteComment(commentId, userId, postId);
            apiResponse.setMessage("Success");
            apiResponse.setCode(200);
        } catch (Exception e) {
            apiResponse.setMessage("Error: " + e.getMessage());
            apiResponse.setCode(500);
        }
        return apiResponse;
    }

}
