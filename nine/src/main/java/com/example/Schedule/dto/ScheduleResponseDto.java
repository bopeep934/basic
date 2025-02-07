package com.example.Schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String todo;
    private String location;

    public ScheduleResponseDto(Long id, String todo, String location){
        this.id=id;
        this.todo=todo;
        this.location=location;
    }
}
