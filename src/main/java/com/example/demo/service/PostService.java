package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.web.dto.PostResponse;
import com.example.demo.web.dto.PostCreateRequest;
import com.example.demo.web.dto.PostUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    // 생성자 주입 (DI)
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

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
        return postRepository.findAll().stream().map(PostResponse::new).toList();
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
