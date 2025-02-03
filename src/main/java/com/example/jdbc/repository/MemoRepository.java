package com.example.jdbc.repository;

import com.example.jdbc.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    Memo save(Memo memo);// create
    Optional<Memo> findById(Long id);//read 단건 조회, optional은 null 에러 방지용. 목록이 없을 수도 있으니까.
    List<Memo> findAll();//read 다건
//    Memo update(Memo memo);//update
    Memo updateContent(Long id, String content) ;
    void deleteById(Long id);//delete
}
