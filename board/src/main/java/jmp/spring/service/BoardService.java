package jmp.spring.service;

import java.util.List;

import jmp.spring.vo.BoardVO;
import jmp.spring.vo.Criteria;

public interface BoardService {

	public List<BoardVO> getList(Criteria cri);
	
	public int insertBoard(BoardVO vo);
	
	public BoardVO get(int bno);
	
	public int update(BoardVO vo);
	
	public int delete(int bno);
	
	public int getTotal(Criteria cri);
}
