package com.example.demo.web.dto;

import jakarta.validation.constraints.NotBlank; // null, "", " " 모두 금지
import jakarta.validation.constraints.Size; // 최대, 최소 입력값 체크

public class PostCreateRequest {

    @NotBlank(message = "필수 입력값입니다.")
    @Size(max = 200, message = "제목은 200자 이내여야 합니다.")
    private String title;

    @NotBlank(message = "필수 입력값입니다.")
    private String content;

    @NotBlank(message = "필수 입력값입니다.")
    @Size(max = 50, message = "작성자는 50자 이내여야 합니다.")
    private String author;

    // Getter: 처리 후 데이터를 컨트롤러나 서비스로 전달하기 위한 역할
    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getAuthor() {return author;}
}
