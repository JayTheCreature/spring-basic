/**
 * POST 클래스 제어하는 Interface
 * JpaRepository
 * JPA는 자바 내에서 sql 제어 등을 자동으로 해주는 라이브러리
 * 직접 sql 을 작성하지 않고도 직접 DB에 접속하지 않아도 JPQL 을 생성한다.
 */
package com.example.demo.repository;

import com.example.demo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository <엔티티 클래스, PK 타입> 을 상속하면 CRUD 기본 기능 자동 제공
public interface PostRepository extends JpaRepository<Post, Long> {

    // Methods
    // 여기에 정의하면 PostRepository를 참조하는 곳(서비스)에서 메서드 형태로 사용 가능한 것 같다.
    // 제목 검색
    List<Post> findByTitle(String keyword);

    // 검색 결과 갯수
    long countByTitle(String keyword);
}
