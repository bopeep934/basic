package com.example.Member.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //자동으로 id 부여
    private Long id;
    private String name;

    public Member(String name){
        this.name=name;
    }
    public void update(String name){
        this.name=name;
    }
}
