package com.example.scheduleproject2.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
/**
 * 댓글 수정 요청을 처리하기 위한 DTO 클래스
 * 내용 ID가 비어있을 경우 예외메세지 출력
 */
@Getter
public class CommentUpdateRequest {

	@NotBlank(message = "댓글 내용이 없습니다.")
	private String content;

	@NotBlank(message = "작성자 ID가 없습니다.")
	private String writerId;
}
