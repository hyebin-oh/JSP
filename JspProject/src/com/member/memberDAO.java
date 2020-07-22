package com.member;

import java.util.ArrayList;

public interface memberDAO {
	//추가
	public void memberInsert(MemberVO vo);
	
	//전체보기
	public ArrayList<MemberVO> memberList();
	
	//수정하기
	public int memberUpdate(MemberVO vo);
	
	//상세보기
	public MemberVO memberView(String userid);
	
	//삭제하기
	public void memberDel(String userid);
	
	//아이디 체크(중복체크)
	public String idCheck(String userid);
	
	public int loginCheck(String userid, String pwd);
	
	//회원수
	public int memberCount();
}
