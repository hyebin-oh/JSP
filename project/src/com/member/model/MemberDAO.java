package com.member.model;

import java.util.ArrayList;


public interface MemberDAO {
	//�߰�
	public void memberInsert(MemberDTO vo);
	
	//��ü����
	public ArrayList<MemberDTO> memberList();
	
	//�����ϱ�
	public int memberUpdate(MemberDTO vo);
	
	//�󼼺���
	public MemberDTO memberView(String userid);
	
	//�����ϱ�
	public void memberDel(String userid);
	
	//���̵� üũ(�ߺ�üũ)
	public String idCheck(String userid);
	
	public int loginCheck(String userid, String pwd);
	
	//ȸ����
	public int memberCount();
}
