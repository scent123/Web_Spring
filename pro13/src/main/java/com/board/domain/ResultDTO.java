package com.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
	private boolean success;
	private String action;
	private String message;

	/*
	public ResultDTO() {
		this(false, null, null);
	}
	 */

	public ResultDTO(boolean success, String action) {
		this(success, action, null);
	}
}
