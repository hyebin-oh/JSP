package com.board.model;

import java.util.ArrayList;

public interface BoardDAO {
	//�߰�
	public void boardSave(BoardDTO board);
	
	//��ü����
	public ArrayList<BoardDTO> boardList();
	
	//�󼼺���
	public BoardDTO boardFindById(int num);
	
	//�����ϱ�
	public void boardUpdate(BoardDTO board);
	
	//�����ϱ�
	public void boardDelete(int num);
}
