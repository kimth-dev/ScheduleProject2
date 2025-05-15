package com.example.scheduleproject2.domain.comment;

import com.example.scheduleproject2.domain.schedule.Schedule;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
/**
 *  댓글 엔티티 클래스
 * JPA Auditing을 통해 createdAt, updatedAt이 자동 설정됨.
 * nullable = false가 설정된 컬럼은 DB에서 null값을 허용하지 않음.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String writerId;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	/**이 댓글이 속한 일정
	  */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id", nullable = false)
	private Schedule schedule;


	/**
	 * 대댓글 기능을 위한 부모댓글필드
	 * 값이 null이면 일반 댓글
	 * 값이 있으면 해당 댓글은 대댓글로 간주
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_comment_id")
	private Comment parentComment;

	@Builder
	public Comment(String content, String writerId, Schedule schedule, Comment parentComment) {
		this.content = content;
		this.writerId = writerId;
		this.schedule = schedule;
		this.parentComment = parentComment;
	}

	public void update(String content) {
		this.content = content;
	}
}
