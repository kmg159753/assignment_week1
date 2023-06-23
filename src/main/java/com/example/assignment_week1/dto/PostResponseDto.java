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


    public PostResponseDto getResponseDto (String title, String author, String content, LocalDateTime created_at) {
        PostResponseDto dto = new PostResponseDto();

        dto.setContent(content);
        dto.setAuthor(author);
        dto.setTitle(title);
        dto.setCreated_at(created_at);

        return dto;
    }

}