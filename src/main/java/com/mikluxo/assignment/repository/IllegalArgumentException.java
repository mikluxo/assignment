package com.mikluxo.assignment.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Incorrect data provided")
public class IllegalArgumentException extends RuntimeException {
}
