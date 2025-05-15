package com.example.scheduleproject2.service;

import com.example.scheduleproject2.domain.comment.CommentRepository;
import com.example.scheduleproject2.domain.schedule.Schedule;
import com.example.scheduleproject2.domain.schedule.ScheduleRepository;
import com.example.scheduleproject2.dto.comment.CommentResponse;
import com.example.scheduleproject2.dto.schedule.ScheduleDetailResponse;
import com.example.scheduleproject2.dto.schedule.ScheduleRequest;
import com.example.scheduleproject2.dto.schedule.ScheduleResponse;
import com.example.scheduleproject2.dto.schedule.ScheduleUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 일정 관련 비즈니스 로직을 담당하는 서비스 클래스
 * 생성, 조회, 수정, 삭제 등의 기능을 제공
 * ID를 기반으로 일정을 조회하며 수정과 삭제는 작성자ID가 일치할 경우에만 가능하고
 * 일정목록에는 댓글개수, 일정상세조회에는 댓글목록을 포함.
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final CommentRepository commentRepository;
	private final CommentService commentService;

	@Transactional
	public Long create(ScheduleRequest request) {
		Schedule schedule = Schedule.builder()
				.title(request.getTitle())
				.content(request.getContent())
				.writerId(request.getWriterId())
				.build();
		return scheduleRepository.save(schedule).getId();
	}

	@Transactional(readOnly = true)
	public Schedule findById(Long id) {
		return scheduleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("해당 일정이 존재하지 않습니다."));
	}


	@Transactional
	public void update(Long id, ScheduleUpdateRequest request) {
		Schedule schedule = findById(id);
		if (!schedule.getWriterId().equals(request.getWriterId())) {
			throw new IllegalArgumentException("권한이 없습니다.");
		}
		schedule.update(request.getTitle(), request.getContent());
	}

	@Transactional
	public void delete(Long id, String writerId) {
		Schedule schedule = findById(id);
		if (!Objects.equals(schedule.getWriterId(), writerId)) {
			throw new IllegalArgumentException("권한이 없습니다.");
		}
		scheduleRepository.delete(schedule);
	}

	public List<ScheduleResponse> findAllWithCommentCount() {
		List<Schedule> schedules = scheduleRepository.findAll();

		return schedules.stream()
				.map(schedule -> {
					int count = commentRepository.countByScheduleId(schedule.getId());
					return new ScheduleResponse(schedule, count);
				})
				.toList();
	}

	public ScheduleDetailResponse findDetailById(Long id) {
		Schedule schedule = findById(id);
		List<CommentResponse> comments = commentService.findAllByScheduleId(id);
		return new ScheduleDetailResponse(schedule, comments);
	}


}
