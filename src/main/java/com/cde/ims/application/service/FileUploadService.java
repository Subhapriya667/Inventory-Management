package com.cde.ims.application.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class FileUploadService {

	Logger logger = LoggerFactory.getLogger(FileUploadService.class);

	private final AmazonS3 amazonS3;

	private final String bucketName;

	@Autowired
	public FileUploadService(final AmazonS3 amazonS3, final @Value("${aws.s3.bucket}") String bucketName) {
		this.amazonS3 = amazonS3;
		this.bucketName = bucketName;
	}

	public void uploadFile(MultipartFile multipartFile) {
		logger.info("File Starts Uploading...");
		try {
			File file = convertMultiPartFileToFile(multipartFile);
			uploadFileToS3Bucket(bucketName, file);
			logger.info("File upload is completed.");
		} catch (AmazonServiceException ex) {
			logger.info("File upload is failed.");
			logger.error("Error= {} while uploading file.", ex.getMessage());
		}
	}

	private File convertMultiPartFileToFile(MultipartFile multipartFile) {
		File file = new File(multipartFile.getOriginalFilename());
		try (FileOutputStream outputStream = new FileOutputStream(file);
				BufferedOutputStream bout = new BufferedOutputStream(outputStream);) {
			bout.write(multipartFile.getBytes());
		} catch (IOException ex) {
			logger.error("Error converting the multi-part file to file= {}", ex.getMessage());
		}
		return file;
	}

	private void uploadFileToS3Bucket(String bucketName, File file) {
		String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
		amazonS3.putObject(putObjectRequest);
	}

}
