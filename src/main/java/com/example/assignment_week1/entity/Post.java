package com.example.assignment_week1.entity;


import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = true, nullable = false)
    private Long postId;


    @Column
    private String password;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String content;

    @Column
    private LocalDateTime createdAt;


    public Post getEntity(String password, String title, String author, String content, LocalDateTime createdAt) {
        Post post = new Post();

        post.setPassword(password);
        post.setTitle(title);
        post.setAuthor(author);
        post.setContent(content);
        post.setCreatedAt(createdAt);

        return post;
    }
}