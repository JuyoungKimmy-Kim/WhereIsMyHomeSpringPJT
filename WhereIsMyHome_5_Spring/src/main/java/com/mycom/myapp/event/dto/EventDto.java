package com.mycom.myapp.event.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EventDto {
	private int key;
	private String name;
	private String from;
	private String to;
	private int state;

}
