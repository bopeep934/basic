package com.example.demo2.todo.dto;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {

    private String content;

    public TodoSaveRequestDto(String content) {
        this.content = content;
    }
}
