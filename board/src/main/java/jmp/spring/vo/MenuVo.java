package jmp.spring.vo;

import lombok.Data;

@Data
public class MenuVo {
	
	public int level;
	public String menu_id;
	public String title;
	public String url;
	public String up_menu_id;
	
}
