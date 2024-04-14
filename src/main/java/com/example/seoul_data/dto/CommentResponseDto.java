package com.example.seoul_data.dto;

import com.example.seoul_data.model.Comment;
import com.example.seoul_data.model.User; // 사용자 클래스 import
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long postId;
    private String username;
    private List<CommentResponseDto> childCommentList;

    @Builder
    private CommentResponseDto(Comment entity, User user) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.postId = entity.getBoard().getId();
        this.username = user.getUsername(); // getUser() 대신에 사용자 객체로부터 유저네임 가져오기
        // getChildCommentList 메서드에 대한 대체 방법이 필요합니다.
        // 만약 Comment 클래스에 getChildCommentList 메서드가 없다면, 적절한 대체 방법을 고려해야 합니다.
    }

    public static CommentResponseDto from(Comment entity, User user) {
        return CommentResponseDto.builder()
                .entity(entity)
                .user(user) // 사용자 객체 전달
                .build();
    }

    // 게터, 세터, 생성자 등 필요한 메서드 추가
}
