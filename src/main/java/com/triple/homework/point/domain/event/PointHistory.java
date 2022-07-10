package com.triple.homework.point.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import com.triple.homework.point.domain.BaseEntity;
import com.triple.homework.point.domain.user.User;
import com.triple.homework.point.util.type.EventType;
import com.triple.homework.point.util.type.PointState;
import com.triple.homework.point.util.type.PointType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class PointHistory extends BaseEntity {
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "point_history_id", columnDefinition = "BINARY(16)")
	private UUID id;

	@Enumerated(EnumType.STRING)
	@Column
	private EventType eventType; // ex. REVIEW ..

	@Column
	private String eventTargetId;

	@Enumerated(EnumType.STRING)
	@Column
	private PointType pointType;

	@Enumerated(EnumType.STRING)
	@Column
	private PointState state; // 적립,회수

	@Column
	private long amount;

	@Column(name = "original_point_id", columnDefinition = "BINARY(16)")
	private UUID originalPointId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "user_id", columnDefinition = "BINARY(16)")
	private User user;

	private static void validateUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("포인트생성 시, 사용자는 필수값 입니다.");
		}
	}

	@Builder
	public PointHistory(UUID id, EventType eventType, String eventTargetId, PointType pointType, PointState state, UUID originalPointId, long amount, User user) {
		validateUser(user);
		this.id = id == null ? UUID.randomUUID() : id;
		this.eventType = eventType;
		this.eventTargetId = eventTargetId;
		this.pointType = pointType;
		this.state = state;
		this.amount = amount;
		this.user = user;
		this.originalPointId = originalPointId == null ? this.id : originalPointId;
	}
}
