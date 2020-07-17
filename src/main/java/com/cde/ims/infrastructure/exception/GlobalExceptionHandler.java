package com.cde.ims.infrastructure.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cde.ims.domain.model.ErrorInformation;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> orderNotFoundException(ProductNotFoundException ex) {
		ErrorInformation errorDetails = new ErrorInformation(new Date(), ex.message, null);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductExistException.class)
	public ResponseEntity<?> orderExistException(ProductExistException ex) {
		ErrorInformation errorDetails = new ErrorInformation(new Date(), ex.message, null);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorInformation errorDetails = new ErrorInformation(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
