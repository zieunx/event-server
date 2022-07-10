package com.triple.homework.point.util.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EventType {
	REVIEW;

	@JsonCreator
	public static EventType from(String s) {
		return EventType.valueOf(s.toUpperCase());
	}
}
