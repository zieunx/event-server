package com.triple.homework.point.domain;

import java.util.Collections;

import com.triple.homework.point.dto.event.RequestRegisterEventDto;
import com.triple.homework.point.dto.event.RequestRegisterEventDto.RequestRegisterEventDtoBuilder;

public class Fixtures {
	public static RequestRegisterEventDtoBuilder aRequestRegisterEventDto() {
		return RequestRegisterEventDto.builder()
			.type("REVIEW")
			.action("ADD")
			.content("리뷰등록")
			.placeId("5b5435a1-d786-4f3a-9144-44fa9102d93f")
			.userId("f8f5f991-b3e1-4f37-95c7-5353426e11a8")
			.attachedPhotoIds(Collections.emptyList());
	}
}
