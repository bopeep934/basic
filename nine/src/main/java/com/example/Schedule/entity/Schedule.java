package com.example.Schedule.entity;

import com.example.Schedule.dto.ScheduleResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String todo;
    private String location;

    public Schedule(String todo, String location){
        this.todo=todo;
        this.location=location;
    }
    public void update(String todo, String location){
        this.todo= todo;
        this.location= location;
    }
}
