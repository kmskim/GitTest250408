package com.itwillbs.domain;

// 화원 관련 데이터를 저장해서 잔달
public class MemberDTO {
//	객체지향개념 특징 : 데이터 은닉(캡술화)
//	멤버변수(기억장소) : 외부에서 접근하지 못하게 막아줌(데이터 은닉, 접근 제어자 private)
	private String id;
	private String passwd;
	private String name;
	
//	alt shift s > tostring
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", passwd=" + passwd + ", name=" + name + "]";
	}
	
	
//	생성자
//	메서드() : 외부에서 멤버변수에 접근할 수 있도록 setter, getter 메서드 정의 
//	alt shift s => r
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
	
	
	
	
}
