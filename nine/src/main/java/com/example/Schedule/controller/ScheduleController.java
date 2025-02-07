package com.example.Schedule.controller;


import com.example.Schedule.dto.ScheduleRequestDto;
import com.example.Schedule.dto.ScheduleResponseDto;
import com.example.Schedule.entity.Schedule;
import com.example.Schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;


    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto dto){
        return ResponseEntity.ok(scheduleService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        return ResponseEntity.ok(scheduleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        scheduleService.delete(id);
    }
}
