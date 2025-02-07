package com.example.Memo.dto;

import lombok.Getter;

@Getter
public class MemoResponseDto {

    Long id;
    String content;

    public MemoResponseDto(Long id, String content){
        this.id=id;
        this.content=content;
    }
}
