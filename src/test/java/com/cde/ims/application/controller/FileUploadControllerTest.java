package com.cde.ims.application.controller;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.cde.ims.application.service.FileUploadService;


@SpringBootTest
@RunWith(PowerMockRunner.class)
public class FileUploadControllerTest {

	@InjectMocks
	FileUploadController fileUploadController;

	@Mock
	FileUploadService fileUploadService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		fileUploadController = new FileUploadController(fileUploadService);
	}

	@Test
	public void fileUploadTest() throws IOException {
		Path path = Paths.get("src/test/resources/input.txt");
		MultipartFile multipartFile = new MockMultipartFile("file", "input.txt", "text/plain",
				Files.readAllBytes(path));
		fileUploadController.fileUpload(multipartFile);
		verify(fileUploadService, timeout(1)).uploadFile(multipartFile);
	}
	
}
