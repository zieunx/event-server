package com.triple.homework.point.service.event;

import com.triple.homework.point.domain.user.User;
import com.triple.homework.point.dto.event.RequestRegisterEventDto;

public interface EventService<T> {
	T addEvent(RequestRegisterEventDto issueEventDto, User user);
	T modifyEvent(RequestRegisterEventDto issueEventDto, User user);
	void deleteEvent(RequestRegisterEventDto issueEventDto, User user);
}
