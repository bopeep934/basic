package com.example.Member.service;

import com.example.Member.dto.MemberRequestDto;
import com.example.Member.dto.MemberResponseDto;
import com.example.Member.entity.Member;
import com.example.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(MemberRequestDto dto){
        Member member =new Member(dto.getName());
        Member savedMember =memberRepository.save(member);
        return new MemberResponseDto(savedMember.getId(), savedMember.getName());
    }

    @Transactional(readOnly=true)
    public List<MemberResponseDto> findAll(){
        List<Member> members= memberRepository.findAll();

        List<MemberResponseDto> dtos =new ArrayList<>();
        for (Member member : members){
            dtos.add( new MemberResponseDto(member.getId(),member.getName()));
        }

        return dtos;
    }
    @Transactional(readOnly=true)
    public MemberResponseDto findById(Long id){
        Member member=memberRepository.findById(id).orElseThrow(//없으면 에러 있으면 넘어감
                ()->new IllegalArgumentException("그 id 없어")
        );
        return new MemberResponseDto(member.getId(), member.getName());
    }
    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto){
        Member member=memberRepository.findById(id).orElseThrow(//없으면 에러 있으면 넘어감
                ()->new IllegalArgumentException("id 없음")
        );
        //jpa는 여기서 이미 업데이트가  모두 됨.
  //      Component member;
       member.update(dto.getName());//영속성 컨텍스트
        return new MemberResponseDto(member.getId(), member.getName());

    }

    @Transactional
    public void deleteById(Long id){

                if(!memberRepository.existsById(id)){
                    throw new IllegalArgumentException("id 없어서 삭제 안됨");
                }
                memberRepository.deleteById(id);
    }



}
