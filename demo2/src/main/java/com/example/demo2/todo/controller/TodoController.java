package com.example.demo2.todo.controller;

import com.example.demo2.todo.dto.TodoResponseDto;
import com.example.demo2.todo.dto.TodoSaveRequestDto;
import com.example.demo2.todo.dto.TodoSaveResponseDto;
import com.example.demo2.todo.dto.TodoUpdateRequestDto;
import com.example.demo2.todo.entity.Todo;
import com.example.demo2.todo.repository.TodoRepository;
import com.example.demo2.todo.service.TodoService;
import com.example.demo2.common.consts.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,

    @RequestBody
    TodoSaveRequestDto dto)

    {
        return ResponseEntity.ok(todoService.save(memberId, dto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDto>> getAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> getOne(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.findById(todoId));
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequestDto dto
    ) {
        return ResponseEntity.ok(todoService.update(memberId, todoId, dto));
    }

    @DeleteMapping("/todos/{todoId}")
    public void delete(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId) {
        todoService.deleteById(memberId, todoId);
    }
}



