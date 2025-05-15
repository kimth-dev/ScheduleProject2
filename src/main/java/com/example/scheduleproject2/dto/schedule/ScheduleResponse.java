package com.example.scheduleproject2.dto.schedule;

import com.example.scheduleproject2.domain.schedule.Schedule;
import java.time.LocalDateTime;
import lombok.Getter;
/**
 * 일정 목록 조회 시 클라이언트에 반환되는 응답 DTO
 * 순서대로 일정ID, 일정제목, 일정 내용, 작성자ID, 생성일, 수정일, 댓글 개수를 나타냄.
 */
@Getter
public class ScheduleResponse {

	private Long id;
	private String title;
	private String content;
	private String writerId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int commentCount;

	public ScheduleResponse(Schedule schedule, int commentCount) {
		this.id = schedule.getId();
		this.title = schedule.getTitle();
		this.content = schedule.getContent();
		this.writerId = schedule.getWriterId();
		this.createdAt = schedule.getCreatedAt();
		this.updatedAt = schedule.getUpdatedAt();
		this.commentCount = commentCount;
	}
}
