package com.example.seoul_data.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Getter
@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;


    @Builder
    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User of(String username, String password) {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }

}