package com.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.memberDAOImpl;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class BoardDAO {

	//디비셋팅
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstace() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}
	
	
	//추가
	public void boardInsert(BoardVO bo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			//writer, email, content,passwd, num, subject, ip
			String sql="insert into board(num, writer, subject, email, content, passwd, ip) values (board_seq.nextval,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, bo.getWriter());
			ps.setString(2, bo.getSubject());
			ps.setString(3, bo.getEmail());
			ps.setString(4, bo.getContent());
			ps.setString(5, bo.getPasswd());
			ps.setString(6,  bo.getIp());
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
	}
	
	
	//전체보기-검색아님
	public ArrayList<BoardVO> boardList(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<BoardVO> arr = new ArrayList<BoardVO>();
		
		try {
			con=getConnection();
			String sql = "select * from board";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setSubject(rs.getString("subject"));
				vo.setWriter(rs.getString("writer"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setIp(rs.getString("ip"));
				arr.add(vo);
			}			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}		
		return arr;
	}
	
	
	//전체보기-검색
	public ArrayList<BoardVO> boardList(String field, String word){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<BoardVO> arr = new ArrayList<BoardVO>();
		
		try {
			con=getConnection();
			String sql = "select * from board where "+ field+ " like '%"+word+"%'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setSubject(rs.getString("subject"));
				vo.setWriter(rs.getString("writer"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setIp(rs.getString("ip"));
				arr.add(vo);
			}			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}		
		return arr;
	}
	
	
	//상세보기
	public BoardVO boardView(int num) {
		Connection con=null;
		Statement st = null;
		ResultSet rs = null;
		BoardVO board=null;
		
		try {
			con=getConnection();
			String sql="select * from board where num="+num;
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				board=new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setIp(rs.getString("ip"));
				board.setPasswd(rs.getString("passwd"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setReg_date(rs.getString("reg_date"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;	
	}
	
	
	//수정
	public int boardUpdate(BoardVO board) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		int flag=0;
		String sql="";
		
		try {
			con=getConnection();
			sql="select passwd from board where num="+board.getNum();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getString("passwd").equals(board.getPasswd())){//비번일치
					sql="update board set subject=?, email=?, content=?, reg_date=sysdate where num=?";
					ps=con.prepareStatement(sql);	
					ps.setString(1, board.getSubject());
					ps.setString(2, board.getEmail());
					ps.setString(3, board.getContent());
					ps.setInt(4, board.getNum());			
					flag=ps.executeUpdate(); //업데이트된 수를 flag에 담는다
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	finally {
			closeConnection(con, ps);
		}
		return flag;
	}
	
	
	//삭제
	public int boardDelete(int num) {
		Connection con=null;
		Statement st=null;
		int flag=0;
		
		try {
			con=getConnection();
			String sql="delete from board where num='"+num+"'";
			st=con.createStatement();
			flag=st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeStatement(con, st, null);
		}
		return flag;
	}
	
	
	//개수-검색아님
	public int boardCount(){
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="select count(*) from board";
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}		
		return count;
	}
	
	//개수-검색
		public int boardCount(String field, String word){
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			int count=0;
			
			try {
				con=getConnection();
				st=con.createStatement();
				String sql="select count(*) from board where "+ field+ " like '%"+word+"%'";
				rs=st.executeQuery(sql);
				
				if(rs.next()) {
					count=rs.getInt(1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeStatement(con, st, rs);
			}		
			return count;
		}
	
	
	//닫기
	private void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if(con!=null) con.close();
			if(ps!=null) ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void closeStatement(Connection con, Statement st, ResultSet rs) {
		try {
			if(con!=null) con.close();
			if(st!=null) st.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
