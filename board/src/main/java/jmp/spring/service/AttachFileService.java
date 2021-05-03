package jmp.spring.service;

import java.util.List;

import jmp.spring.vo.AttachFileVo;

public interface AttachFileService {

	public int getSeq();
	public int insert(AttachFileVo vo);
	public List<AttachFileVo> getList(int attachNo);
}
