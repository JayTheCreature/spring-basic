/**
 * 게시물 리스트 반환 클래스 객체
 */

package com.example.demo.web.dto;

import lombok.Getter;

import java.util.List;

// 게시글 목록 + 전체 count 같이 반환
@Getter
public class PostListResponse {

    // getter 다른 파일에서 조회하기 위한 게터
    // initial 클래스 내장 변수 정의
    private final List<PostResponse> posts;
    private final long totalCount;

    // constructor 본체 메서드 정의
    public PostListResponse(List<PostResponse> posts, long totalCount) {
        this.posts = posts;
        this.totalCount = totalCount;
    }

}
