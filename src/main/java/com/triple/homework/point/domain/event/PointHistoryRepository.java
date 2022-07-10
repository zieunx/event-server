package com.triple.homework.point.domain.event;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triple.homework.point.domain.user.User;
import com.triple.homework.point.util.type.EventType;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistory, UUID> {
	List<PointHistory> findPointHistoriesByEventTypeAndEventTargetId(EventType eventType, String eventTargetId);

	List<PointHistory> findAllByUser(User user, Pageable pageable);
}
