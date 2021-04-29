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
		// TODO Auto-generated method stub
		int res = mapper.insert(vo);
		// 댓글 갯수는 입력할때, 삭제할때 변경된다.
		// 댓글에 갯수를 카운트 해서 tbl_board 테이블의 replycnt 컬럼에 입력 update
		// 갯수가 변경되는  게시글의 번호는 vo.getBno();
		mapper.updateReplyCnt(vo.getBno());
		return res;
	}

	@Override
	public int update(ReplyVo vo) {
		// TODO Auto-generated method stub
		int res = mapper.update(vo);
		// 댓글수를 카운트 하여 테이블에 저장;
		mapper.updateReplyCnt(vo.getBno());
		return res;
	}

	@Override
	@Transactional
	public int delete(int rno) {
		// TODO Auto-generated method stub
		// 먼저 조회후 삭제
		ReplyVo vo = mapper.get(rno);
		
		int res = mapper.delete(rno);
		// 댓글 삭제시 댓글의 수가 변경됨 > 댓글 수를 다시 카운트
		// reply 상세 정보 획득
		
		// 상세정보의 bno 값을 획득
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
