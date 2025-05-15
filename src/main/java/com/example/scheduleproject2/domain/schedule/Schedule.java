package com.example.scheduleproject2.domain.schedule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/**
 * 일정 엔티티 클래스
 * JPA Auditing을 통해 createdAt, updatedAt이 자동 설정됨.
 * nullable = false가 설정된 컬럼은 DB에서 null값을 허용하지 않음.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column
	private String content;

	@Column(nullable = false)
	private String writerId;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@Builder
	public Schedule(String title, String content, String writerId) {
		this.title = title;
		this.content = content;
		this.writerId = writerId;
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
