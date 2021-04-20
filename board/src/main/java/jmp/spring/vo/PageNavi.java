package jmp.spring.vo;

import lombok.Data;

@Data
public class PageNavi {
	int startPage;
	int endPage;
	boolean prev;
	boolean next;
	Criteria cri;
	
	public PageNavi(Criteria cri, int total) {
		endPage = (int)Math.ceil((cri.getPageNo()/10.0))*10;
		startPage = endPage-9;
		
		int realEndPage = (int)Math.ceil((total*1.0)/cri.getAmount());
		
		prev = startPage>1; // 이 조건에 해당하면 true 로 보여줌.
		next = realEndPage>endPage;
		endPage = endPage>realEndPage ? realEndPage : endPage; //삼항연산자 = 조건 ? 참일경우 : 거짓일경우;
	}
	
}
