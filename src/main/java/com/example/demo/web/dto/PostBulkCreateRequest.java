/**
 * Bulk 게시물 등록 API
 * 요청 DTO (배치 전용)
 */
package com.example.demo.web.dto; // 패키지 선언

import jakarta.validation.Valid; // 중첩 객체에 대해 Bean Validation을 수행하기 위해
//import jakarta.validation.constraints.NotBlank; // 컬렉션이 비어있지 않음을 검증하는 애너테이션
import jakarta.validation.constraints.NotEmpty; // null + 빈 값(””) 금지

import java.util.List; // 리스트 객체 사용

/**
 * 여러 게시글을 한번에 생성하기 위한 요청 DTO
 * 요청 json body 예:
 * {
 *     posts: [
 *      {},
 *      {},
 *     ]
 * }
 */
public class PostBulkCreateRequest {

	@NotEmpty(message = "posts 배열은 비어있을 수 없습니다.")
	@Valid // 내부 요소 (PostCreateRequest)의 Bean Validation 수행
	private List<PostCreateRequest> posts; // 배치로 생성할 개별 게시글 요청 Request JSON 목록

	// getter methods
	public List<PostCreateRequest> getPosts() {
		return posts; // Reqeust JSON 반환
	}
}
