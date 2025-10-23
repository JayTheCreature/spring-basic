package com.example.demo.web;

import com.example.demo.common.ApiResponse;
import com.example.demo.service.PostService;
import com.example.demo.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController // REST API 사용 선언
@RequestMapping("/api/posts") //  API 접근 경로
public class PostController {

    private final PostService postService;

    // 생성자 주입
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "게시글 생성", description = "신규 게시글 생성 API")
    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> create(@Valid @RequestBody PostCreateRequest req) {
        PostResponse created = postService.create(req);
        // Location 헤더 포함하여 201 Created 반환
        return ResponseEntity.created(URI.create("/api/posts/" + created.getId())).body(ApiResponse.ok(created));
    }

    @Operation(summary = "게시물 조회", description = "id로 게시물 조회 GET")
    @GetMapping("/{id}")
    public ApiResponse<PostResponse> get(@PathVariable Long id) {
        return ApiResponse.ok(postService.get(id));
    }

    @Operation(summary = "게시판 리스트 조회")
    @GetMapping
    public ApiResponse<List<PostResponse>> list() {
        return ApiResponse.ok(postService.list());
    }

    @Operation(summary = "게시글 수정")
    @PostMapping("/{id}")
    public ApiResponse<PostResponse> update(@PathVariable Long id, @Valid @RequestBody PostUpdateRequest req) {
        return ApiResponse.ok(postService.update(id, req));
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ApiResponse.ok(null);
    }
}
