package com.mycom.myapp.event.dao;

import java.util.List;

import com.mycom.myapp.event.dto.EventDto;

public interface EventDao {
	int registerEvent (EventDto eventDto);
	List<EventDto> getEventList();
	int updateEvent (EventDto eventDto);
	int deleteEvent (int key);
}
