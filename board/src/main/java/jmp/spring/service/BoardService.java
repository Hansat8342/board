package jmp.spring.service;

import java.util.List;

import jmp.spring.vo.BoardVO;

public interface BoardService {

	public List<BoardVO> getList();
	
	public int insertBoard(BoardVO vo);
	
	public BoardVO get(int bno);
	
	public int BoardUpdate(BoardVO vo);
}
