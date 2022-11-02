package com.mycom.myapp.event.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.event.dto.EventDto;

@RestController
public class EventController {

	@GetMapping("/events")
	public Map<List<EventDto>> list(){
		
	}
	
}
