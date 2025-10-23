package com.example.demo.web.dto;

import jakarta.validation.constraints.Size;

public class PostUpdateRequest {
    @Size(max = 200, message = "제목은 200자 이내여야 합니다.")
    private String title;

    private String content;

    // Getter: 처리 후 데이터를 컨트롤러나 서비스로 전달하기 위한 역할
    public String getTitle() { return title; }
    public String getContent() { return content; }
}
