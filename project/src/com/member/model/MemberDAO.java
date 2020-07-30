package com.member.model;

import java.util.ArrayList;


public interface MemberDAO {
	//추가
	public void memberInsert(MemberDTO vo);
	
	//전체보기
	public ArrayList<MemberDTO> memberList();
	
	//수정하기
	public int memberUpdate(MemberDTO vo);
	
	//상세보기
	public MemberDTO memberView(String userid);
	
	//삭제하기
	public void memberDel(String userid);
	
	//아이디 체크(중복체크)
	public String idCheck(String userid);
	
	public int loginCheck(String userid, String pwd);
	
	//회원수
	public int memberCount();
}
