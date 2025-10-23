package com.example.demo.web.dto;

import com.example.demo.domain.Post;
import java.time.LocalDateTime;

// 게시글 응답 DTO (엔티티 데이터를 JSON으로 변환하는 기능)
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    // 엔티티를 받아서 response 객체로 변환
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }

    // Getter: 처리 후 데이터를 컨트롤러나 서비스로 전달하기 위한 역할
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
