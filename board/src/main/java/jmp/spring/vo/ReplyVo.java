package jmp.spring.vo;

import lombok.Data;

@Data
public class ReplyVo {

	// 리플번호
	int rno;
	// 게시물 번호
	int bno;
	// 리플
	String reply;
	// 작성자
	String replyer;
	// 작성시간
	String replydate;
	// 수정시간
	String updatedate;
	
}
