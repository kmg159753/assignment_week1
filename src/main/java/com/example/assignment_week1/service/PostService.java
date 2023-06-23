package com.example.assignment_week1.service;

import com.example.assignment_week1.dto.PostRequestDto;
import com.example.assignment_week1.dto.PostResponseDto;
import com.example.assignment_week1.entity.Post;
import com.example.assignment_week1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;



    public List<PostResponseDto> getAllPosts() {

        return postRepository.findAll().stream().map(factor -> {
            PostResponseDto dto = new PostResponseDto();
            return dto.getResponseDto(factor.getTitle(), factor.getAuthor(),
                    factor.getContent(), factor.getCreatedAt());
        }).sorted(Comparator.comparing(PostResponseDto::getCreated_at).reversed()).collect(Collectors.toList());

    }

    public PostResponseDto getPost(Long id) {
        // id를 사용하여 게시글 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        // PostResponseDto에 조회된 게시글 정보 담기
        PostResponseDto responseDto = new PostResponseDto();

        return responseDto.getResponseDto(post.getTitle(), post.getAuthor(),
                post.getContent(), post.getCreatedAt());

    }
    public PostResponseDto createPost(PostRequestDto requestDto) {

        // PostRequestDto를 사용하여 새로운 게시글 생성
        Post post = new Post();




        // 게시글 저장
        Post savedPost = postRepository.save(
                post.getEntity(requestDto.getPassword(), requestDto.getTitle(),
                requestDto.getContent(), requestDto.getContent(), LocalDateTime.now())
        );

        // PostResponseDto에 저장된 게시글 정보 담기
        PostResponseDto responseDto = new PostResponseDto();

        return responseDto.getResponseDto(savedPost.getTitle(), savedPost.getAuthor(),
                savedPost.getContent(), savedPost.getCreatedAt());

    }



    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        // id를 사용하여 수정할 게시글 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수정하려는 게시글이 없습니다."));

        // 비밀번호 일치 여부 확인
        if (!post.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }
        // 게시글 수정
        post.setTitle(requestDto.getTitle());
        post.setAuthor(requestDto.getAuthor());
        post.setContent(requestDto.getContent());

       // 수정된 게시글 저장
//        postRepository.save(post);

        // PostResponseDto에 수정된 게시글 정보 담기
        PostResponseDto responseDto = new PostResponseDto();
        return responseDto.getResponseDto(post.getTitle(), post.getAuthor(),
                post.getContent(), post.getCreatedAt());
    }

    public void deletePost(Long id, String password) {
        // id를 사용하여 삭제할 게시글 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        // 비밀번호 일치 여부 확인
        if (!post.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password does not match");
        }
        // 게시글 삭제
        postRepository.delete(post);
    }
}