package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class SMemberDAOImpl implements MemberDAO{
	//디비연결
	private static SMemberDAOImpl instance = new SMemberDAOImpl();
	public static SMemberDAOImpl getInstace() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}

	
	@Override//추가하기
	public void memberInsert(MemberDTO member) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql="insert into member values (?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getName());
			ps.setString(3, member.getPwd());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone());
			ps.setInt(6, member.getAdmin());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseConnection(con, ps);
		}
		
	}

	@Override //전체보기
	public ArrayList<MemberDTO> memberList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> arr = new ArrayList<MemberDTO>();
		
		try {
			con=getConnection();
			String sql = "select * from member";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setAdmin(rs.getInt("admin"));
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setPwd(rs.getString("pwd"));
				dto.setUserid(rs.getString("userid"));
				arr.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseConnection(con, st, rs);
		}
		return arr;
	}

	@Override //수정하기
	public int memberUpdate(MemberDTO member) {
		Connection con = null;
		PreparedStatement ps = null;
		int flag=0;
		try {
			con=getConnection();
			String sql = "update member set name=?, email=?, phone=?, admin=? where userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setString(3, member.getPhone());
			ps.setInt(4, member.getAdmin());
			ps.setString(5, member.getUserid());
			flag = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseConnection(con, ps);
		}
		return flag;
	}

	@Override //상세보기
	public MemberDTO memberView(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberDTO member = null;
		
		try {
			con=getConnection();
			String sql = "select * from member where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				member = new MemberDTO();
				member.setName(rs.getString("name"));
				member.setAdmin(rs.getInt("admin"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setPwd(rs.getString("pwd"));
				member.setUserid(rs.getString("userid"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseConnection(con, st, rs);			
		}
		return member;
	}

	@Override //삭제하기
	public void memberDel(String userid) {
		Connection con = null;
		Statement st = null;
		
		try {
			con=getConnection();
			String sql = "delete from member where userid='"+userid+"'";
			st=con.createStatement();
			st.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseConnection(con, st, null);
		}	
	}

	@Override //아이디 중복체크
	public String idCheck(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String flag="yes"; //사용가능
		
		try {
			con=getConnection();
			String sql = "select * from member where userid='"+userid+"'";
			st = con.createStatement();
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				flag = "no"; //사용불가능	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseConnection(con, st, rs);
		}		
		return flag;
	}
	

	@Override //로그인 체크
	public int loginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int flag = -1; //회원아님
		try {
			con=getConnection();
			String sql = "select * from member where userid='"+userid+"'";
			st = con.createStatement();
			rs=st.executeQuery(sql);
			
			//0 일반회원, 1관리자, 2비번 오류
			if(rs.next()) {//회원임
				if(rs.getString("pwd").equals(pwd)) {//비번일치
					flag = rs.getInt("admin");
				}else {
					flag = 2;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseConnection(con, st, rs);
		}		
		return flag;
	}

	@Override //인원수
	public int memberCount() {
		Connection con =null;
		Statement st = null;
		ResultSet rs = null;
		int count=0;
		
		try {
			con=getConnection();
			String sql = "select count(*) from member";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseConnection(con, st, rs);
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
	
	public void CloseConnection(Connection con, PreparedStatement ps) {
		try {
			if(con!=null) con.close();
			if(ps!=null) ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
