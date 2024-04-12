package com.example.seoul_data.service;

import com.example.seoul_data.common.ApiResponseDto;
import com.example.seoul_data.common.SuccessResponseDto;
import com.example.seoul_data.common.ResponseUtil;
import com.example.seouldata.dto.CommentRequestDto;
import com.example.seouldata.dto.CommentResponseDto;
import com.example.seoul_data.exception.ResourceNotFoundException;
import com.example.seoul_data.model.Board;
import com.example.seoul_data.model.Comment;
import com.example.seoul_data.model.User;
import com.example.seoul_data.repository.BoardRepository;
import com.example.seoul_data.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 작성
    @Transactional
    public ApiResponseDto<CommentResponseDto> createComment(Long id, CommentRequestDto requestDto, User user) {

        // 선택한 게시글 DB 조회
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new ResourceNotFoundException("게시글을 찾을 수 없습니다."); // Use ResourceNotFoundException
        }

        Long parentCommentId = requestDto.getParentCommentId();
        Comment comment = Comment.of(requestDto, board.get(), user);
        if (parentCommentId == null) {  // parentComment 가 없다면
            commentRepository.save(comment);    // 바로 저장
            return ResponseUtil.ok(CommentResponseDto.from(comment));
        }
        // parentComment 가 있다면 parent comment 에 childComment 를 추가
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new ResourceNotFoundException("댓글을 찾을 수 없습니다.")); // Use ResourceNotFoundException
        parentComment.addChildComment(comment); // parentComment 에 childComment 추가
        commentRepository.save(comment);

        return ResponseUtil.ok(CommentResponseDto.from(comment));

    }

    // 댓글 수정
    @Transactional
    public ApiResponseDto<CommentResponseDto> updateComment(Long id, CommentRequestDto requestDto, User user) {

        // 선택한 댓글이 DB에 있는지 확인
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new ResourceNotFoundException("댓글을 찾을 수 없습니다."); // Use ResourceNotFoundException
        }

        // 댓글의 작성자와 수정하려는 사용자의 정보가 일치하는지 확인 (수정하려는 사용자가 관리자라면 댓글 수정 가능)
        Optional<Comment> found = commentRepository.findByIdAndUser(id, user);
        if (found.isEmpty() && user.getRole() == UserRoleEnum.USER) {
            throw new ResourceNotFoundException("댓글 작성자만 수정 가능합니다."); // Use ResourceNotFoundException
        }

        // 관리자이거나, 댓글의 작성자와 수정하려는 사용자의 정보가 일치한다면, 댓글 수정
        comment.get().update(requestDto, user);
        commentRepository.flush();   // responseDto 에 modifiedAt 업데이트 해주기 위해 saveAndFlush 사용

        // ResponseEntity 에 dto 담아서 리턴
        return ResponseUtil.ok(CommentResponseDto.from(comment.get()));

    }

    // 댓글 삭제
    @Transactional
    public ApiResponseDto<SuccessResponseDto> deleteComment(Long id, User user) {

        // 선택한 댓글이 DB에 있는지 확인
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new ResourceNotFoundException("댓글을 찾을 수 없습니다."); // Use ResourceNotFoundException
        }

        // 댓글의 작성자와 삭제하려는 사용자의 정보가 일치하는지 확인 (삭제하려는 사용자가 관리자라면 댓글 삭제 가능)
        Optional<Comment> found = commentRepository.findByIdAndUser(id, user);
        if (found.isEmpty() && user.getRole() == UserRoleEnum.USER) {
            throw new ResourceNotFoundException("댓글 작성자만 삭제 가능합니다.");
        }
    }
}
