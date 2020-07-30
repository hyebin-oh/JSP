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
	//��񿬰�
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

	
	@Override//�߰��ϱ�
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

	@Override //��ü����
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

	@Override //�����ϱ�
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

	@Override //�󼼺���
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

	@Override //�����ϱ�
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

	@Override //���̵� �ߺ�üũ
	public String idCheck(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String flag="yes"; //��밡��
		
		try {
			con=getConnection();
			String sql = "select * from member where userid='"+userid+"'";
			st = con.createStatement();
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				flag = "no"; //���Ұ���	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseConnection(con, st, rs);
		}		
		return flag;
	}
	

	@Override //�α��� üũ
	public int loginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int flag = -1; //ȸ���ƴ�
		try {
			con=getConnection();
			String sql = "select * from member where userid='"+userid+"'";
			st = con.createStatement();
			rs=st.executeQuery(sql);
			
			//0 �Ϲ�ȸ��, 1������, 2��� ����
			if(rs.next()) {//ȸ����
				if(rs.getString("pwd").equals(pwd)) {//�����ġ
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

	@Override //�ο���
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

	
	//�ݱ�
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
