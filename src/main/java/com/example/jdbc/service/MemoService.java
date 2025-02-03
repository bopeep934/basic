package com.example.jdbc.service;


import com.example.jdbc.dto.MemoRequestDto;
import com.example.jdbc.dto.MemoResponseDto;
import com.example.jdbc.entity.Memo;
import com.example.jdbc.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    //save 메서드
    @Transactional
    public MemoResponseDto save(MemoRequestDto dto){

        Memo memo=new Memo(dto.getContent()); //저장 되기 전의 memo
        Memo savedMemo= memoRepository.save(memo); // 저장된 놈의 memo
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    //findAll 메소드
    @Transactional(readOnly=true)
    public List<MemoResponseDto> findAll(){

        List<Memo> memos= memoRepository.findAll();

        //리턴 타입을 맞추기 위한 dto 리스트 그릇
        List<MemoResponseDto> dtos=new ArrayList<>();
        for(Memo memo:memos){
            MemoResponseDto dto=new MemoResponseDto(memo.getId(), memo.getContent());
            dtos.add(dto);

        }
        return dtos;
    }

    @Transactional(readOnly=true)
    public MemoResponseDto findById(Long id){
        Memo memo= memoRepository.findById(id).orElseThrow(//있으면 memo, 없으면 error
                ()-> new IllegalArgumentException("해당 id가 존재하지 않습니다")
        );

        return new MemoResponseDto(memo.getId(), memo.getContent());

    }
    public MemoResponseDto update(Long id, MemoRequestDto dto){
        Memo updateMemo= memoRepository.updateContent(id, dto.getContent());
        return new MemoResponseDto(updateMemo.getId(), updateMemo.getContent());
    }
    public void deleteById(Long id){
        //삭제 전에 있나 없나 한 번 찾아보고 싶으면 findById 사용
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );

        memoRepository.deleteById(id);
    }
}
