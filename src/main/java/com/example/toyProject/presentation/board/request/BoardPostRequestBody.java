package com.example.toyProject.presentation.board.request;

import com.example.toyProject.application.board.dto.BoardPostDto;
import com.example.toyProject.domain.board.Tag;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BoardPostRequestBody {

    private final String title;

    private final String content;

    @Nullable
    private final List<Tag> tags;

    private final String topic;

    public BoardPostDto toDto() {
        return new BoardPostDto(title, content, tags, topic);
    }
}
