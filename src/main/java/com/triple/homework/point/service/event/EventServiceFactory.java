package com.triple.homework.point.service.event;

import org.springframework.stereotype.Component;

import com.triple.homework.point.util.type.EventType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventServiceFactory {

	private final ReviewEventServiceImpl reviewEventService;
	public EventService findEventServiceByType(EventType eventType) {
		if (eventType == EventType.REVIEW) {
			return reviewEventService;
		}

		throw new IllegalArgumentException("서비스를 찾을 수 없습니다.");
	}
}
