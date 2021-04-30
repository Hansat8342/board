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
	// 파라메터가 2개이상인 경우 @Param 어노테이션 작성
	public List<ReplyVo> getList(@Param("bno") int bno,
								 @Param("cri") Criteria cri);
	public int getTotal(int bno);
	
	public int updateReplyCnt(int bno);
}
