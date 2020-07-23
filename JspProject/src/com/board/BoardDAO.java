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
	public void boardInsert(BoardVO bo) {//새글 답글 구분
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";
		
		//부모글
		int num= bo.getNum();
		int ref= bo.getRef();
		int re_step=bo.getRe_step();
		int re_level=bo.getRe_level();
		
		int number=0;
		
		try {
			con=getConnection();
			ps=con.prepareStatement("select max(num) from board");
			rs=ps.executeQuery();
			if(rs.next()) {//기존 데이터가 있을 때 ref를 최대값+1로 결정
				number=rs.getInt(1)+1;
			}else {//기존 데이터가 없을 때 ref를 1로 결정
				number=1;
			}
			if(num!=0) {//답글
				sql="update board set re_step=re_step+1 where ref=? and re_step>?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, ref);
				ps.setInt(2, re_step);
				ps.executeUpdate();
				re_step=re_step+1;
				re_level=re_level+1;				
				
			}else {//새글
				ref=number;
				re_step=0;
				re_level=0;
			}			
			
			//num, writer, subject, email, content,ip, readcount, ref, re_step,re_level, passwd,reg_date
			sql="insert into board(num, writer, subject, email, content, ip, readcount, ref, re_step,re_level, passwd) values (board_seq.nextval,?,?,?,?,?,0,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, bo.getWriter());
			ps.setString(2, bo.getSubject());
			ps.setString(3, bo.getEmail());
			ps.setString(4, bo.getContent());
			ps.setString(5, bo.getIp());
			ps.setInt(6, ref);
			ps.setInt(7, re_step);
			ps.setInt(8, re_level);	
			ps.setString(9, bo.getPasswd());
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
	}
	
	
	//전체보기-검색아님
	public ArrayList<BoardVO> boardList(int startRow, int endRow){
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<BoardVO> arr = new ArrayList<BoardVO>();
		
		try {
			con=getConnection();
			String sql = "select * from ("
							+ "select rownum rn, aa.* from ("
								+ "select * from board order by ref desc, re_step asc) aa"
								+ ") where rn <=? and rn >=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, endRow );
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			
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
			closeStatement(con, ps, rs);
		}		
		return arr;
	}
	
	
	//전체보기-검색
	public ArrayList<BoardVO> boardList(String field, String word, int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BoardVO> arr = new ArrayList<BoardVO>();
		
		try {
			con=getConnection();
			String sql = "select * from ("
					+ "select rownum rn, aa.* from ("
						+ "select * from board where "+ field+ " like '%"+word+"%' order by ref desc, re_step asc) aa"
						+ ") where rn <=? and rn >=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, endRow );
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			
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
			closeStatement(con, ps, rs);
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
			st=con.createStatement();
			st.executeUpdate("update board set readcount=readcount+1 where num="+num);
			String sql="select * from board where num="+num;
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
	
		
	//comment
	
	//commentInsert
	public void commentInsert(CommentVO cvo) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=getConnection();
			String sql="insert into commentboard(cnum, userid, regdate, msg, bnum) values(comment_seq.nextval,?,sysdate,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, cvo.getUserid());
			ps.setString(2, cvo.getMsg());
			ps.setInt(3, cvo.getBnum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		
	}
	
	
	public ArrayList<CommentVO> commentList(int num){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<CommentVO> arr = new ArrayList<>();
		
		try {
			con=getConnection();
			String sql="select * from commentboard where bnum= "+num+" order by cnum desc";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				CommentVO cvo = new CommentVO();
				cvo.setCnum(rs.getInt("cnum"));
				cvo.setUserid(rs.getString("userid"));
				cvo.setRegdate(rs.getString("regdate"));
				cvo.setMsg(rs.getString("msg"));
				cvo.setBnum(rs.getInt("bnum"));
				arr.add(cvo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeStatement(con, st, rs);
		}
		return arr;		
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
