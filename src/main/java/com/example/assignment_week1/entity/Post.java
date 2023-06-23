package com.example.assignment_week1.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @Column(name = "post_id", unique = true, nullable = false)
    private Long posId;


    @Column
    private String password;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String content;

    @Column
    private LocalDateTime created_at;



}