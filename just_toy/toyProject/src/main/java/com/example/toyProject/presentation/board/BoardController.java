package com.example.toyProject.presentation.board;

import com.example.toyProject.application.board.BoardService;
import com.example.toyProject.application.board.dto.BoardPostDto;
import com.example.toyProject.presentation.board.request.BoardPostRequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {
    private BoardService boardService;

    @PostMapping
    public Long postBoard(BoardPostRequestBody requestBody) {
        BoardPostDto dto = requestBody.toDto();

        return boardService.postBoard(dto).getId();
    }
}
