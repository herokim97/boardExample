package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.Entity.Board;
import org.example.board.Entity.Member;
import org.example.board.dto.BoardResponseDto;
import org.example.board.dto.BoardWithAgeResponseDto;
import org.example.board.repository.BoardRepository;
import org.example.board.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public BoardResponseDto save(String title, String contents, String username) {

        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);

        Board board = new Board(title, contents);
        board.setMember(findMember);

        Board savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard.getId(), savedBoard.getTitle(), savedBoard.getContent());
    }

    public List<BoardResponseDto> findAll() {

        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::toDto)
                .toList();
    }

    public BoardWithAgeResponseDto findById(Long id) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        Member writer = findBoard.getMember();

        return new BoardWithAgeResponseDto(findBoard.getTitle(), findBoard.getContent(), writer.getAge());
    }

    public void delete(Long id) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        boardRepository.delete(findBoard);
    }
}
