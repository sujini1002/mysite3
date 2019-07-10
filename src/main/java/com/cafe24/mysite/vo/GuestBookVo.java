package com.cafe24.mysite.vo;

public class GuestBookVo {
	private Long no;
	private String name;
	private String password;
	private String contents;
	private String reg_date;

	
	public GuestBookVo(Long no,String name,String password,String contents,String reg_date) {
		this.no = no;
		this.name= name;
		this.password = password;
		this.contents = contents;
		this.reg_date = reg_date;
	}
	public GuestBookVo() {
		
	}
	
	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", password=" + password + ", contents=" + contents
				+ ", reg_date=" + reg_date + "]";
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public Object getNo2() {
		return null;
	}
}
