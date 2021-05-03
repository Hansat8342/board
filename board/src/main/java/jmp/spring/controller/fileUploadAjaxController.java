package jmp.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jmp.spring.service.AttachFileService;
import jmp.spring.vo.AttachFileVo;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@RestController
@Log4j
public class fileUploadAjaxController {

	@Autowired
	public AttachFileService service;
	
	private static final String ROOT_DIR = "C:\\upload\\";
	@PostMapping("/fileUploadAjax")
	public List<AttachFileVo> fileUpload(MultipartFile[] uploadFile, int attachNo) { // 화면에서 넘버 받아옴
		
		log.info("========================="+attachNo);
		// 신규 생성된 파일의 경우 attachNo 를 생성합니다.
		if(attachNo==0) { // attachNo = 0 이면 파일을 처음 등록 하는겁니다.
			attachNo = service.getSeq();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("===================================");
			log.info(multipartFile.getOriginalFilename());
			log.info(multipartFile.getName());
			log.info(multipartFile.getSize());
			log.info("===================================");
			
			
			
			// 중복 방지를 위해 UUID 를 생성하여 파일명 앞에 붙여준다.
			// UUID : 고유 식별자 표준으로 표준에 따라 36개 문자 (8-4-4-4-12)를 생성 합니다.
			UUID uuid = UUID.randomUUID();
			
			String uploadPath=getFolder();
			
			String uploadFileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();
			
			// 파일용 서버에 저장해보기
			File saveFile = new File(ROOT_DIR + uploadPath + uploadFileName);
			
			try {
				//파일을 서버에 저장
				multipartFile.transferTo(saveFile);
				
				// 확장자를 이용하려 MimeType를 결정합니다.
				// 마임타입을 확인하지 못하면 null을 반환합니다.
				String contentType = Files.probeContentType(saveFile.toPath());
				
				// 이미지파일일 경우 썸네일을 생성
				if(contentType.startsWith("image")) {
					// 썸네일의 경로를 생성
					String thmbnail = ROOT_DIR + uploadPath + "s_" + uploadFileName;
					// 썸네일 생성
					Thumbnails.of(saveFile).size(100, 100).toFile(thmbnail);
				}
				AttachFileVo vo = new AttachFileVo();
				vo.setUuid(uuid.toString());
				vo.setAttachNo(attachNo);
				vo.setFileName(multipartFile.getOriginalFilename());
				vo.setFileType(contentType.startsWith("image")?"Y":"N");
				vo.setUploadPath(uploadPath);
				
				// 파일 정보를 DB에 저장하기
				service.insert(vo);
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		// attachNo에 해당하는 파일리스트를 조회하여 화면에 출력
		// 다시 조회하는 이유
		List<AttachFileVo> list = service.getList(attachNo);
		return list;
		
	}
	
	/*
	 * 중복 방지용
	 * 업로드 날짜를 업로드 경로로 지정합니다.
	 * 지정된 경로에 폴더가 존재하지 않으면 폴더를 생성 해줍니다.
	 * @return uploadPath 
	 */
	private String getFolder() {
		String uploadPath = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		
		// File.separator : 플랫폼에서 사용하는 디렉터리 구분자를 리턴
		uploadPath = str.replace("-", File.separator) + File.separator;
		
		File saveFile = new File(ROOT_DIR + uploadPath);
		
		if(!saveFile.exists()) {
			saveFile.mkdirs();
		}
		return uploadPath;
	}
}
