package com.example.assignment_week1.repository;

import com.example.assignment_week1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
