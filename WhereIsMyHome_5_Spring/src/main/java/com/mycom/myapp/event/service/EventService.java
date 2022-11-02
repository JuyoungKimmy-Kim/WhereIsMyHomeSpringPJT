package com.mycom.myapp.event.service;

import java.util.List;

import com.mycom.myapp.event.dto.EventDto;

public interface EventService {
	int registerEvent (EventDto eventDto);
	List<EventDto> getEventList();
	int updateEvent (EventDto eventDto);
	int deleteEvent (int key);
}
