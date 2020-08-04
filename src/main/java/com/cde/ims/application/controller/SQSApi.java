package com.cde.ims.application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "Inventory Management System")
@RequestMapping("/api/v1")
public interface SQSApi {
	
	@ApiOperation(value = "Send message to sqs", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully message sent") })
	@PostMapping("/sendMessage")
	String sendMessage();
	
	@ApiOperation(value = "Message retrieved from sqs", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully message retrieved") })
	@PostMapping("/getmessage")
	String getMessage();

	
}
