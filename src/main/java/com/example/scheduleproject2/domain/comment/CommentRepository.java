package com.example.scheduleproject2.domain.comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByScheduleIdOrderByCreatedAtAsc(Long scheduleId);

	int countByScheduleId(Long scheduleId);
}
