package com.triple.homework.point.util.type;

public enum PointType {
	REVIEW_WHITE("리뷰 작성"),
	REVIEW_MODIFY("리뷰 수정"),
	REVIEW_DELETE("리뷰 삭제"),
	BONUS_FIRST_REVIEW("첫 번째 리뷰"),
	;

	private final String title;

	PointType(String title) {
		this.title = title;
	}
}
