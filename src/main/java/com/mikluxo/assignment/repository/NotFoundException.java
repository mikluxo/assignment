package com.mikluxo.assignment.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "item is missing")
public class NotFoundException extends RuntimeException{

}
