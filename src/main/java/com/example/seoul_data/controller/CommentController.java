package com.example.seoul_data.controller;

import com.example.seoul_data.common.ApiResponseDto;
import com.example.seoul_data.common.SuccessResponseDto;
import com.example.seoul_data.dto.CommentRequestDto;
import com.example.seoul_data.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.seoul_data.model.User;
import com.example.seoul_data.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ApiResponseDto<CommentResponseDto> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @RequestHeader("Authorization") String authorizationHeader) {
        // 여기서 authorizationHeader를 파싱하여 사용자 정보를 추출하고, 이를 CommentService로 전달합니다.
        User user = parseUserFromAuthorizationHeader(authorizationHeader);
        return commentService.createComment(postId, requestDto, user);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ApiResponseDto<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @RequestHeader("Authorization") String authorizationHeader) {
        User user = parseUserFromAuthorizationHeader(authorizationHeader);
        return commentService.updateComment(commentId, requestDto, user);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ApiResponseDto<SuccessResponseDto> deleteComment(@PathVariable Long commentId, @RequestHeader("Authorization") String authorizationHeader) {
        User user = parseUserFromAuthorizationHeader(authorizationHeader);
        return commentService.deleteComment(commentId, user);
    }

    // Authorization 헤더로부터 사용자 정보를 파싱하는 메서드
    private User parseUserFromAuthorizationHeader(String authorizationHeader) {
        // Assuming the Authorization header follows the format "Bearer {JWT_TOKEN}"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring("Bearer ".length());

            // Verify and decode the JWT token to extract user information
            // This might involve using a JWT library or implementing custom JWT validation logic
            // For example, you could use a JWT library like JJWT to decode the token
            // User = parseJwtToken(jwtToken);

            // Return the extracted user information
            return user;
        }

        // Return null if the Authorization header is missing or invalid
        return null;
    }

}
