package com.example.scheduleproject2.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
/**
 * 일정 생성 요청을 처리하기 위한 DTO 클래스
 * 제목과 ID가 비어있을 경우 예외메세지 출력
 */
@Getter
public class ScheduleRequest {

	@NotBlank(message = "제목이 없습니다.")
	private String title;

	private String content;

	@NotBlank(message = "작성자 ID가 없습니다.")
	private String writerId;
}
