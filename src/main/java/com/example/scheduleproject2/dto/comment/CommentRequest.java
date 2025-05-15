package com.example.scheduleproject2.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
/**
 * 댓글 생성 요청을 처리하기 위한 DTO 클래스
 * 댓글내용괴 ID가 비어있을 경우 예외메세지 출력
 */
@Getter
public class CommentRequest {

	@NotBlank(message = "댓글 내용이 없습니다.")
	private String content;

	@NotBlank(message = "작성자 ID가 없습니다.")
	private String writerId;
	/**
	 * 부모 댓글 ID
	 * 값이 null이면 일반 댓글
	 * 값이 있으면 해당 댓글은 대댓글로 간주
	 */
	private Long parentCommentId; // null이면 일반 댓글, 값이 있으면 대댓글
}
