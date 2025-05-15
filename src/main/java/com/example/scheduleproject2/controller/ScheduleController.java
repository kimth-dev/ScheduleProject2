package com.example.scheduleproject2.controller;

import com.example.scheduleproject2.dto.schedule.ScheduleDetailResponse;
import com.example.scheduleproject2.dto.schedule.ScheduleRequest;
import com.example.scheduleproject2.dto.schedule.ScheduleResponse;
import com.example.scheduleproject2.dto.schedule.ScheduleUpdateRequest;
import com.example.scheduleproject2.service.ScheduleService;
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
 * 일정 관련 HTTP 요청을 처리하는 컨트롤러 클래스
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

	private final ScheduleService scheduleService;

	@PostMapping
	public String create(@RequestBody @Valid ScheduleRequest request) {
		scheduleService.create(request);
		return "일정이 등록되었습니다.";
	}
	/**
	 * 댓글 개수를 포함하여 일정 목록 조회
	 */
	@GetMapping
	public List<ScheduleResponse> getAll() {
		return scheduleService.findAllWithCommentCount();
	}

	/**
	 * 댓글 목록을 포함하여 일정 상세 조회
	 */
	@GetMapping("/{id}")
	public ScheduleDetailResponse getOne(@PathVariable Long id) {
		return scheduleService.findDetailById(id);
	}


	@PatchMapping("/{id}")
	public String update(@PathVariable Long id, @RequestBody @Valid ScheduleUpdateRequest request) {
		scheduleService.update(id, request);
		return "일정이 수정되었습니다.";
	}

	@DeleteMapping("/{id}")
		public String delete(@PathVariable Long id, @RequestParam String writerId) {
			scheduleService.delete(id, writerId);
			return "일정이 삭제되었습니다.";
	}
}
