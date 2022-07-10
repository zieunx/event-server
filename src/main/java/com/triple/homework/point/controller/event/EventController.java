package com.triple.homework.point.controller.event;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triple.homework.point.domain.review.Review;
import com.triple.homework.point.domain.user.User;
import com.triple.homework.point.domain.user.UserRepository;
import com.triple.homework.point.dto.event.RequestRegisterEventDto;
import com.triple.homework.point.service.event.EventService;
import com.triple.homework.point.service.event.EventServiceFactory;
import com.triple.homework.point.util.type.EventAction;
import com.triple.homework.point.util.type.EventType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("events")
@RestController
public class EventController {

	private final EventServiceFactory eventServiceFactory;
	private final UserRepository userRepository;

	@PostMapping
	public ResponseEntity<?> issueEvent(@Valid @RequestBody RequestRegisterEventDto issueEventDto) {
		EventService eventService = eventServiceFactory.findEventServiceByType(EventType.valueOf(issueEventDto.getType()));

		try {
			User user = userRepository.findById(UUID.fromString(issueEventDto.getUserId()))
				.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
			if (EventAction.valueOf(issueEventDto.getAction()) == EventAction.ADD) {
				Review review = (Review) eventService.addEvent(issueEventDto, user);
				return ResponseEntity.created(URI.create("/reviews/" + review.getId()))
					.body("created");
			} else if (EventAction.valueOf(issueEventDto.getAction()) == EventAction.MOD) {
				eventService.modifyEvent(issueEventDto, user);
				return ResponseEntity.ok("modified");
			} else if (EventAction.valueOf(issueEventDto.getAction()) == EventAction.DELETE) {
				eventService.deleteEvent(issueEventDto, user);
				return ResponseEntity.noContent().build();
			}
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}

		return ResponseEntity.badRequest().build();
	}
}
