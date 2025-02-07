package com.example.Member.repository;

import com.example.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//이제 @repository안붙임 왜냐면 jparepository에 이미 들어있어서.
public interface MemberRepository extends JpaRepository<Member, Long> {


}
