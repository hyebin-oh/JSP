package com.board.model;

import java.util.ArrayList;

public interface BoardDAO {
	//추가
	public void boardSave(BoardDTO board);
	//전체보기
	public ArrayList<BoardDTO> boardList(String field, String word,int startRow, int endRow);
	//상세보기
	public BoardDTO boardfindById(int num);
	//수정
	public void boardUpdate(BoardDTO board);
	//삭제
	public int boardDelete(int num);
	//갯수-검색x
	public int boardCount();
	//갯수-검색
	public int boardCount(String field, String word);

}