package com.example.seoul_data.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.seoul_data.model.Board;
import com.example.seoul_data.dto.BoardRequestDto;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/posts")
public class BoardController {

    private final List<Board> boards = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(BoardController.class, args);
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody BoardRequestDto boardRequestDto) {
        Board board = new Board(boardRequestDto.getTitle(), boardRequestDto.getContent());
        boards.add(board);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Board>> getAllPosts() {
        return ResponseEntity.ok(boards);
    }

    // 모델 클래스

}
