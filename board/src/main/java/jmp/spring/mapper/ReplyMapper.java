package jmp.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;

public interface ReplyMapper {
	
	public int insert(ReplyVo vo);
	public int update(ReplyVo vo);
	public int delete(int rno);
	public ReplyVo get(int rno);
	public List<ReplyVo> getList(@Param("bno") int bno, @Param("cri") Criteria cri);
	public int getTotal(int bno);
}
