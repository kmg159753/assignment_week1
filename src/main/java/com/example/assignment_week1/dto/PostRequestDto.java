package com.example.assignment_week1.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String author;
    private String password;
    private String content;


}