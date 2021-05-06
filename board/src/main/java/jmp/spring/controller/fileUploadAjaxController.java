package jmp.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private static final String ROOT_DIR = "c:\\upload\\";

	@Autowired
	public AttachFileService service;
	
	@GetMapping("/attachFileDelete/{uuid}/{attachNo}")
	public String delete(@PathVariable("uuid") String uuid,
						@PathVariable("attachNo") int attachNo) {
		
		log.info("============================");
		log.info("uuid : "+uuid);
		log.info("attachNo : "+attachNo);
		log.info("============================");
		
		//uuid, attachNo 저장된 파일의 정보를 조회
		AttachFileVo vo = service.get(uuid, attachNo);
		
		// 서버에 저장된 파일 삭제
		File file = new File(vo.getSavePath());
		if(file.exists())
			file.delete();
		
		//만약에 이미지 이면 서버에 저장된 이미지 파일의 썸네일도 삭제
		if(vo.getFileType() == "Y") {
			File sFile = new File(ROOT_DIR + vo.getS_savePath());
			if(sFile.exists())
				sFile.delete();
		}
		int res = service.delete(uuid, attachNo);
		
		return res>0?"success":"fail";
	}
	
	@GetMapping("/download")
	public ResponseEntity<byte[]> download(String fileName) {
		log.info("/display================fileName : " + fileName);
		
		// file 경로 = urlPath + uuid + _ + 파일이름
		// savePath
		File file = new File(ROOT_DIR+fileName);
		if(file.exists()) {
			//파일을 ResponseEntity에 담아서 변환
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
				headers.add("Content-Disposition", "attachment;fileName=\""+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")
							+"\"");
				return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			//파일 없음 처리
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
	 * 이미지 파일의 경로를 받아서 
	 * 이미지 파일 반환 합니다.
	 * @param fileName
	 */
	@GetMapping("/display")
	public  ResponseEntity<byte[]> display(String fileName) {
		// /display?fileName=다운로드.jpg
		// 해당 URL이 정상적으로 실행 되었다면 서버에 로그가 찍히겠죠!
		log.info("/display==============fileName : " + fileName);
		// fileName : uploadpath + uuid+ '_' + fileName

		
		HttpHeaders headers = new HttpHeaders();
		File file = new File(ROOT_DIR+fileName);
		
		// 파일이 존재여부 확인
		if(file.exists()) {
			try {
				headers.add("Content-Type", Files.probeContentType(file.toPath()));
				
				// ResponseEntity객체에 파일과 헤더를 담아서 브라우져로 리턴
				return new ResponseEntity<byte[]>(
						FileCopyUtils.copyToByteArray(file)
						, headers
						, HttpStatus.OK);
				
			} catch (IOException e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	/**
	 * 파일리스트를 조회 합니다
	 * @return List<AttachFileVo> 
	 */
	@GetMapping("/getFileList/{attachNo}")
	public List<AttachFileVo> 
				getList(@PathVariable("attachNo") int attachNo){
		return service.getList(attachNo);
	}
	
	@PostMapping("/fileUploadAjax")
	public Map<String, String> fileUpload(MultipartFile[] uploadFile, int attachNo) {
		
		// 신규 생성 이면 sequence값을 가져 옵니다.
		if(attachNo==0) {
			attachNo = service.getSeq();
		}
		
		int res = 0;
		for(MultipartFile file : uploadFile) {
			
			try {
				AttachFileVo vo = 
						new AttachFileVo(   attachNo, 
											getFolder(), // 업로드 경로 지정 ( 년\월\일 )
											file.getOriginalFilename());
				
				log.info("=======================" + vo);
				// 파일을 서버에 저장 해봅시다
				File saveFile = new File(ROOT_DIR+vo.getSavePath());
				
				// 서버에 파일을 생성 합니다.
				file.transferTo(saveFile);
				
				// Mime 타입을 스트링으로 받아옵니다.
				// Myme 타입을 모를경우 null을 반환 합니다. ex) xx.sql
				String contentType = Files.probeContentType(saveFile.toPath());
				log.info("==============contentType : "+contentType);
				if(contentType != null 
						&& contentType.startsWith("image")) {
					Thumbnails.of(saveFile).size(100, 100).toFile(ROOT_DIR+vo.getS_savePath());
					vo.setFileType("Y");
				}

				// 파일 정보를 DB에 저장 합니다.
				if(service.insert(vo)>0) {
					res++;
				}
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("attachNo", attachNo+"");
		map.put("result", res+"건 저장 되었습니다.");
		
		return map;
	}
	
	// 업로드 날짜를 디렉토리로 생성 해줍니다.
	// 년-월-일   -->>   '-'  -> '\' 
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 오늘 날짜를 년-월-일 형태의 스트링으로 생성
		String str = sdf.format(new Date());
		
		
		String uploadPath = str.replace("-", File.separator) + File.separator;
		log.info("============" + uploadPath);
		
		File saveFile = new File(ROOT_DIR + uploadPath);
		
		// 디렉토리가 있는 체크
		if(!saveFile.exists()) {
			// 디렉토리 생성
			saveFile.mkdirs();
		}
		
		return uploadPath;
	}
	
	
	
}













