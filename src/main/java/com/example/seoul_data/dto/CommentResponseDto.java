// CommentResponseDto
package com.example.seouldata.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String content;
    private Long postId;

    public CommentResponseDto(Long id, String content, Long postId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
    }

    // 게터, 세터, 생성자 등 필요한 메서드 추가
}
