// CommentRequestDto
package com.example.seoul_data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    private String content;
    Long parentCommentId;

    // 게터, 세터, 생성자 등 필요한 메서드 추가
}
