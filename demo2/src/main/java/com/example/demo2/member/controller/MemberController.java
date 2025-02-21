package com.example.demo2.member.controller;

import com.example.demo2.common.consts.Const;
import com.example.demo2.member.dto.MemberResponseDto;
import com.example.demo2.member.dto.MemberSaveRequestDto;
import com.example.demo2.member.dto.MemberSaveResponseDto;
import com.example.demo2.member.dto.MemberUpdateRequestDto;
import com.example.demo2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/memberes/{memberId}")
    public ResponseEntity<MemberResponseDto> getOne(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findById(memberId));
    }

    @PutMapping("/members")
    public void update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemberUpdateRequestDto dto
    ) {
        memberService.update(memberId, dto);
    }

    @DeleteMapping("/members")
    public void delete(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId
    ) {
        memberService.deleteById(memberId);
    }
}
