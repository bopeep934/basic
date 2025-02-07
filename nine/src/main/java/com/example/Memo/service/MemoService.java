package com.example.Memo.service;

import com.example.Memo.dto.MemoRequestDto;
import com.example.Memo.dto.MemoResponseDto;
import com.example.Memo.entity.Memo;
import com.example.Memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getContent());
        Memo savedMemo = memoRepository.save(memo);
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();
        List<MemoResponseDto> dtos = new ArrayList<>();

        for (Memo memo : memos) {
            dtos.add(new MemoResponseDto(memo.getId(), memo.getContent()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다.")
        );

        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto dto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다.")
        );
        memo.update(dto.getContent());
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public void deleteById(Long id) {
        if(!memoRepository.existsById(id)){
            throw new IllegalArgumentException("삭제할 메모가 없습니다.");
        }
        memoRepository.deleteById(id);
    }
    }
