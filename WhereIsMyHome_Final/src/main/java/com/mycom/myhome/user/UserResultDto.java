package com.mycom.myhome.user;

import com.mycom.myhome.Status;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResultDto {
	private Status result;
	private String message;
}
