package com.example.assignment_week1.controller;


import com.example.assignment_week1.dto.PostRequestDto;
import com.example.assignment_week1.dto.PostResponseDto;
import com.example.assignment_week1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;



    @GetMapping("/get/all")
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> responseDtoList = postService.getAllPosts();
        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping("/create")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto) {
        // PostRequestDto를 사용하여 게시글을 생성하고, 생성된 게시글의 정보를 PostResponseDto에 담아 반환합니다.
        PostResponseDto responseDto = postService.createPost(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        // id를 사용하여 해당 게시글의 정보를 조회하고, 조회된 게시글의 정보를 PostResponseDto에 담아 반환합니다.
        PostResponseDto responseDto = postService.getPost(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        // id와 수정할 데이터를 사용하여 게시글을 수정하고, 수정된 게시글의 정보를 PostResponseDto에 담아 반환합니다.
        PostResponseDto responseDto = postService.updatePost(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long id, @RequestParam String password) {
        // id와 비밀번호를 사용하여 게시글을 삭제합니다.
        postService.deletePost(id, password);
        return ResponseEntity.ok("게시글이 삭제 되었습니다. .");
    }
}