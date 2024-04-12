package com.example.seoul_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.seoul_data.model.Board;
import com.example.seoul_data.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();
    Optional<Board> findByIdAndUser(Long id, User user);

    void deleteAllByUser(User user);
}
