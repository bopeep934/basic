package com.example.Member.controller;

import com.example.Member.dto.MemberRequestDto;
import com.example.Member.dto.MemberResponseDto;
import com.example.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//내장에 controller있음
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberResponseDto> save(@RequestBody MemberRequestDto RequestDto) {
        return ResponseEntity.ok(memberService.save(RequestDto));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> update(
            @PathVariable Long id,
            @RequestBody MemberRequestDto dto
    ) {
        return ResponseEntity.ok(memberService.update(id, dto));

    }

    @DeleteMapping("/members/{id}")
    public void deleteById(@PathVariable Long id) {
        memberService.deleteById(id);

    }


}
