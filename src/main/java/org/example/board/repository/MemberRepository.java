package org.example.board.repository;

import org.example.board.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional <Member> findMemberByUserName(String username);

    default Member findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    default Member findMemberByUsernameOrElseThrow(String username) {
        return findMemberByUserName(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

}
