package com.triple.homework.point.controller.point;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triple.homework.point.domain.event.PointHistory;
import com.triple.homework.point.domain.user.User;
import com.triple.homework.point.domain.user.UserRepository;
import com.triple.homework.point.dto.point.RequestPointDto;
import com.triple.homework.point.service.point.PointService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("points")
@RestController
public class PointController {

	private final PointService pointService;
	private final UserRepository userRepository;

	@GetMapping
	public ResponseEntity<List<PointHistory>> getPoint(RequestPointDto requestPointDto) {
		Pageable pageable = PageRequest.of(
			requestPointDto.getPage(),
			requestPointDto.getSize(),
			Sort.by("createdDateTime").descending());
		User user = userRepository.findById(UUID.fromString(requestPointDto.getUserId()))
			.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

		return ResponseEntity.ok(pointService.findPointEvents(user, pageable));
	}
}
