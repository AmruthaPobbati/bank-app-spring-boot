package com.javaexpress.loans.exceptions;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorAPI {
	private Integer statusCode;
	private String title;
	private String status;
	private String details;
	private LocalDateTime currentTime;
}
