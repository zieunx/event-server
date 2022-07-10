package com.triple.homework.point.service.event;

import static com.triple.homework.point.domain.Fixtures.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.triple.homework.point.domain.review.Review;
import com.triple.homework.point.domain.review.ReviewRepository;
import com.triple.homework.point.domain.user.User;
import com.triple.homework.point.domain.user.UserRepository;
import com.triple.homework.point.dto.event.RequestRegisterEventDto;

@SpringBootTest
class ReviewEventServiceImplTest {

	@Autowired
	private ReviewEventServiceImpl<Review> reviewEventService;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private UserRepository userRepository;

	User user;

	@BeforeEach
	void SetUp() {
		user = userRepository.findById(UUID.fromString("f8f5f991-b3e1-4f37-95c7-5353426e11a8"))
			.orElseThrow(IllegalArgumentException::new);
	}

	@Test
	void addEvent() {
		reviewEventService.addEvent(aRequestRegisterEventDto().build(), user);
	}
}