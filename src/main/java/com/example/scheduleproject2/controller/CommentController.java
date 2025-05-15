package com.example.scheduleproject2.controller;

import com.example.scheduleproject2.dto.comment.CommentRequest;
import com.example.scheduleproject2.dto.comment.CommentResponse;
import com.example.scheduleproject2.dto.comment.CommentUpdateRequest;
import com.example.scheduleproject2.service.CommentService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 댓글 관련 HTTP 요청을 처리하는 컨트롤러 클래스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {

	private final CommentService commentService;

	@PostMapping
	public String create(@PathVariable Long scheduleId,
			@RequestBody @Valid CommentRequest request) {
		commentService.create(scheduleId, request);
		return "댓글이 등록되었습니다.";
	}

	@GetMapping
	public List<CommentResponse> getAll(@PathVariable Long scheduleId) {
		return commentService.findAllByScheduleId(scheduleId);
	}

	@PatchMapping("/{commentId}")
	public String update(@PathVariable Long commentId,
			@RequestBody @Valid CommentUpdateRequest request) {
		commentService.update(commentId, request);
		return "댓글이 수정되었습니다.";
	}

	@DeleteMapping("/{commentId}")
	public String delete(@PathVariable Long commentId, @RequestParam String writerId) {
		commentService.delete(commentId, writerId);
		return "댓글이 삭제되었습니다.";
	}
}
