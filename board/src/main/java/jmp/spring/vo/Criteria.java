package jmp.spring.vo;

import lombok.Data;

@Data
public class Criteria {
	int pageNo;
	int amount;
	
	//검색타입, 키워드
	String type;
	String keyword;
	
	// 초기화
	public Criteria() {
		this.pageNo=1;
		this.amount=10;
	}
	
	public Criteria(int pageNo, int amount) {
		this.pageNo = pageNo;
		this.amount = amount;
	}
	
	public String[] getTupeArr() {
		return type==null?new String[] {}: type.split("");
	}
	
}
