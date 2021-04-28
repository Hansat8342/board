package jmp.spring.vo;

import lombok.Data;

@Data
public class Criteria {
	// 페이지 번호
	int pageNo;
	// 페이지당 게시물수 
	int amount;
	// 검색 타입
	String type;
	// 검색 키워드
	String keyword;
	
	// 초기화
	public Criteria() {
		this.pageNo = 1;
		this.amount = 10;
	}

	public Criteria(int pageNo, int amount) {
		this.pageNo = pageNo;
		this.amount = amount;
	}
	
	
}




