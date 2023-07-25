package com.example.toyProject.domain.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
public class BoardTagMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "board_tag_mapper_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BoardTagMapper() {}
}
