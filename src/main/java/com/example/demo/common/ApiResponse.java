/**
 * REST API Response Body 공통 처리 클래스
 */
package com.example.demo.common;

public class ApiResponse<T> {

    private final boolean success; // 성공 여부
    private final T data; // 응답 데이터
    private final String message; // 오류 메세지

    // 생성자
    private ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    // 성공 응답 공통
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null);
    }

	// 에러 공통
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, message);
    }

    public boolean isSuccess() {
        return success;
    }
    public T getData() { return data; }
    public String getMessage() { return message; }

}
