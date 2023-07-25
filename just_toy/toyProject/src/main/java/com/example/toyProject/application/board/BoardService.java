package com.example.toyProject.application.board;

import com.example.toyProject.application.board.dto.BoardPostDto;
import com.example.toyProject.domain.board.Board;
import com.example.toyProject.domain.board.BoardTagMapper;
import com.example.toyProject.domain.board.Tag;
import com.example.toyProject.repository.board.BoardRepository;
import com.example.toyProject.repository.board.BoardTagMapperRepository;
import com.example.toyProject.repository.board.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class BoardService {

    private BoardRepository boardRepository;
    private BoardTagMapperRepository boardTagMapperRepository;
    private TagRepository tagRepository;
    @Transactional
    public Board postBoard(BoardPostDto dto) {
        List<BoardTagMapper> boardTagMappers = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();

        Board board = dto.toEntity(now);

        Board boardId = boardRepository.save(board);

        if(!dto.getTags().isEmpty()) {
            List<String> tagNames = dto.getTags();
            List<Tag> tags = tagRepository.findAllByTagNameIn(tagNames);

            tags.forEach( tag ->
                boardTagMappers.add(
                        BoardTagMapper.builder()
                                .createdAt(now).tag(tag).board(board)
                                .build())
            );

            boardTagMapperRepository.saveAll(boardTagMappers);
        }

        return board;
    }
}
