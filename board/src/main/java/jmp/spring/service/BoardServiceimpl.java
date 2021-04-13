package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.BoardMapper;
import jmp.spring.vo.BoardVO;

@Service
public class BoardServiceimpl implements BoardService{

	@Autowired // 중요. 오토와이어드 안쓰면 널포인트
	BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}

	@Override
	public int insertBoard(BoardVO vo) {
		return mapper.insertBoard(vo);
	}

}
