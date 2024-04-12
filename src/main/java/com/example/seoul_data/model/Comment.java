package com.example.seoul_data.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Comment comment;
    private Board board;

    public Comment(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 댓글의 소유자인 게시글 설정
    public void setPost(Comment comment) {
        this.comment = comment;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
