package com.example.demo2.todo.service;

import com.example.demo2.member.entity.Member;
import com.example.demo2.member.repository.MemberRepository;
import com.example.demo2.todo.dto.TodoResponseDto;
import com.example.demo2.todo.dto.TodoSaveRequestDto;
import com.example.demo2.todo.dto.TodoSaveResponseDto;
import com.example.demo2.todo.dto.TodoUpdateRequestDto;
import com.example.demo2.todo.entity.Todo;
import com.example.demo2.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TodoSaveResponseDto save(Long memberId, TodoSaveRequestDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalStateException("해당 사용자를 찾을 수 없습니다."));
        Todo todo = new Todo(dto.getContent(),member);
        Todo saveTodo = todoRepository.save(todo);
        return new TodoSaveResponseDto(
                saveTodo.getId(),
                saveTodo.getContent(),
                member.getId(),
                member.getEmail());
    }

    @Transactional(readOnly= true)
    public List<TodoResponseDto> findAll() {
        List<Todo> todos= todoRepository.findAll();
        List<TodoResponseDto> dtos= new ArrayList<>();

        for(Todo todo : todos) {
            dtos.add(new TodoResponseDto(
                    todo.getId(),
                    todo.getContent()
            ));
        }
        return dtos;
    }

    @Transactional(readOnly= true)
    public TodoResponseDto findById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("해당 일정을 찾을 수 없습니다."));

        return new TodoResponseDto(
                todo.getId(),
                todo.getContent()
        );
    }

    @Transactional
    public TodoResponseDto update(Long memberId, Long todoId, TodoUpdateRequestDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalStateException("해당 사용자를 찾을 수 없습니다."));

        Todo todo =todoRepository.findById(todoId)
                .orElseThrow(()-> new IllegalStateException("해당 일정을 찾을 수 없습니다."));

        if(!todo.getMember().getId().equals(member.getId())){
            throw new IllegalStateException("작성자만 일정을 수정할 수 있습니다.");
        }

        todo.update(dto.getContent());

        return new TodoResponseDto(
                todo.getId(),
                todo.getContent()
        );
    }

    @Transactional
    public void deleteById(Long memberId, Long todoId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalStateException("해당 사용자를  찾을 수 없습니다."));

        Todo todo =todoRepository.findById(todoId)
                .orElseThrow(()-> new IllegalStateException("해당 일정을 찾을 수 없습니다."));

        if(!todo.getMember().getId().equals(member.getId())){
            throw new IllegalStateException("작성자만 일정을 수정할 수 있습니다.");
        }

        todoRepository.deleteById(todoId);
    }
}
