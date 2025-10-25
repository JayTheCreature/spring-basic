package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.web.dto.PostResponse;
import com.example.demo.web.dto.PostCreateRequest;
import com.example.demo.web.dto.PostUpdateRequest;
import com.example.demo.web.dto.PostListResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    // JpaRepository 기능 상속 받아서 Service 로 제공하기 위한 내부 변수 선언
    // postRepository.methods: methods는 JpaRepository에서 제공하는 메서드를 그대로 상속 이어 받는다.
    private final PostRepository postRepository;

    // constructor 주입 (DI)
    // 클래스 호출시 자동 실행 됨
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 이하 PostService에서 사용 할 메서드 정의

    // 게시글 작성
    @Transactional // DB에 쓰기라서 트랜잭션
    public PostResponse create(PostCreateRequest req) {
        Post saved = postRepository.save(new Post(req.getTitle(), req.getContent(), req.getAuthor()));

        return new PostResponse(saved);
    }

    // 단건 조회
    public PostResponse get(Long id) {
        Post found = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));

        return new PostResponse(found);
    }

    // 전체 게시글 조회 (목록)
    public List<PostResponse> list() {
//        List<PostResponse> postList = postRepository.findAll().stream().map(PostResponse::new).toList();
        return postRepository.findAll().stream().map(PostResponse::new).toList();
//        return postList;
    }

    // 전체 목록 조회 + 게시갈 카운트
    public PostListResponse listWithCount(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            // 전체 목록 without 검색어(keyword)
            List<PostResponse> posts = postRepository.findAll()
                    .stream()
                    .map(PostResponse::new)
                    .toList();

            // 게시물 카운트
            long totalCount = postRepository.count();

            return new PostListResponse(posts, totalCount);
        } else {
            // 검색어 있을 때
            List<PostResponse> posts = postRepository.findByTitle(keyword)
                    .stream()
                    .map(PostResponse::new)
                    .toList();

            // 게시물 카운트
            long totalCount = postRepository.count();

            return new PostListResponse(posts, totalCount);
        }
    }

    // 게시글 수정
    @Transactional
    public PostResponse update(Long id, PostUpdateRequest req) {
        Post found = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));

        found.update(req.getTitle(), req.getContent());

        return new PostResponse(found);
    }

    // 게시글 삭제
    @Transactional
    public void delete(Long id) {
        Post found = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));

        postRepository.delete(found);
    }
}
