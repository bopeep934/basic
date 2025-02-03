package com.example.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Memo {

    @Setter
    private Long id;// 자동 생성 id
    private String content;

    //id 필드를 제외한 나머지 필드를 생성자에 넣기
    public Memo(String content){
        this.content=content;
    }
}
