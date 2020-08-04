package com.cde.ims.application.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import com.cde.ims.application.config.AWSSQSConfiguration;

@Service
public class SQSService {
	Logger logger = LoggerFactory.getLogger(SQSService.class);

	private final AWSSQSConfiguration awssqsConfig;

	@Value("${aws.sqs.url}")
	private final String sqsUrl;

	@Autowired
	public SQSService(final AWSSQSConfiguration awssqsConfig, final @Value("${aws.sqs.url}") String sqsUrl) {
		this.awssqsConfig = awssqsConfig;
		this.sqsUrl = sqsUrl;
	}

	public void sendMessage() {
		SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(sqsUrl).withMessageBody("Hello Queue")
				.withDelaySeconds(30);
		awssqsConfig.amazonSQSAsync().sendMessage(sendMessageRequest);

	}
	
	public void getMessage() {
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsUrl).withMaxNumberOfMessages(1).withWaitTimeSeconds(3);
		List<Message> messages = awssqsConfig.amazonSQSAsync().receiveMessage(receiveMessageRequest).getMessages();
		logger.info("Message from SQS Queue are {}", messages.size());
	}
	
}
