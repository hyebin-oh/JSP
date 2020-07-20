package com.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class memberDAOImpl implements memberDAO {
	
	private static memberDAOImpl instance = new memberDAOImpl();
	public static memberDAOImpl getInstace() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}

	@Override //추가하기
	public void memberInsert(MemberVO vo) {
		Connection con =null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql="insert into member values (?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getPwd());
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getPhone());
			ps.setInt(6, vo.getAdmin());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}

	@Override //전체보기
	public ArrayList<MemberVO> memberList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<MemberVO> arr = new ArrayList<MemberVO>();
		try {
			con=getConnection();
			String sql="select * from member";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				MemberVO mvo = new MemberVO();
				mvo.setName(rs.getString("name"));
				mvo.setUserid(rs.getString("userid"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setEmail(rs.getString("email"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setAdmin(rs.getInt("admin"));
				arr.add(mvo);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement(con, st, rs);
		}
		return arr;
	}

	@Override //수정하기
	public int memberUpdate(MemberVO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		int flag=0;
		
		try {
			con=getConnection();
			String sql="update member set name=?, email=?, phone=?, admin=? where userid=? ";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getEmail());
			ps.setString(3, vo.getPhone());
			ps.setInt(4, vo.getAdmin());
			ps.setString(5, vo.getUserid());
			flag=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		return flag;
	}

	@Override //상세보기
	public MemberVO memberView(String userid) {
		Connection con=null;
		Statement st =null;
		ResultSet rs = null;
		MemberVO member = null;
		
		try {
			con = getConnection();
			String sql = "select * from member where userid='" + userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				member=new MemberVO();
				member.setName(rs.getString("name"));
				member.setAdmin(rs.getInt("admin"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setPwd(rs.getString("pwd"));
				member.setUserid(rs.getString("userid"));
				member.setAdmin(rs.getInt("admin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}
		return member;
	}

	@Override //삭제하기
	public void memberDel(String userid) {
		Connection con = null;
		Statement st = null;
		
		try {
			con=getConnection();
			String sql = "delete from member where userid='" + userid+"'";
			st=con.createStatement();
			st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeStatement(con, st, null);
		}
		
	}

	@Override //아이디 체크(중복체크)
	public String idCheck(String userid) {
		Connection con=null;
		Statement st = null;
		ResultSet rs = null;
		String flag="yes";//사용가능
		try {
			con=getConnection();
			String sql = "select * from member where userid='"+userid+"'";
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				flag="no";//사용불가능
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}
		
		return flag;
	}
	
	@Override //로그인 체크
	public int loginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int flag=-1; //회원아님
		/*
		 * 비밀번호 오류 :2
		 * 회원아님 : -1
		 * 관리자:1
		 * 일반회원:0 
		 */
		
		try {
			con=getConnection();
			st = con.createStatement();
			String sql = "select pwd, admin from member where userid= '"+userid+"'"; 
			rs = st.executeQuery(sql);
			if(rs.next()) {//아이디 맞음
				if(rs.getString("pwd").equals(pwd)) {//비번맞음
					flag = rs.getInt("admin");
				}else {//비번틀림
					flag = 2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}
		return flag;
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
