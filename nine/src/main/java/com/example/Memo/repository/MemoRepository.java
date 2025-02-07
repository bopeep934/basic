package com.example.Memo.repository;


import com.example.Memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository  extends JpaRepository<Memo,Long> {
}
