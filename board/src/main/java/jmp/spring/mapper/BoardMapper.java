package jmp.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import jmp.spring.vo.BoardVO;

public interface BoardMapper {
	
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2(); 
	
	//이중 셀렉트 주의 매퍼.xml에서 이미 셀렉트 해주고 있음.
	public List <BoardVO> getList();
	
	public int insertBoard(BoardVO vo);
	
	public BoardVO get(int bno);
	
	//업데이트 인서트 딜리트 = 인트 반환
	public int update(BoardVO vo);
}
