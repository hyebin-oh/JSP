package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAOImpl implements BoardDAO{

	//db연결
	private static BoardDAOImpl instance = new BoardDAOImpl();
	public static BoardDAOImpl getInstace() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/board");
		return ds.getConnection();
	}
	
	
	//추가하기(insert)
	public void boardSave(BoardDTO board) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql = "insert into tbl_board values(tbl_board_seq.nextval,?,?,?,sysdate,0)";
			ps=con.prepareStatement(sql);
			ps.setString(1,board.getWriter());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getSubject());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseConnection(con, ps, null);
		}

	}

	//전체보기
	public ArrayList<BoardDTO> boardList(String field, String word, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> arr = new ArrayList<BoardDTO>();
		String sql = "";
		
		try {
			con = getConnection();
			
			if(word.equals("")) {//검색아님
				sql = "select * from (select aa.*, rownum rn from "
						+ "(select * from tbl_board order by num desc) aa "
						+ "where rownum<=?) where rn>=?";
				
			}else {//검색포함
				sql =  "select * from (select aa.*, rownum rn from "
						+ "(select * from tbl_board where "+field+" like '%"+word+"%' order by num desc) aa "
						+ "where rownum<=?) where rn>=?";
			}
			ps = con.prepareStatement(sql);
			ps.setInt(1,  endRow);
			ps.setInt(2, startRow);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setContent(rs.getString("content"));
				board.setNum(rs.getInt("num"));
				board.setReadcount(rs.getShort("readcount"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setReg_date(rs.getString("reg_date"));
				arr.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseConnection(con, ps, rs);
		}		
		return arr;		
	}

	//상세보기
	public BoardDTO boardFindById(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		BoardDTO board = null;
		try {
			con=getConnection();
			st = con.createStatement();
			String sql = "select * from tbl_board where num="+num;
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				board = new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setContent(rs.getString("content"));
				board.setReadcount(rs.getInt("readcount"));
				board.setReg_date(rs.getString("reg_date"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseConnection(con, st, rs);
		}				
		return board;
	}

	@Override
	public void boardUpdate(BoardDTO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boardDelete(int num) {
		// TODO Auto-generated method stub
		
	}
	
	//갯수
	public int boardCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;		
		int count=0;
		String sql = "";
		
		try {
			con=getConnection();
			st=con.createStatement();
			if(word.equals("")) {
				sql="select count(*) from tbl_board";
			}else {
				sql = "select count(*) from tbl_board where "+field+" like '%"+word+"%'";
			}
			rs=st.executeQuery(sql);
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return count;
		
	}
	
	
	//닫기
		public void CloseConnection(Connection con, Statement st, ResultSet rs) {
			try {
				if(con!=null) con.close();
				if(st!=null) st.close();
				if(rs!=null) rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void CloseConnection(Connection con, PreparedStatement ps,ResultSet rs) {
			try {
				if(con!=null) con.close();
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		
		@Override // 자동생성, 구현X
		public ArrayList<BoardDTO> boardList() {
			// TODO Auto-generated method stub
			return null;
		}
	

}
