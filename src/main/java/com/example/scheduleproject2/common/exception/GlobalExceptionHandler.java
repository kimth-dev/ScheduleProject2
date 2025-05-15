package com.example.scheduleproject2.common.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegal(IllegalArgumentException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	// Optional: RuntimeException 전체를 catch
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntime(RuntimeException ex) {
		ex.printStackTrace(); // 어떤 예외인지 콘솔에 출력
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
		// 첫 번째 필드 에러의 메시지를 꺼냄
		String message = ex.getBindingResult().getFieldError().getDefaultMessage();
		return ResponseEntity.badRequest().body(message);
	}
}
