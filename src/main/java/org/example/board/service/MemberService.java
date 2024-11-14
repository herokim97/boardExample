package org.example.board.service;


import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.example.board.dto.MemberResponseDto;
import org.example.board.dto.SignupResponseDto;
import org.example.board.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.example.board.Entity.Member;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


//확장 및 변경의 소지가 있다면 Interface로 선언하여 구현.
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignupResponseDto signUp(String username, String password, Integer age) {

        Member member = new Member(username, password, age);

        Member saveMember = memberRepository.save(member);

        return new SignupResponseDto(saveMember.getId(), saveMember.getUserName(), saveMember.getAge());
    }

    public MemberResponseDto findById(Long id) {

        Optional<Member> optionalMember = memberRepository.findById(id);

        if(optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Not exist id" + id);
        }

        Member findMember = optionalMember.get();

        return new MemberResponseDto(findMember.getUserName(), findMember.getAge());

    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if(!findMember.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호");
        }

        findMember.updatePassword(newPassword);

    }
}
