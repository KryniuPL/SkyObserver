package com.skyobserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No airports with given phrase")
public class AirportsNotFoundException extends RuntimeException {
}
