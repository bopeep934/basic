package com.example.demo2.member.service;

import com.example.demo2.member.dto.MemberResponseDto;
import com.example.demo2.member.dto.MemberSaveRequestDto;
import com.example.demo2.member.dto.MemberSaveResponseDto;
import com.example.demo2.member.dto.MemberUpdateRequestDto;
import com.example.demo2.member.entity.Member;
import com.example.demo2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository ;

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll(){
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member-> new MemberResponseDto(member.getId(), member.getEmail())).toList();
    }

    @Transactional(readOnly=true)
    public MemberResponseDto findById(Long memberId){
        Member member  = memberRepository.findById(memberId).orElseThrow(()-> new IllegalStateException("해당 사용자는 존재하지 않습니다."));
        return new MemberResponseDto(
                member.getId(),
                member.getEmail()
        );
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto dto){
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new IllegalStateException("해당 사용자는 존재하지 않습니다"));
        member.update(dto.getEmail());
    }

    @Transactional
    public void deleteById(Long memberId){
        memberRepository.deleteById(memberId);
    }
}
