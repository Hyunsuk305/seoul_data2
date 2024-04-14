package com.example.seoul_data.common;

public class ResponseUtil {

    // 요청 성공인 경우
    public static <T> ApiResponseDto<T> ok(T response) {
        return ApiResponseDto.<T>builder()
                .success(true)
                .response(response)
                .build();
    }

    // 에러 발생한 경우
    public static <T> ApiResponseDto<T> error(ErrorResponse response) {
        return ApiResponseDto.<T>builder()
                .success(false)
                .error(response)
                .build();
    }
}