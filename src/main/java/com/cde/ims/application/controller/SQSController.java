package com.cde.ims.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cde.ims.application.service.SQSService;

@RestController
public class SQSController implements SQSApi {

	private final SQSService sqsService;

	@Autowired
	public SQSController(final SQSService sqsService) {
		this.sqsService = sqsService;
	}

	@Override
	public String sendMessage() {
		sqsService.sendMessage();
		return "message sent";
	}

	@Override
	public String getMessage() {
		sqsService.getMessage();
		return null;
	}


}
