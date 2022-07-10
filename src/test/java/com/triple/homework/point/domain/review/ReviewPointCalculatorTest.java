package com.triple.homework.point.domain.review;

import static com.triple.homework.point.domain.review.ReviewTest.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReviewPointCalculatorTest {

	@Test
	void 리뷰등록_텍스트0자_사진X_0점() {
		assertThat(ReviewPointCalculator.calculate(NO_CONTENT_NO_PHOTO_REVIEW))
			.isEqualTo(0);
	}

	@Test
	void 리뷰등록_텍스트0자_사진포함_1점() {
		assertThat(ReviewPointCalculator.calculate(ONLY_ONE_PHOTO_REVIEW))
			.isEqualTo(1);
	}

	@Test
	void 리뷰등록_텍스트1자이상_사진X_1점() {
		assertThat(ReviewPointCalculator.calculate(ONLY_CONTENT_REVIEW))
			.isEqualTo(1);
	}

	@Test
	void 리뷰등록_텍스트1자이상_사진포함_2점() {
		assertThat(ReviewPointCalculator.calculate(CONTENT_AND_ONE_PHOTO_REVIEW))
			.isEqualTo(2);
	}
}