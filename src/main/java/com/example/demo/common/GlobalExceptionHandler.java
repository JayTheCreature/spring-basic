package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice // 전역 예외 처리기
public class GlobalExceptionHandler {

    // 엔티티를 찾지 못한 경우
    @ExceptionHandler({IllegalAccessException.class})
    public ResponseEntity<ApiResponse<Void>> handleIllegalAccessException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
    }

    // validation 실패
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<ApiResponse<Void>> handleValidation(Exception e) {
        return ResponseEntity.badRequest().body(ApiResponse.error("Validation failed (유효성 검증 실패): " + e.getMessage()));
    }

    // 그 외 모든 에러
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse<Void>> handleOther(Exception e) {
        // 개발 중에는 구체적인 오류 메세지 표시
        return ResponseEntity.internalServerError().body(ApiResponse.error("내부 서버 에러: " + e.getMessage()));
    }
}
