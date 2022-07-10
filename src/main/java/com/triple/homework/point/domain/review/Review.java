package com.triple.homework.point.domain.review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.triple.homework.point.domain.place.Place;
import com.triple.homework.point.domain.user.User;
import com.triple.homework.point.infra.converter.BooleanToYNConverter;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Review {
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "review_id", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column
	private String content;

	@Column
	private LocalDateTime createdDateTime;

	@Column
	private LocalDateTime updateDateTime;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="review_id")
	private List<AttachedPhoto> attachedPhotos = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "place_id")
	private Place place;

	@Convert(converter = BooleanToYNConverter.class)
	@Column
	private boolean isDeleted;

	public Review(Review review) {
		this.id = review.id;
		this.content = review.content;
		this.attachedPhotos = new ArrayList<>(review.attachedPhotos);
		this.user = review.user;
		this.place = review.place;
		this.createdDateTime = review.createdDateTime;
		this.updateDateTime = review.updateDateTime;
		this.isDeleted = review.isDeleted;
	}

	public Review(String content, User user, Place place, List<AttachedPhoto> attachedPhotos) {
		this(createId(), content, user, place, LocalDateTime.now(), null, false, attachedPhotos);
	}

	@Builder
	private Review(UUID id, String content, User user, Place place, LocalDateTime createdDateTime, LocalDateTime updateDateTime, boolean isDeleted, List<AttachedPhoto> attachedPhotos) {
		validateUser(user);
		validatePlace(place);
		this.id = id == null ? createId() : id;
		this.content = content;
		this.user = user;
		this.place = place;
		this.createdDateTime = createdDateTime;
		this.updateDateTime = updateDateTime;
		this.isDeleted = isDeleted;
		Optional.ofNullable(attachedPhotos).ifPresent(photos -> this.attachedPhotos.addAll(photos));
	}

	public static ReviewBuilder copyBuilder(Review review) {
		return Review.builder()
			.id(review.id)
			.content(review.content)
			.user(review.user)
			.place(review.place)
			.createdDateTime(review.createdDateTime)
			.updateDateTime(review.updateDateTime)
			.isDeleted(review.isDeleted);
	}

	private static void validatePlace(Place place) {
		if (place == null) {
			throw new IllegalArgumentException("리뷰 등록시 장소는 필수입니다.");
		}
	}

	private static void validateUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("리뷰 등록시 사용자는 필수입니다.");
		}
	}

	public static UUID createId() {
		return UUID.randomUUID();
	}

	public boolean isWrittenContent() {
		return content != null && !content.isBlank();
	}

	public boolean isAttachedPhoto() {
		return !attachedPhotos.isEmpty();
	}

	@Override
	public String toString() {
		return "Review{" +
			"id=" + id +
			", content='" + content + '\'' +
			", createdDateTime=" + createdDateTime +
			", updateDateTime=" + updateDateTime +
			", attachedPhotos=" + attachedPhotos +
			'}';
	}

	public Review delete() {
		return Review.copyBuilder(this)
			.isDeleted(true)
			.build();
	}

	public Review update(String content, List<AttachedPhoto> attachedPhotos) {
		return Review.copyBuilder(this)
			.updateDateTime(LocalDateTime.now())
			.content(content)
			.attachedPhotos(attachedPhotos)
			.build();
	}
}
