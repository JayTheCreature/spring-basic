/**
 *  응답 DTO (생성 결과 목록 + 개수)
 */
package com.example.demo.web.dto;

import java.util.List;

/**
 * 배치 생성 결과: 생성된 게시글 목록 + 생성 개수
 */
public class PostBulkCreateResponse {

	// 벌크 생성 요청 받아 반환 할 생성 된 게시글 목록
	private final List<PostResponse> createdPosts;
	// 게시글 카운트
	private final int createdCount;

	// constructor
	public PostBulkCreateResponse(List<PostResponse> createdPosts) {
		this.createdPosts = createdPosts; // 필드 설정
		this.createdCount = createdPosts != null ? createdPosts.size() : 0;
	}

	/**
	 * getter methods
	 * 여기에 return 정의한 데이터만 ApiResponse Body에 포함 된다.
	 */
	// posts 반환
	public List<PostResponse> getCreatedPosts() {
		return createdPosts;
	}
	// posts 개수 반환
	public int getCreatedCount() {
		return createdCount;
	}
}

