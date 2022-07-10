package com.triple.homework.point.service.event;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.triple.homework.point.util.type.EventType;

@SpringBootTest
class EventServiceFactoryTest {

	@Autowired
	private EventServiceFactory eventServiceFactory;

	@Test
	void EventType_이_REVIEW_면_ReviewEventServiceImpl() {
		assertThat(eventServiceFactory.findEventServiceByType(EventType.REVIEW))
			.isInstanceOf(ReviewEventServiceImpl.class);
	}
}