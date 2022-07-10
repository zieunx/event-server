package com.triple.homework.point.domain.review;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
	boolean existsReviewsByUserIdAndPlaceIdAndIsDeletedFalse(UUID userId, UUID placeId);

	boolean existsReviewsByPlaceIdAndIsDeletedFalse(UUID placeId);
}
