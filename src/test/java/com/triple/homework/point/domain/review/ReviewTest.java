package com.triple.homework.point.domain.review;

import java.util.List;

import com.triple.homework.point.domain.entity.PlaceTest;
import com.triple.homework.point.domain.entity.UserTest;

public class ReviewTest {
	public static Review NO_CONTENT_NO_PHOTO_REVIEW = Review.builder()
		.user(UserTest.USER_1)
		.place(PlaceTest.PLACE_1)
		.build();

	public static Review ONLY_ONE_PHOTO_REVIEW = Review.builder()
		.user(UserTest.USER_1)
		.place(PlaceTest.PLACE_1)
		.attachedPhotos(List.of(AttachedPhotoTest.ATTACHED_PHOTO_1))
		.build();

	public static Review ONLY_CONTENT_REVIEW = Review.builder()
		.content("1")
		.user(UserTest.USER_1)
		.place(PlaceTest.PLACE_1)
		.build();
	public static Review CONTENT_AND_ONE_PHOTO_REVIEW = Review.builder()
		.content("1")
		.user(UserTest.USER_1)
		.place(PlaceTest.PLACE_1)
		.attachedPhotos(List.of(AttachedPhotoTest.ATTACHED_PHOTO_1))
		.build();

	public static Review THREE_PHOTO_REVIEW = Review.builder()
		.content("만족해요")
		.user(UserTest.USER_1)
		.place(PlaceTest.PLACE_1)
		.attachedPhotos(List.of(AttachedPhotoTest.ATTACHED_PHOTO_1))
		.build();
}