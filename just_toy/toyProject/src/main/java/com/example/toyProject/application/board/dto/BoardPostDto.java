package com.example.toyProject.application.board.dto;

import com.example.toyProject.domain.board.Board;
import com.example.toyProject.domain.board.Tag;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
public final class BoardPostDto {
    private final String title;
    private final String content;
    private final List<String> tags;
    private final String topic;

    public BoardPostDto(String title, String content, List<String> tags, String topic) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.topic = topic;
    }

    public Board toEntity(LocalDateTime createdAt) {
        return Board.builder()
                .topic(topic)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .build();
    }
}
