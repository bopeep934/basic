package com.example.jdbc.controller;


import com.example.jdbc.dto.MemoRequestDto;
import com.example.jdbc.dto.MemoResponseDto;
import com.example.jdbc.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    //create API
    @PostMapping("/memos")
    public MemoResponseDto save(@RequestBody MemoRequestDto dto){
        return memoService.save(dto);
    }

    //Read All API
    @GetMapping("/memos")
    public List<MemoResponseDto> findAll(){
        return memoService.findAll();
    }

    //Read One API
    @GetMapping("/memos/{memoId}")
    public MemoResponseDto findById(@PathVariable Long memoId){
        return memoService.findById(memoId);
    }

    //update API
    @PutMapping("/memos/{memoId}")
    public MemoResponseDto update(
            @PathVariable Long memoId,
            @RequestBody MemoRequestDto dto

    ){
        return memoService.update(memoId,dto);
    }

    //Delete API
    @DeleteMapping("/memos/{memoId}")
    public void delete(@PathVariable Long id) {
        memoService.deleteById(id);
    }

}
