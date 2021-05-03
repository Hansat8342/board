package jmp.spring.vo;

import lombok.Data;

@Data
public class AttachFileVo {

	private int attachNo;
	private String uuid;
	private String uploadPath;
	private String fileName;
	private String fileType;
	private String regdate;
	
	private String savePath;
	private String s_savePath;
	
}
