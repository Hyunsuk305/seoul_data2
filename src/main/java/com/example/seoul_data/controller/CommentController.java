package com.example.seoul_data.controller;

import com.example.seoul_data.common.ApiResponseDto;
import com.example.seoul_data.common.SuccessResponseDto;
import com.example.seouldata.dto.CommentRequestDto;
import com.example.seouldata.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.seoul_data.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ApiResponseDto<CommentResponseDto> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(postId, requestDto);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ApiResponseDto<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(commentId, requestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ApiResponseDto<SuccessResponseDto> deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

}
