package com.triple.homework.point.domain.review;

public class ReviewPointCalculator {
	public static long calculate(Review review) {
		int addPoint = 0;
		if (review.isWrittenContent()) {
			addPoint += 1;
		}
		if (review.isAttachedPhoto()) {
			addPoint += 1;
		}
		return addPoint;
	}

	public static long calculate(Review origin, Review target) {
		int point = 0;
		if (origin.isWrittenContent() && !target.isWrittenContent()) {
			point -= 1;
		} else if (!origin.isWrittenContent() && target.isWrittenContent()) {
			point += 1;
		}

		if (origin.isAttachedPhoto() && !target.isAttachedPhoto()) {
			point -= 1;
		} else if (!origin.isAttachedPhoto() && target.isAttachedPhoto()) {
			point += 1;
		}

		return point;
	}

	public static long calculateBonus(boolean isFirstReviewByPlace) {
		int addPoint = 0;
		if (isFirstReviewByPlace) {
			addPoint += 1;
		}
		return addPoint;
	}
}
