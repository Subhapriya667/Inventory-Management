package com.cde.ims.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Inventory Management System" )
@RequestMapping("/api/v1")
public interface FileUploadApi {
	
	@ApiOperation(value = "Upload File to S3 Bucket", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully file uploaded in s3 bucket") })
	@PostMapping("/fileupload")
	ResponseEntity<String> fileUpload(
			@ApiParam(value = "file", required = true) @RequestPart(value = "file") MultipartFile multipartFile);

	

}
