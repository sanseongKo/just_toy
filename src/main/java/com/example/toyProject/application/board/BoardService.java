package com.example.toyProject.application.board;

import com.example.toyProject.application.board.dto.BoardPostDto;
import com.example.toyProject.domain.board.Board;
import com.example.toyProject.domain.board.BoardTagMapper;
import com.example.toyProject.repository.board.BoardRepository;
import com.example.toyProject.repository.board.BoardTagMapperRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private BoardTagMapperRepository boardTagMapperRepository;

    @Transactional
    public Long postBoard(BoardPostDto dto) {
        List<BoardTagMapper> boardTagMappers = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();

        Board board = dto.toEntity(now);

        Long boardId = boardRepository.save(board).getId();

        if(!dto.tags().isEmpty()) {
            dto.tags().forEach( tag ->
                boardTagMappers.add(
                        BoardTagMapper.builder()
                                .createdAt(now).tag(tag).board(board)
                                .build())
            );
        }

        boardTagMapperRepository.saveAll(boardTagMappers);

        return boardId;
    }
}
