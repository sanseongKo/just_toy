package com.example.toyProject.application.board.dto;

import com.example.toyProject.domain.board.Board;
import com.example.toyProject.domain.board.Tag;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public record BoardPostDto(String title, String content, List<Tag> tags, String topic) {
    public Board toEntity(LocalDateTime createdAt) {
        return Board.builder()
                .topic(topic)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .build();
    }
}
