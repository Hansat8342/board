package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jmp.spring.mapper.ReplyMapper;
import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper mapper;
	
	@Override
	@Transactional
	public int insert(ReplyVo vo) {
		// 댓글 입력
		int res = mapper.insert(vo);
		// 댓글의 수를 카운팅 해서 board테이블의 replyCnt컬럼에 입력
		mapper.updateReplyCnt(vo.getBno());
		
		return res;
	}

	@Override
	public int update(ReplyVo vo) {
		int res = mapper.update(vo);
		
		// 댓글수를 카운트 하여 테이블에 저장
		mapper.updateReplyCnt(vo.getBno());
		return res;
	}

	@Override
	@Transactional
	public int delete(int rno) {
		
		// 댓글 삭제시 댓글의 수가 변경됨 -> 댓글 수를 다시 카운트
		// reply 상세 정보 조회
		// 게시글 번호를 조회 !!! 
		// 리플을 삭제하기 전에 게시글 번호를 먼저 조회 한다!!!
		ReplyVo vo = mapper.get(rno);

		int res = mapper.delete(rno);
		
		
		
		// 상세정보의 게시글 번호에 해당 하는 리플의 수를 카운트
		mapper.updateReplyCnt(vo.getBno());
		
		return res;
	}

	@Override
	public ReplyVo get(int rno) {
		// TODO Auto-generated method stub
		return mapper.get(rno);
	}

	@Override
	public List<ReplyVo> getList(int bno, Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getList(bno, cri);
	}

	@Override
	public int getTotal(int bno) {
		// TODO Auto-generated method stub
		return mapper.getTotal(bno);
	}

}
