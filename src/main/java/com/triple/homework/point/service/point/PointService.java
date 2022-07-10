package com.triple.homework.point.service.point;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triple.homework.point.domain.event.PointHistory;
import com.triple.homework.point.domain.event.PointHistoryRepository;
import com.triple.homework.point.domain.user.User;
import com.triple.homework.point.util.type.EventType;
import com.triple.homework.point.util.type.PointState;
import com.triple.homework.point.util.type.PointType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PointService {

	private final PointHistoryRepository pointHistoryRepository;

	@Transactional(readOnly = true)
	public List<PointHistory> findPointEvents(User user, Pageable pageable) {
		return pointHistoryRepository.findAllByUser(user, pageable);
	}


	public void registerPoint(EventType eventType, String eventTargetId, PointType pointType, long amount, User user) {
		pointHistoryRepository.save(PointHistory.builder()
			.eventType(eventType)
			.eventTargetId(eventTargetId)
			.pointType(pointType)
			.state(PointState.findByPointAmount(amount))
			.amount(amount)
			.user(user)
			.build());
	}

	public void deletePoint(EventType eventType, String eventTargetId, PointType pointType, User user) {
		long sum = pointHistoryRepository.findPointHistoriesByEventTypeAndEventTargetId(eventType, eventTargetId)
			.stream()
			.mapToLong(PointHistory::getAmount)
			.sum();

		registerPoint(eventType, eventTargetId, pointType, sum * -1, user);
	}
}
