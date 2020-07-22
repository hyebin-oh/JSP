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

	@Override //�߰��ϱ�
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

	@Override //��ü����
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

	@Override //�����ϱ�
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

	@Override //�󼼺���
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

	@Override //�����ϱ�
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

	@Override //���̵� üũ(�ߺ�üũ)
	public String idCheck(String userid) {
		Connection con=null;
		Statement st = null;
		ResultSet rs = null;
		String flag="yes";//��밡��
		try {
			con=getConnection();
			String sql = "select * from member where userid='"+userid+"'";
			st = con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				flag="no";//���Ұ���
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}
		
		return flag;
	}
	
	@Override //�α��� üũ
	public int loginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int flag=-1; //ȸ���ƴ�
		/*
		 * ��й�ȣ ���� :2
		 * ȸ���ƴ� : -1
		 * ������:1
		 * �Ϲ�ȸ��:0 
		 */
		
		try {
			con=getConnection();
			st = con.createStatement();
			String sql = "select pwd, admin from member where userid= '"+userid+"'"; 
			rs = st.executeQuery(sql);
			if(rs.next()) {//���̵� ����
				if(rs.getString("pwd").equals(pwd)) {//�������
					flag = rs.getInt("admin");
				}else {//���Ʋ��
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
	

	@Override
	public int memberCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con=getConnection();
			st=con.createStatement();
			String sql = "select count(*) from member";
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement(con, st, rs);
		}
		return count;
	}

	//�ݱ�
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
