package com.member;

import java.util.ArrayList;

public interface memberDAO {
	//�߰�
	public void memberInsert(MemberVO vo);
	
	//��ü����
	public ArrayList<MemberVO> memberList();
	
	//�����ϱ�
	public void memberUpdate(MemberVO vo);
	
	//�󼼺���
	public MemberVO memberView(String userid);
	
	//�����ϱ�
	public void memberDel(String userid);
	
	//���̵� üũ(�ߺ�üũ)
	public String idCheck(String userid);
}
