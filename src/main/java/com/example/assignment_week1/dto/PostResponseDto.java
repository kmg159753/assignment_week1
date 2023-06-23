package com.example.assignment_week1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter

public class PostResponseDto {
    private String title;
    private String author;
    private String content;
    private LocalDateTime created_at;



}