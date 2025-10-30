/**
 * Rest API Request 정의
 */
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

	/**
	 * URI: /api/posts (/api/posts/ 같이 끝에 슬래시 붙이면 안됨)
	 * @param req: request JSON body
	 * @return 등록 결과 반환
	 */
	@Operation(summary = "게시글 생성", description = "신규 게시글 생성 API")
	@PostMapping
	public ResponseEntity<ApiResponse<PostResponse>> create(@Valid @RequestBody PostCreateRequest req) {
		PostResponse created = postService.create(req);
		// Location 헤더 포함하여 201 Created 반환
		return ResponseEntity.created(URI.create("/api/posts/" + created.getId())).body(ApiResponse.ok(created));
	}

	/**
	 * 게시글 Bulk 생성 컨트롤러
	 * @param req: request JSON body
	 * @return 등록 결과 반환
	 */
	@Operation(summary = "게시글 다중 생성") // swagger 연동
	@PostMapping("/bulk") // POST API URI 정의
	public ResponseEntity<ApiResponse<PostBulkCreateResponse>> createBulk(
			@Valid @RequestBody PostBulkCreateRequest req
	) {
		System.out.println("req: " + req);
		// Request 받아서 등록 서비스로 보낸다
		PostBulkCreateResponse created = postService.bulkCreate(req);
		System.out.println("created: " + created);
		return ResponseEntity.ok(ApiResponse.ok(created));
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

    @Operation(summary = "게시판 리스트 조회 with 카운트")
    @GetMapping("/list")
    public ApiResponse<PostListResponse> listWithCount(
            // 요청 파라미터는 인자로 정의한다 eg. /api/posts?keyword=%검색어% 형태 사용
            // 검색어
            @RequestParam(name = "keyword", required = false) String keyword
    ) {
        return ApiResponse.ok(postService.listWithCount(keyword));
    }

	/**
	 * 게시물 수정 api
	 * @param id update 대상 게시물 id
	 * @param updateRequestBody 클라이언트에서 보내는 json body가 들어옴 @valid도 수행 한다.
	 * @return 처리 결과
	 */
    @Operation(summary = "게시글 수정")
    @PostMapping("/{id}")
    public ApiResponse<PostResponse> update(@PathVariable Long id, @Valid @RequestBody PostUpdateRequest updateRequestBody) {
        return ApiResponse.ok(postService.update(id, updateRequestBody));
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ApiResponse.ok(null);
    }
}
