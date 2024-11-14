package org.example.board.Controller;

import lombok.RequiredArgsConstructor;
import org.example.board.dto.MemberResponseDto;
import org.example.board.dto.SignupRequestDto;
import org.example.board.dto.SignupResponseDto;
import org.example.board.dto.UpdatePasswordRequestDto;
import org.example.board.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody SignupRequestDto requestDto) {

        SignupResponseDto signupResponseDto = memberService.signUp(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getAge()
        );

        return new ResponseEntity<>(signupResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ){

        memberService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
