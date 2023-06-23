package com.example.assignment_week1.service;

import com.example.assignment_week1.dto.PostRequestDto;
import com.example.assignment_week1.dto.PostResponseDto;
import com.example.assignment_week1.entity.Post;
import com.example.assignment_week1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;



    public List<PostResponseDto> getAllPosts() {
        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "created_At"));

        List<PostResponseDto> responseDtoList = new ArrayList<>();
        for (Post post : postList) {
            PostResponseDto responseDto = new PostResponseDto();
            responseDto.setTitle(post.getTitle());
            responseDto.setAuthor(post.getAuthor());
            responseDto.setCreatedAt(post.getCreated_at());
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }
    public PostResponseDto createPost(PostRequestDto requestDto) {
        // PostRequestDto를 사용하여 새로운 게시글 생성
        Post post = new Post();
        post.setTitle(requestDto.getTitle());
        post.setPassword(requestDto.getPassword());
        post.setContent(requestDto.getContent());
        post.setCreated_at(LocalDateTime.now());
        post.setAuthor(requestDto.getAuthor());

        // 게시글 저장
        Post savedPost = postRepository.save(post);

        // PostResponseDto에 저장된 게시글 정보 담기
        PostResponseDto responseDto = new PostResponseDto();
        responseDto.setTitle(savedPost.getTitle());
        responseDto.setAuthor(savedPost.getAuthor());
        responseDto.setCreatedAt(savedPost.getCreated_at());
        responseDto.setContent(savedPost.getContent());
        return responseDto;
    }

    public PostResponseDto getPost(Long id) {
        // id를 사용하여 게시글 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        // PostResponseDto에 조회된 게시글 정보 담기
        PostResponseDto responseDto = new PostResponseDto();
        responseDto.setTitle(post.getTitle());
        responseDto.setAuthor(post.getAuthor());
        responseDto.setCreatedAt(post.getCreated_at());
        responseDto.setContent(post.getContent());

        return responseDto;
    }

    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        // id를 사용하여 수정할 게시글 조회
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        // 비밀번호 일치 여부 확인
        if (!post.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("Password does not match");
        }

        // 게시글 수정
        post.setTitle(requestDto.getTitle());
        post.setAuthor(requestDto.getAuthor());
        post.setContent(requestDto.getContent());

        // 수정된 게시글 저장
        Post updatedPost = postRepository.save(post);

        // PostResponseDto에 수정된 게시글 정보 담기
        PostResponseDto responseDto = new PostResponseDto();
        responseDto.setTitle(updatedPost.getTitle());
        responseDto.setAuthor(updatedPost.getAuthor());
        responseDto.setCreatedAt(updatedPost.getCreated_at());
        responseDto.setContent(updatedPost.getContent());

        return responseDto;
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