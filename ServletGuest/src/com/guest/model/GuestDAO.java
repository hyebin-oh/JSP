package com.guest.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class GuestDAO {
	
	//디비 셋팅
	private static GuestDAO instance = new GuestDAO();
	public static GuestDAO getInstace() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/guest");
		return ds.getConnection();
	}
	
	//추가
	public void guestInsert(GuestDTO guest) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql = "insert into guestbook values(guestbook_seq.nextval,?,?,?,sysdate,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, guest.getName());
			ps.setString(2, guest.getContent());
			ps.setString(3, guest.getGrade());
			ps.setString(4, guest.getIpaddr());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			CloseConnection(con, ps, null);
		}
	}	
	
	
	//전체보기
	public ArrayList<GuestDTO> guestList(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<GuestDTO> arr = new ArrayList<GuestDTO>();
		
		try {
			con=getConnection();
			st=con.createStatement();
			String sql = "select * from guestbook";
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				GuestDTO guest = new GuestDTO();
				guest.setContent(rs.getString("content"));
				guest.setCreated(rs.getString("created"));
				guest.setGrade(rs.getString("grade"));
				guest.setIpaddr(rs.getString("ipaddr"));
				guest.setName(rs.getString("name"));
				guest.setNum(rs.getInt("num"));
				arr.add(guest);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseConnection(con, st, rs);
		}		
		return arr;
	}
	
	//전체보기 페이징
	public ArrayList<GuestDTO> guestList(int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<GuestDTO> arr = new ArrayList<GuestDTO>();
		
		try {
			con=getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select * from");
			sb.append(" (select aa.*, rownum rn from");
			sb.append(" (select * from guestbook order by num desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
//			String sql = "select * from (select rownum rn, aa.* from "
//													+ "(select * from guestbook) aa"
//														+ ") where rn<=? and rn >=?";
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				GuestDTO guest = new GuestDTO();
				guest.setContent(rs.getString("content"));
				guest.setCreated(rs.getString("created"));
				guest.setGrade(rs.getString("grade"));
				guest.setIpaddr(rs.getString("ipaddr"));
				guest.setName(rs.getString("name"));
				guest.setNum(rs.getInt("num"));
				arr.add(guest);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseConnection(con, ps, rs);
		}		
		return arr;
	}

	
	//상세보기
	public GuestDTO guestView(String name) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		GuestDTO guest = null;
		
		try {
			con=getConnection();
			st=con.createStatement();
			String sql = "select * from board where name="+name;
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				guest=new GuestDTO();
				guest.setNum(rs.getInt("num"));
				guest.setName(rs.getString("name"));
				guest.setContent(rs.getString("content"));
				guest.setGrade(rs.getString("grade"));
				guest.setCreated(rs.getString("created"));
				guest.setIpaddr(rs.getString("ipaddr"));
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			CloseConnection(con, st, rs);
		} return guest;
	}
	
	
	//개수
	public int guestCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count=0;
		
		try {
			con=getConnection();
			String sql = "select count(*) from guestbook";
			st=con.createStatement();
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
}
