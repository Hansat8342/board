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
	// 파라메터가 2개 이상인 경우!!!
	// @Param 어노테이션을 꼭 작성 해줘야 합니다.
	public List<ReplyVo> getList(@Param("bno") int bno,
								 @Param("cri") Criteria cri);
	public int getTotal(int bno);
	
	// 게시글의 댓글수를 카운트해서 replycnt컬럼에 입력
	public int updateReplyCnt(int bno); 
}





