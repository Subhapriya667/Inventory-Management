package com.cde.ims.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cde.ims.application.service.FileUploadService;

import io.swagger.annotations.ApiParam;

@RestController
public class FileUploadController implements FileUploadApi {
	Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	private final FileUploadService fileUploadService;

	@Autowired
	public FileUploadController(final FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@Override
	public ResponseEntity<String> fileUpload(@ApiParam(value = "file", required = true) @RequestPart(value = "file") MultipartFile multipartFile) {
		fileUploadService.uploadFile(multipartFile);
		return new ResponseEntity<>("Successfully File Uploaded In S3 Bucket", HttpStatus.OK);
	}
}
