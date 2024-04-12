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
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 게시글에 댓글 추가
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setBoard(this);
    }

    // 게시글의 댓글들 가져오기
    public List<Comment> getComments() {
        return comments;
    }
}
