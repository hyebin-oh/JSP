package com.member.model;

import java.util.ArrayList;

public interface MemberDAO {

	//회원가입 추가
	public void memberJoin(MemberDTO member);
	
	//회원 전체보기
	public ArrayList<MemberDTO> memberList(String field, String word, int startRow, int endRow);
	
	//회원 상세보기
	public MemberDTO memberView(int num);
	
	//정보 수정하기
	public void memberUpdate(MemberDTO member);
	
	//회원 삭제하기
	public int memberDelete(int num);
	
	//전체 회원수
	public int memberCount(String field, String word);
}
