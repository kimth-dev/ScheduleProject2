package com.example.scheduleproject2.service;

import com.example.scheduleproject2.domain.comment.Comment;
import com.example.scheduleproject2.domain.comment.CommentRepository;
import com.example.scheduleproject2.domain.schedule.Schedule;
import com.example.scheduleproject2.domain.schedule.ScheduleRepository;
import com.example.scheduleproject2.dto.comment.CommentRequest;
import com.example.scheduleproject2.dto.comment.CommentResponse;
import com.example.scheduleproject2.dto.comment.CommentUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 댓글, 대댓글 관련 비즈니스 로직을 담당하는 서비스 클래스
 * 생성, 조회, 수정, 삭제 등의 기능을 제공
 * 댓글 생성시 부모댓글이 지정되면 대댓글로 간주
 * 부모 댓글이 대댓글일 경우 예외 발생 (1-depth 제한)
 * 작성자ID가 일치할 경우만 수정및 삭제 가능
 */
@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final ScheduleRepository scheduleRepository;

	@Transactional
	public Long create(Long scheduleId, CommentRequest request) {
		Schedule schedule = scheduleRepository.findById(scheduleId)
				.orElseThrow(() -> new EntityNotFoundException("일정이 존재하지 않습니다."));

		Comment parent = null;
		if (request.getParentCommentId() != null) {
			parent = commentRepository.findById(request.getParentCommentId())
					.orElseThrow(() -> new EntityNotFoundException("부모 댓글이 존재하지 않습니다."));

			if (parent.getParentComment() != null) {
				throw new IllegalArgumentException("대댓글의 대댓글은 허용되지 않습니다.");
			}
		}

		Comment comment = Comment.builder()
				.content(request.getContent())
				.writerId(request.getWriterId())
				.schedule(schedule)
				.parentComment(parent)
				.build();

		return commentRepository.save(comment).getId();
	}
	/**
	 * 해당 일정에 작성된 모든 댓글과 대댓글을 조회
	 * 부모 댓글은 작성일 기준 오름차순 정렬
	 * 각 부모 댓글 하위에 대댓글이 포함되어 응답
	 */
	@Transactional(readOnly = true)
	public List<CommentResponse> findAllByScheduleId(Long scheduleId) {
		List<Comment> comments = commentRepository.findByScheduleIdOrderByCreatedAtAsc(scheduleId);

		Map<Long, CommentResponse> map = new LinkedHashMap<>();
		List<CommentResponse> result = new ArrayList<>();

		for (Comment comment : comments) {
			if (comment.getParentComment() == null) {
				CommentResponse response = new CommentResponse(comment, new ArrayList<>());
				map.put(comment.getId(), response);
				result.add(response);
			} else {
				CommentResponse parent = map.get(comment.getParentComment().getId());
				if (parent != null) {
					parent.getReplies().add(new CommentResponse(comment, List.of()));
				}
			}
		}
		return result;
	}

	@Transactional
	public void update(Long commentId, CommentUpdateRequest request) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new EntityNotFoundException("해당 댓글이 존재하지 않습니다."));

		if (!comment.getWriterId().equals(request.getWriterId())) {
			throw new IllegalArgumentException("권한이 없습니다.");
		}

		comment.update(request.getContent());
	}

	@Transactional
	public void delete(Long commentId, String writerId) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new EntityNotFoundException("해당 댓글이 존재하지 않습니다."));

		if (!comment.getWriterId().equals(writerId)) {
			throw new IllegalArgumentException("권한이 없습니다.");
		}

		commentRepository.delete(comment);
	}
}
