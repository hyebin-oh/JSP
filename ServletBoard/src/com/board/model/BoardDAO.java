package com.board.model;

import java.util.ArrayList;

public interface BoardDAO {
	//추가
	public void boardSave(BoardDTO board);
	
	//전체보기
	public ArrayList<BoardDTO> boardList();
	
	//상세보기
	public BoardDTO boardFindById(int num);
	
	//수정하기
	public void boardUpdate(BoardDTO board);
	
	//삭제하기
	public void boardDelete(int num);
}
