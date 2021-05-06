package jmp.spring.vo;

import java.util.UUID;

import lombok.Data;

@Data
public class AttachFileVo {

	// 첨부파일 번호
	private int attachNo;
	// 중복처리를 위한 랜덤값 UUID
	private String uuid;
	// 업로드 경로 (년/월/일)
	private String uploadPath;
	// 파일명
	private String fileName;
	// 이미지 구분 (이미지=Y/아니면=N)
	private String fileType;
	// 저장된 날짜
	private String regdate;
	
	public AttachFileVo(int attachNo, String uploadPath, String fileName) {
		
		UUID uuid = UUID.randomUUID();
		this.uuid=uuid.toString();
		this.fileType = "N";
		
		this.attachNo = attachNo;
		this.uploadPath = uploadPath;
		this.fileName = fileName;
		this.savePath = uploadPath + uuid + "_" + fileName;
		this.s_savePath = uploadPath + "s_" + uuid + "_" + fileName;
		
	}
	// 파일 전체 경로
	private String savePath;
	// 썸네일 전체 경로
	private String s_savePath;
	
}
