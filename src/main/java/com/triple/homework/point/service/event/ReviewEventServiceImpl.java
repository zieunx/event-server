package com.triple.homework.point.service.event;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.triple.homework.point.domain.place.Place;
import com.triple.homework.point.domain.place.PlaceRepository;
import com.triple.homework.point.domain.review.AttachedPhotoRepository;
import com.triple.homework.point.domain.review.Review;
import com.triple.homework.point.domain.review.ReviewPointCalculator;
import com.triple.homework.point.domain.review.ReviewRepository;
import com.triple.homework.point.domain.user.User;
import com.triple.homework.point.dto.event.RequestRegisterEventDto;
import com.triple.homework.point.service.point.PointService;
import com.triple.homework.point.util.type.EventType;
import com.triple.homework.point.util.type.PointType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewEventServiceImpl<T extends Review> implements EventService<T> {
	private final PlaceRepository placeRepository;
	private final ReviewRepository reviewRepository;
	private final AttachedPhotoRepository attachedPhotoRepository;
	private final PointService pointService;

	@Transactional
	@Override
	public T addEvent(RequestRegisterEventDto issueEventDto, User user) {
		Place place = placeRepository.findById(UUID.fromString(issueEventDto.getPlaceId()))
			.orElseThrow(() -> new IllegalArgumentException("장소를 찾을 수 없습니다."));

		if (reviewRepository.existsReviewsByUserIdAndPlaceIdAndIsDeletedFalse(user.getId(), place.getId())) {
			throw new IllegalArgumentException("이미 회원님이 작성된 리뷰가 있습니다.");
		}

		Review createReview = Review.builder()
			.content(issueEventDto.getContent())
			.user(user)
			.place(place)
			.attachedPhotos(attachedPhotoRepository.findAllById(issueEventDto.getAttachedPhotoIds().stream()
				.map(UUID::fromString)
				.collect(Collectors.toList())))
			.build();

		issuePoint(place, user, createReview);

		reviewRepository.save(createReview);

		return (T)createReview;
	}

	@Transactional
	@Override
	public T modifyEvent(RequestRegisterEventDto issueEventDto, User user) {
		Review originReview = reviewRepository.findById(UUID.fromString(issueEventDto.getReviewId()))
			.orElseThrow(() -> new IllegalArgumentException("요청하신 리뷰를 찾을 수 없습니다."));

		if (!originReview.getUser().equals(user)) {
			throw new IllegalArgumentException("다른 사용자가 작성한 리뷰로 수정이 불가합니다.");
		}

		Review updatedReview = originReview.update(issueEventDto.getContent(),
			attachedPhotoRepository.findAllById(issueEventDto.getAttachedPhotoIds().stream()
				.map(UUID::fromString)
				.collect(Collectors.toList())));

		issueUpdatePoint(user, originReview, updatedReview);

		reviewRepository.save(updatedReview);

		return (T)updatedReview;
	}

	@Transactional
	@Override
	public void deleteEvent(RequestRegisterEventDto issueEventDto, User user) {
		Review review = reviewRepository.findById(UUID.fromString(issueEventDto.getReviewId()))
			.orElseThrow(() -> new IllegalArgumentException("요청하신 리뷰를 찾을 수 없습니다."));

		reviewRepository.save(review.delete());

		pointService.deletePoint(
			EventType.REVIEW,
			review.getId().toString(),
			PointType.REVIEW_DELETE,
			user);
	}

	private void issuePoint(Place place, User user, Review review) {
		if (ReviewPointCalculator.calculate(review) != 0) {
			pointService.registerPoint(
				EventType.REVIEW,
				review.getId().toString(),
				PointType.REVIEW_WHITE,
				ReviewPointCalculator.calculate(review),
				user);
		}

		if (ReviewPointCalculator.calculateBonus(true) != 0 && isFirstReviewByPlace(place)) {
			pointService.registerPoint(
				EventType.REVIEW,
				review.getId().toString(),
				PointType.BONUS_FIRST_REVIEW,
				ReviewPointCalculator.calculateBonus(true),
				user);
		}
	}

	private void issueUpdatePoint(User user, Review originReview, Review updatedReview) {
		long defaultPointAmount = ReviewPointCalculator.calculate(originReview, updatedReview);

		if (defaultPointAmount == 0) {
			return;
		}

		pointService.registerPoint(
			EventType.REVIEW,
			originReview.getId().toString(),
			PointType.REVIEW_MODIFY,
			defaultPointAmount,
			user);
	}

	private boolean isFirstReviewByPlace(Place place) {
		return !reviewRepository.existsReviewsByPlaceIdAndIsDeletedFalse(place.getId());
	}
}
