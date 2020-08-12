package com.notice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.model.MemberDTO;

public class NoticeDAO {
	//디비연결
	private static NoticeDAO instance = new  NoticeDAO();
	public static  NoticeDAO  getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/project");
		return ds.getConnection();
	}
	
	//공지사항 등록
	public void noticeInsert(NoticeDTO notice) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con=getConnection();
			String sql = "insert into notice values(notice_seq.nextval,?,?,sysdate,0)";
			ps=con.prepareStatement(sql);
			ps.setString(1, notice.getNsubject());
			ps.setString(2, notice.getNcontent());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		
	}
	//공지사항 글 수
	public int noticeCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count=0;		
		
		try {
			con=getConnection();
			String sql = "select count(*) from notice";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return count;
	}
	
	//공지사항 전체보기
	public ArrayList<NoticeDTO> noticeList(int startRow, int endRow){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<NoticeDTO> arr = new ArrayList<>();
		
		try {
			con=getConnection();
			String sql="select * from notice order by nnum";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				NoticeDTO notice = new NoticeDTO();
				notice.setNcontent(rs.getString("ncontent"));
				notice.setNdate(rs.getString("ndate"));
				notice.setNnum(rs.getLong("nnum"));
				notice.setNsubject(rs.getString("nsubject"));
				notice.setNview(rs.getString("nview"));
				arr.add(notice);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		
		return arr;
	}
	
	//공지사항 상세보기
	public NoticeDTO noticeView(String nsubject) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		NoticeDTO notice = null;
		
		return notice;
	}
	
	//공지사항 수정하기
	public void noticeUpdate(NoticeDTO notice) {
		Connection con = null;
		PreparedStatement ps = null;
	}
	
	//공지사항 삭제하기
	public void notiecDelete(String nsubject) {
		Connection con = null;
		Statement st = null;
		
		try {
			con=getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//닫기
		private void closeConnection(Connection con, PreparedStatement ps) {
			try {
				if(ps!=null) ps.close();
				if(con!=null) con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		private void closeConnection(Connection con, Statement st, ResultSet rs) {
			try {
				if(rs!=null) rs.close();
				if(st!=null) st.close();
				if(con!=null) con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}	
}
