package com.example.Memo.controller;

import com.example.Memo.dto.MemoRequestDto;
import com.example.Memo.dto.MemoResponseDto;
import com.example.Memo.entity.Memo;
import com.example.Memo.service.MemoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memos")
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    public ResponseEntity<MemoResponseDto> save(@RequestBody MemoRequestDto dto){

        return ResponseEntity.ok(memoService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> findAll(){

        return ResponseEntity.ok(memoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(memoService.findById(id));

    }
    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> update(@PathVariable Long id, @RequestBody MemoRequestDto dto){
        return ResponseEntity.ok(memoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
         memoService.deleteById(id);
    }
}
