package com.example.Schedule.service;

import com.example.Schedule.dto.ScheduleRequestDto;
import com.example.Schedule.dto.ScheduleResponseDto;
import com.example.Schedule.entity.Schedule;
import com.example.Schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto) {
        Schedule schedule=new Schedule(dto.getTodo(), dto.getLocation());
        Schedule savedSchedule=scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule.getId(),savedSchedule.getTodo(),savedSchedule.getLocation());
    }

    @Transactional(readOnly=true)
    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules=scheduleRepository.findAll();
        List<ScheduleResponseDto> dtos=new ArrayList<>();
        for(Schedule schedule: schedules)
            dtos.add(new ScheduleResponseDto(schedule.getId(), schedule.getTodo(), schedule.getLocation()));

        return dtos;
    }

    @Transactional(readOnly= true)
    public ScheduleResponseDto findById(Long id){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException(" 찾는 일정이 없습니다.")
        );

        return new ScheduleResponseDto(schedule.getId(),schedule.getTodo(),schedule.getLocation());
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto){
        Schedule schedule= scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException( "찾는 일정이 없습니다.")
        );
        schedule.update(dto.getTodo(), dto.getLocation());
       return new ScheduleResponseDto(schedule.getId(),schedule.getTodo(),schedule.getLocation());
    }

    @Transactional
    public void delete(Long id){
        if(!scheduleRepository.existsById(id)){
            throw new IllegalArgumentException("찾는 일정이 없습니다.");
        }

        scheduleRepository.deleteById(id);
    }
}
