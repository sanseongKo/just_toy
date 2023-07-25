package com.example.toyProject.domain.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    private String topic;

    private String title;

    @Lob
    private String content;

    @OneToMany
    private List<BoardTagMapper> boardTagMappers;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
