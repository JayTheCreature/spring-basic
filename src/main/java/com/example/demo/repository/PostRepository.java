// POST 클래스 제어하는 Interface

package com.example.demo.repository;

import com.example.demo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository <엔티티 클래스, PK 타입> 을 상속하면 CRUD 기본 기능 자동 제공
public interface PostRepository extends JpaRepository<Post, Long> {

}
