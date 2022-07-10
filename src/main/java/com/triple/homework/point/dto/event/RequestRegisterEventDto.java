package com.triple.homework.point.dto.event;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Data
public class RequestRegisterEventDto {
	private String type;
	private String action;
	private String content;
	@JsonDeserialize
	private List<String> attachedPhotoIds;
	private String userId;
	private String placeId;
	private String reviewId;

	@Builder
	public RequestRegisterEventDto(String type, String action, String content, List<String> attachedPhotoIds,
		String userId,
		String placeId, String reviewId) {
		this.type = type;
		this.action = action;
		this.content = content;
		this.attachedPhotoIds = attachedPhotoIds;
		this.userId = userId;
		this.placeId = placeId;
		this.reviewId = reviewId;
	}
}
