package com.cde.ims.application.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;

@SpringBootTest
@RunWith(PowerMockRunner.class)
public class FileUploadServiceTest {

	@InjectMocks
	FileUploadService fileUploadService;

	@Mock
	AmazonS3 amazonS3;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		fileUploadService = new FileUploadService(amazonS3, "A");
	}

	@Test
	public void fileUploadTest() throws IOException {
		Path path = Paths.get("src/test/resources/input.txt");
		MultipartFile multipartFile = new MockMultipartFile("file", "input.txt", "text/plain",
				Files.readAllBytes(path));
		PutObjectResult value = new PutObjectResult();
		Mockito.when(amazonS3.putObject(Mockito.anyString(), Mockito.anyString(), Mockito.any(File.class)))
				.thenReturn(value);
		fileUploadService.uploadFile(multipartFile);
	}
	
}
