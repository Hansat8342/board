package jmp.spring.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class FileUploadController {

	@GetMapping("/board/fileUpload")
	public void fileUploadForm() {
		
	}
	
	@PostMapping("/uploadFormAction")
	public void fileUpload(MultipartFile[] uploadFile) { // 여러개의 파일이 넘어오기 때문에 배열로 받음.
		for (MultipartFile multipartFile : uploadFile) {
			
			File saveFile = new File(multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		  	log.info("Upload File Name: " +multipartFile.getOriginalFilename());
		  	log.info("Upload File Size: " +multipartFile.getSize());
			}

	}
}
