package com.example.scheduleproject2.dto.comment;

import com.example.scheduleproject2.domain.comment.Comment;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
/**
 * 댓글 조회 시 응답을 위한 DTO클래스
 * 순서대로 댓글ID, 댓글내용, 작성자ID, 생성일, 수정일, 부모댓글ID, 대댓글 목록을 나타냄.
 */
@Getter
public class CommentResponse {

	private Long id;
	private String content;
	private String writerId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long parentCommentId;
	private List<CommentResponse> replies;

	/**
	 *
	 * 부모 댓글일 경우 replies 필드에 대댓글 목록을 포함
	 * 대댓글일 경우 replies는 null로 설정
	 */
	public CommentResponse(Comment comment, List<CommentResponse> replies) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.writerId = comment.getWriterId();
		this.createdAt = comment.getCreatedAt();
		this.updatedAt = comment.getUpdatedAt();
		this.parentCommentId =
				comment.getParentComment() != null ? comment.getParentComment().getId() : null;
		/** 부모 댓글인 경우에만 replies 포함
		 */
		if (comment.getParentComment() == null) {
			this.replies = replies;
		}
	}
}
